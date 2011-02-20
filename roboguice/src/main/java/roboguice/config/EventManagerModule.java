package roboguice.config;

import com.google.inject.AbstractModule;
import roboguice.event.Observes;
import roboguice.event.ObservesTypeListenerFactory;
import roboguice.inject.CustomInjectionRegistrationListener;
import roboguice.inject.delayedInjection.DelayedInjectionListenerFactory;

/**
 * @author John Ericksen
 */
public class EventManagerModule extends AbstractModule {

    protected CustomInjectionRegistrationListener customInjectionRegistrationListener;

    public EventManagerModule(CustomInjectionRegistrationListener customInjectionRegistrationListener){
        this.customInjectionRegistrationListener = customInjectionRegistrationListener;
    }

    @Override
    protected void configure() {
        // Context observers
        ObservesTypeListenerFactory observesTypeListenerFactory = new ObservesTypeListenerFactory();
        requestInjection(observesTypeListenerFactory);
        DelayedInjectionListenerFactory observesDelayedInjectorFactory =
                new DelayedInjectionListenerFactory(observesTypeListenerFactory);
        customInjectionRegistrationListener.registerMemberInjector(Observes.class, observesDelayedInjectorFactory);

        requestInjection(observesDelayedInjectorFactory);
    }
}
