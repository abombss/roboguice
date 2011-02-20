package roboguice.inject.delayedInjection;

import com.google.inject.spi.InjectionListener;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author John Ericksen
 */
public interface ParameterInjectionListenerFactory<A extends Annotation> extends DelayedInjectionFactory {

   <T> InjectionListener<T> buildParameterMemberInjector(Class<?> clazz, Method method, Class<?> parameterType, T instance, A annotation);
}
