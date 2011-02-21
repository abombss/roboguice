package roboguice.inject;

import com.google.inject.spi.TypeEncounter;
import roboguice.inject.delayedInjection.MemberInjectorFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author John Ericksen
 */
public abstract class MemberInjectorFactoryAdaptor<A extends Annotation> implements MemberInjectorFactory<A> {
    public <T> void registerField(TypeEncounter<T> typeEncounter, Class<?> clazz, Field field, A annotation) {
        //noop
    }

    public <T> void registerMethod(TypeEncounter<T> typeEncounter, Class<?> clazz, Method method, A annotation) {
        //noop
    }

    public <T> void registerParameter(TypeEncounter<T> typeEncounter, Class<?> clazz, Method method, A annotation, Class<?> parameterType) {
        //noop
    }
}
