package roboguice.inject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.google.inject.*;
import com.google.inject.internal.Nullable;
import com.google.inject.util.Types;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Map;

/**
* @author John Ericksen
*/
class ExtrasMembersInjector<T> implements MembersInjector<T> {
    protected Field field;
    protected Provider<Context> contextProvider;
    protected InjectExtra annotation;

    public ExtrasMembersInjector(Field field, Provider<Context> contextProvider, InjectExtra annotation) {
        this.field = field;
        this.contextProvider = contextProvider;
        this.annotation = annotation;
    }

    public void injectMembers(T instance) {
        final Context context = contextProvider.get();

        if (!(context instanceof Activity)) {
            return;
        }

        final Activity activity = (Activity) context;
        Object value = null;

        final String id = annotation.value();
        final Bundle extras = activity.getIntent().getExtras();

        if (extras == null || !extras.containsKey(id)) {
            // If no extra found and the extra injection is optional, no
            // injection happens.
            if (annotation.optional()) {
                return;
            } else {
                throw new IllegalStateException(String.format("Can't find the mandatory extra identified by key [%s] on field %s.%s", id, field
                        .getDeclaringClass(), field.getName()));
            }
        }

        value = extras.get(id);

        // Context must implement InjectorProvider to enable extra conversion
        /*TODO:fix this?
        if (context instanceof InjectorProvider) {
            Injector injector = ((InjectorProvider) context).getInjector();
            value = convert(field, value, injector);
        }*/

        /*
         * Please notice : null checking is done AFTER conversion. Having
         *
         * @Nullable on a field means "the injected value might be null", ie
         * "the converted value might be null". Which also means that if you
         * don't use @Nullable and a converter returns null, an exception will
         * be thrown (which I find to be the most logic behavior).
         */
        if (value == null && field.getAnnotation(Nullable.class) == null) {
            throw new NullPointerException(String.format("Can't inject null value into %s.%s when field is not @Nullable", field.getDeclaringClass(), field
                    .getName()));
        }

        field.setAccessible(true);
        try {

            if(instance  != null)
            field.set(instance, value);

        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);

        } catch (IllegalArgumentException f) {
            throw new IllegalArgumentException(String.format("Can't assign %s value %s to %s field %s", value != null ? value.getClass() : "(null)", value,
                    field.getType(), field.getName()));
        }
    }

    @SuppressWarnings("unchecked")
    protected Object convert(Field field, Object value, Injector injector) {

        // Don't try to convert null or primitives
        if (value == null || field.getType().isPrimitive()) {
            return value;
        }

        // Building parameterized converter type
        // Please notice that the extra type and the field type must EXACTLY
        // match the declared converter parameter types.
        ParameterizedType pt = Types.newParameterizedType(ExtraConverter.class, value.getClass(), field.getType());
        Key<?> key = Key.get(pt);

        // Getting bindings map to check if a binding exists
        // We DO NOT currently check for injector's parent bindings. Should we ?
        Map<Key<?>, Binding<?>> bindings = injector.getBindings();

        if (bindings.containsKey(key)) {
            ExtraConverter converter = (ExtraConverter) injector.getInstance(key);
            value = converter.convert(value);
        }

        return value;

    }
}
