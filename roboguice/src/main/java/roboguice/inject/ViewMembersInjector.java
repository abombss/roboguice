package roboguice.inject;

import android.app.Activity;
import android.content.Context;
import com.google.inject.MembersInjector;
import com.google.inject.Provider;
import com.google.inject.internal.Nullable;

import java.lang.reflect.Field;

import static com.google.inject.internal.Preconditions.checkNotNull;

/**
 * @author John Ericksen
 */
class ViewMembersInjector<T> implements MembersInjector<T> {
    protected Field field;
    protected Provider<Context> contextProvider;
    protected InjectView annotation;
    protected T instance;

    public ViewMembersInjector(Field field, Provider<Context> contextProvider, InjectView annotation) {
        this.field = field;
        this.annotation = annotation;
        this.contextProvider = contextProvider;
    }

    public void injectMembers(T instance) {
        checkNotNull(instance);

        Object value = null;

        try {

            value = ((Activity) contextProvider.get()).findViewById(annotation.value());

            if (value == null && field.getAnnotation(Nullable.class) == null)
                throw new NullPointerException(String.format("Can't inject null value into %s.%s when field is not @Nullable", field.getDeclaringClass(), field.getName()));

            field.setAccessible(true);
            field.set(instance, value);

        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);

        } catch (IllegalArgumentException f) {
            throw new IllegalArgumentException(String.format("Can't assign %s value %s to %s field %s", value != null ? value.getClass() : "(null)", value,
                    field.getType(), field.getName()));
        }
    }
}
