package roboguice.inject.delayedInjection;

import com.google.inject.MembersInjector;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author John Ericksen
 */
public class DelayedMethodMemberInjectorFactory<T, A extends Annotation> extends DelayedMembersInjector<T> {
    protected Class<?> clazz;
    protected Method method;
    protected A annotation;
    protected MethodMemberInjectorFactory<A> factoryProvider;

    public DelayedMethodMemberInjectorFactory(Class<?> clazz, Method method, A annotation, MethodMemberInjectorFactory<A> factoryProvider) {
        this.clazz = clazz;
        this.method = method;
        this.annotation = annotation;
        this.factoryProvider = factoryProvider;
    }

    @Override
    public MembersInjector<T> getMembersInjector() {
        return factoryProvider.buildMethodMemberInjector(clazz, method, annotation);
    }
}
