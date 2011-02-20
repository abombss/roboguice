package roboguice.event;

import android.content.Context;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.spi.InjectionListener;
import roboguice.inject.delayedInjection.ParameterInjectionListenerFactory;

import java.lang.reflect.Method;

/**
 * Guice driven type listener which scans for the @Observes annotations.
 * Registers these methods with the EventManager.
 *
 * @author Adam Tybor
 * @author John Ericksen
 */
public class ObservesTypeListenerFactory<T> implements ParameterInjectionListenerFactory<Observes> {
    @Inject
    protected EventManager eventManager;
    @Inject
    protected Provider<Context> contextProvider;

    @Override
    public <T> InjectionListener<T> buildParameterMemberInjector(Class<?> clazz, Method method, Class<?> parameterType, T instance, Observes annotation) {
        checkMethodParameters(method);
        return new ContextObserverMethodInjector<T>(contextProvider, eventManager, method, parameterType);
    }

    /**
     * Error checking method, verifies that the method has the correct number of parameters.
     *
     * @param method
     */
    protected void checkMethodParameters(Method method) {
        if(method.getParameterTypes().length > 1)
            throw new RuntimeException("Annotation @Observes must only annotate one parameter," +
                    " which must be the only parameter in the listener method.");
    }

    /**
     * Injection listener to handle the observation manager registration.
     *
     * @param <I>
     */
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
