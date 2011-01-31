package roboguice.config;

import android.content.Context;
import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.matcher.Matchers;
import roboguice.event.EventManager;
import roboguice.event.ObservesTypeListener;
import roboguice.event.eventListener.ObservesThreadingFactory;

/**
 * @author John Ericksen
 */
public class EventManagerModule extends AbstractModule {

    protected EventManager eventManager;
    protected Provider<Context> contextProvider;

    public EventManagerModule(EventManager eventManager, Provider<Context> contextProvider) {
        this.eventManager = eventManager;
        this.contextProvider = contextProvider;
    }

    @Override
    protected void configure() {

        // Context observers
        bind(EventManager.class).toInstance(eventManager);
        ObservesThreadingFactory observerThreadingFactory = new ObservesThreadingFactory();
        requestInjection(observerThreadingFactory);
        bindListener(Matchers.any(), new ObservesTypeListener(contextProvider, eventManager,observerThreadingFactory));
        requestInjection(eventManager);
    }
}
