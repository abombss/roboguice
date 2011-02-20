package roboguice.inject.delayedInjection;

import com.google.inject.MembersInjector;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author John Ericksen
 */
public interface FieldMemberInjectorFactory<A extends Annotation> extends DelayedInjectionFactory {

   <T> MembersInjector<T> buildFieldMemberInjector(Class<?> clazz, Field field, A annotation);
}
