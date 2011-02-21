package roboguice.inject.delayedInjection;

import com.google.inject.spi.InjectionListener;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author John Ericksen
 */
public interface FieldInjectionListenerFactory<A extends Annotation> extends DelayedInjectionFactory {

   <T> InjectionListener<T> buildFieldMemberInjector(Class<?> clazz, Field field, A annotation);
}
