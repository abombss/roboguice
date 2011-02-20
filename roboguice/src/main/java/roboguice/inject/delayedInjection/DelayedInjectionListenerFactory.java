package roboguice.inject.delayedInjection;

import com.google.inject.spi.TypeEncounter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author John Ericksen
 */
public class DelayedInjectionListenerFactory<A extends Annotation> implements MemberInjectorFactory<A> {

    private DelayedInjectionFactory factory;

    public DelayedInjectionListenerFactory(DelayedInjectionFactory factory) {
        this.factory = factory;
    }

    @Override
    public <T> void registerField(TypeEncounter<T> typeEncounter, Class<?> clazz, Field field, A annotation) {
        register(typeEncounter, new DelayedFieldInjectionListenerFactory<T, A>(clazz, field, annotation, (FieldInjectionListenerFactory<A>) factory));
    }

    @Override
    public <T> void registerMethod(TypeEncounter<T> typeEncounter, Class<?> clazz, Method method, A annotation) {
        register(typeEncounter, new DelayedMethodInjectionListenerFactory<T, A>(clazz, method, annotation, (MethodInjectionListenerFactory<A>) factory));
    }

    @Override
    public <T> void registerParameter(TypeEncounter<T> typeEncounter, Class<?> clazz, Method method, A annotation, Class<?> parameterType) {
        register(typeEncounter, new DelayedParameterInjectionListenerFactory<T, A>(clazz, method, annotation, parameterType, (ParameterInjectionListenerFactory<A>) factory));
    }

    private <T> void register(TypeEncounter<T> typeEncounter, DelayedInjectionListener delayedInjector){
        typeEncounter.register(delayedInjector);
    }
}
