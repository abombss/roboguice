package roboguice.inject.delayedInjection;

import com.google.inject.spi.InjectionListener;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author John Ericksen
 */
public class DelayedFieldInjectionListenerFactory<T, A extends Annotation> extends DelayedInjectionListener<T> {
    protected Class<?> clazz;
    protected Field field;
    protected A annotation;
    protected FieldInjectionListenerFactory<A> factoryProvider;

    public DelayedFieldInjectionListenerFactory(Class<?> clazz, Field field, A annotation, FieldInjectionListenerFactory<A> factoryProvider) {
        this.clazz = clazz;
        this.field = field;
        this.annotation = annotation;
        this.factoryProvider = factoryProvider;
    }

    @Override
    public InjectionListener<T> getMembersInjector() {
        return factoryProvider.buildFieldMemberInjector(clazz, field, annotation);
    }
}
