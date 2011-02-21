package roboguice.inject.delayedInjection;

import com.google.inject.spi.TypeEncounter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author John Ericksen
 */
public class DelayedInjectionMemberInjectorFactory<A extends Annotation> implements MemberInjectorFactory<A> {

    private DelayedInjectionFactory factory;

    public DelayedInjectionMemberInjectorFactory(DelayedInjectionFactory factory) {
        this.factory = factory;
    }

    @Override
    public <T> void registerField(TypeEncounter<T> typeEncounter, Class<?> clazz, Field field, A annotation) {
        register(typeEncounter, new DelayedFieldMemberInjectorFactory<T, A>(clazz, field, annotation, (FieldMemberInjectorFactory<A>) factory));
    }

    @Override
    public <T> void registerMethod(TypeEncounter<T> typeEncounter, Class<?> clazz, Method method, A annotation) {
        register(typeEncounter, new DelayedMethodMemberInjectorFactory<T, A>(clazz, method, annotation, (MethodMemberInjectorFactory<A>) factory));
    }

    @Override
    public <T> void registerParameter(TypeEncounter<T> typeEncounter, Class<?> clazz, Method method, A annotation, Class<?> parameterType) {
        register(typeEncounter, new DelayedParameterMemberInjectorFactory<T, A>(clazz, method, annotation, parameterType, (ParameterMemberInjectorFactory<A>) factory));
    }

    private <T> void register(TypeEncounter<T> typeEncounter, DelayedMembersInjector delayedInjector){
        typeEncounter.register(delayedInjector);
    }
}
