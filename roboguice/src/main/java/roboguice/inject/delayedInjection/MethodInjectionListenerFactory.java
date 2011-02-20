package roboguice.inject.delayedInjection;

import com.google.inject.spi.InjectionListener;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author John Ericksen
 */
public interface MethodInjectionListenerFactory<A extends Annotation> extends DelayedInjectionFactory {

   <T> InjectionListener<T> buildMethodMemberInjector(Class<?> clazz, Method method, A annotation);
}
