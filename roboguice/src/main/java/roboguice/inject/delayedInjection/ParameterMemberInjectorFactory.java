package roboguice.inject.delayedInjection;

import com.google.inject.MembersInjector;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author John Ericksen
 */
public interface ParameterMemberInjectorFactory<A extends Annotation> extends DelayedInjectionFactory {

   <T> MembersInjector<T> buildParameterMemberInjector(Class<?> clazz,Method method, Class<?> parameterType, T instance, A annotation);
}
