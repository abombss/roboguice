package roboguice.inject.delayedInjection;

import com.google.inject.spi.InjectionListener;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author John Ericksen
 */
public class DelayedMethodInjectionListenerFactory<T, A extends Annotation> extends DelayedInjectionListener<T> {
    protected Class<?> clazz;
    protected Method method;
    protected A annotation;
    protected MethodInjectionListenerFactory<A> factoryProvider;

    public DelayedMethodInjectionListenerFactory(Class<?> clazz, Method method, A annotation, MethodInjectionListenerFactory<A> factoryProvider) {
        this.clazz = clazz;
        this.method = method;
        this.annotation = annotation;
        this.factoryProvider = factoryProvider;
    }

    @Override
    public InjectionListener<T> getMembersInjector() {
        return factoryProvider.buildMethodMemberInjector(clazz, method, annotation);
    }
}
