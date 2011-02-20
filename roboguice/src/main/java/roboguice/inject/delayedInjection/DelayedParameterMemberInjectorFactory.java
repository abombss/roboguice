package roboguice.inject.delayedInjection;

import com.google.inject.MembersInjector;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author John Ericksen
 */
public class DelayedParameterMemberInjectorFactory<T, A extends Annotation> extends DelayedMembersInjector<T> {
    protected Class<?> clazz;
    protected Method method;
    protected A annotation;
    protected Class<?> parameterType;
    protected ParameterMemberInjectorFactory<A> factoryProvider;

    public DelayedParameterMemberInjectorFactory(Class<?> clazz, Method method, A annotation, Class<?> parameterType, ParameterMemberInjectorFactory<A> factoryProvider) {
        this.clazz = clazz;
        this.method = method;
        this.annotation = annotation;
        this.parameterType = parameterType;
        this.factoryProvider = factoryProvider;
    }

    @Override
    public MembersInjector<T> getMembersInjector() {
        return factoryProvider.buildParameterMemberInjector(clazz, method, parameterType, instance, annotation);
    }
}
