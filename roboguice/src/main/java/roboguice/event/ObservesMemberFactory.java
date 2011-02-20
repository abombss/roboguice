package roboguice.event;

import android.content.Context;
import com.google.inject.Provider;
import com.google.inject.spi.InjectionListener;
import com.google.inject.spi.TypeEncounter;
import roboguice.inject.MemberInjectorFactoryAdaptor;

import java.lang.reflect.Method;

/**
 * @author John Ericksen
 */
public class ObservesMemberFactory extends MemberInjectorFactoryAdaptor<Observes> {

    private Provider<Context> contextProvider;
    private EventManager eventManager;

    public ObservesMemberFactory(Provider<Context> contextProvider, EventManager eventManager) {
        this.contextProvider = contextProvider;
        this.eventManager = eventManager;
    }

    public <T> void registerParameter(TypeEncounter<T> typeEncounter, Class<?> clazz, Method method, Observes annotation, Class<?> parameterType) {
        checkMethodParameters(method, clazz);
        typeEncounter.register(new ContextObserverMethodInjector<T>(contextProvider, eventManager, method, parameterType));
    }

    /**
     * Error checking method, verifies:
     *
     * 1.  The method has the correct number of parameters, 0 or 1
     * 2.  If the method has a parameter, it is of the proper type.
     *
     * @param method
     * @param parameterType
     */
    protected void checkMethodParameters(Method method, Class parameterType) {
        if(method.getParameterTypes().length > 1)
            throw new RuntimeException("Annotation @Observes must only annotate one parameter," +
                    " which must be the only parameter in the listener method.");

        if(method.getParameterTypes().length == 1 && !method.getParameterTypes()[0].equals(parameterType))
            throw new RuntimeException("Value injected by Observer or Observes in method " +
                    method.getDeclaringClass().getCanonicalName() + "." + method.getName() +
                    " must match annotated type " + parameterType.getName() + " .");

    }

    public static class ContextObserverMethodInjector<I> implements InjectionListener<I> {
        protected Provider<Context> contextProvider;
        protected EventManager eventManager;
        protected Method method;
        protected Class<?> event;

        public ContextObserverMethodInjector(Provider<Context> contextProvider, EventManager eventManager, Method method, Class<?> event) {
            this.contextProvider = contextProvider;
            this.eventManager = eventManager;
            this.method = method;
            this.event = event;
        }

        public void afterInjection(I i) {
            eventManager.registerObserver(contextProvider.get(), i, method, event);
        }
    }
}
