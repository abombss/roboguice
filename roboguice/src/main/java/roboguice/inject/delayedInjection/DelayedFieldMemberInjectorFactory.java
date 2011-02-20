package roboguice.inject.delayedInjection;

import com.google.inject.MembersInjector;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author John Ericksen
 */
public class DelayedFieldMemberInjectorFactory<T, A extends Annotation> extends DelayedMembersInjector<T> {
    protected Class<?> clazz;
    protected Field field;
    protected A annotation;
    protected FieldMemberInjectorFactory<A> factoryProvider;

    public DelayedFieldMemberInjectorFactory(Class<?> clazz, Field field, A annotation, FieldMemberInjectorFactory<A> factoryProvider) {
        this.clazz = clazz;
        this.field = field;
        this.annotation = annotation;
        this.factoryProvider = factoryProvider;
    }

    @Override
    public MembersInjector<T> getMembersInjector() {
        return factoryProvider.buildFieldMemberInjector(clazz, field, annotation);
    }
}
