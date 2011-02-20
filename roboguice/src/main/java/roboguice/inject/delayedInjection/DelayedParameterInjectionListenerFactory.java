package roboguice.inject.delayedInjection;

import com.google.inject.spi.InjectionListener;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author John Ericksen
 */
public class DelayedParameterInjectionListenerFactory<T, A extends Annotation> extends DelayedInjectionListener<T> {
    protected Class<?> clazz;
    protected Method method;
    protected A annotation;
    protected Class<?> parameterType;
    protected ParameterInjectionListenerFactory<A> factoryProvider;

    public DelayedParameterInjectionListenerFactory(Class<?> clazz, Method method, A annotation, Class<?> parameterType, ParameterInjectionListenerFactory<A> factoryProvider) {
        this.clazz = clazz;
        this.method = method;
        this.annotation = annotation;
        this.parameterType = parameterType;
        this.factoryProvider = factoryProvider;
    }

    @Override
    public InjectionListener<T> getMembersInjector() {
        return factoryProvider.buildParameterMemberInjector(clazz, method, parameterType, instance, annotation);
    }
}
