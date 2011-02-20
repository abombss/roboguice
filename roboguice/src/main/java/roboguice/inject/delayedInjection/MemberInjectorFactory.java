package roboguice.inject.delayedInjection;

import com.google.inject.spi.TypeEncounter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author John Ericksen
 */
public interface MemberInjectorFactory<A extends Annotation> {
    <T> void registerField(TypeEncounter<T> typeEncounter, Class<?> clazz, Field field, A annotation);

    <T> void registerMethod(TypeEncounter<T> typeEncounter, Class<?> clazz, Method method, A annotation);

    <T> void registerParameter(TypeEncounter<T> typeEncounter, Class<?> clazz, Method method, A annotation, Class<?> parameterType);
}
