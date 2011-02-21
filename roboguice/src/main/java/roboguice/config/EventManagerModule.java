package roboguice.config;

import android.content.Context;
import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import roboguice.event.EventManager;
import roboguice.event.Observes;
import roboguice.event.ObservesTypeListenerFactory;
import roboguice.inject.CustomInjectionRegistrationListener;
import roboguice.inject.delayedInjection.DelayedInjectionListenerFactory;

/**
 * @author John Ericksen
 */
public class EventManagerModule extends AbstractModule {

    protected CustomInjectionRegistrationListener customInjectionRegistrationListener;
    protected Provider<Context> contextProvider;

    public EventManagerModule(Provider<Context> contextProvider, CustomInjectionRegistrationListener customInjectionRegistrationListener){
        this.customInjectionRegistrationListener = customInjectionRegistrationListener;
        this.contextProvider = contextProvider;
    }

    @Override
    protected void configure() {
        // Context observers

        EventManager eventManager = new EventManager(contextProvider);
        bind(EventManager.class).toInstance(eventManager);
        ObservesTypeListenerFactory observesTypeListenerFactory = new ObservesTypeListenerFactory(eventManager, contextProvider);

        DelayedInjectionListenerFactory observesDelayedInjectorFactory =
                new DelayedInjectionListenerFactory(observesTypeListenerFactory);
        customInjectionRegistrationListener.registerMemberInjector(Observes.class, observesDelayedInjectorFactory);
    }
}
