package roboguice.inject.delayedInjection;

import com.google.inject.MembersInjector;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author John Ericksen
 */
public interface MethodMemberInjectorFactory<A extends Annotation> extends DelayedInjectionFactory {

   <T> MembersInjector<T> buildMethodMemberInjector(Class<?> clazz, Method method, A annotation);
}
