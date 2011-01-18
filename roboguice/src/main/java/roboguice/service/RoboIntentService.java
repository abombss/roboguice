package roboguice.service;

import roboguice.application.RoboApplication;
import roboguice.event.EventManager;
import roboguice.inject.ContextScope;
import roboguice.inject.InjectorProvider;
import roboguice.service.event.OnConfigurationChangedEvent;
import roboguice.service.event.OnCreateEvent;
import roboguice.service.event.OnDestroyEvent;
import roboguice.service.event.OnStartEvent;

import android.app.IntentService;
import android.content.Intent;
import android.content.res.Configuration;

import com.google.inject.Injector;

/**
 * A {@link RoboIntentService} extends from {@link IntentService} to provide dynamic
 * injection of collaborators, using Google Guice.<br /> <br />
 * <p/>
 * Your own services that usually extend from {@link IntentService} should now extend from
 * {@link RoboIntentService}.<br /> <br />
 * <p/>
 * If we didn't provide what you need, you have two options : either post an issue on <a
 * href="http://code.google.com/p/roboguice/issues/list">the bug tracker</a>, or
 * implement it yourself. Have a look at the source code of this class (
 * {@link RoboIntentService}), you won't have to write that much changes. And of
 * course, you are welcome to contribute and send your implementations to the
 * RoboGuice project.<br /> <br />
 * <p/>
 * You can have access to the Guice
 * {@link Injector} at any time, by calling {@link #getInjector()}.<br />
 * <p/>
 * However, you will not have access to Context scoped beans until
 * {@link #onCreate()} is called. <br /> <br />
 *
 * @author Donn Felker
 */
public abstract class RoboIntentService extends IntentService implements InjectorProvider {

    protected EventManager eventManager;
    protected ContextScope scope;


    public RoboIntentService(String name) {
        super(name);
    }


    @Override
    public void onCreate() {
        final Injector injector = getInjector();
        eventManager = injector.getInstance(EventManager.class);
        scope = injector.getInstance(ContextScope.class);
        scope.enter(this);
        injector.injectMembers(this);
        super.onCreate();
        eventManager.fire(this, new OnCreateEvent() );
    }

    @Override
    public void onStart(Intent intent, int startId) {
        scope.enter(this);
        super.onStart(intent, startId);
        eventManager.fire(this, new OnStartEvent() );
    }


    @Override
    public void onDestroy() {
        eventManager.fire(this, new OnDestroyEvent() );
        scope.exit(this);
        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        eventManager.fire(this, new OnConfigurationChangedEvent() );
    }

    /**
     * @see roboguice.application.RoboApplication#getInjector()
     */
    public Injector getInjector() {
        return ((RoboApplication) getApplication()).getInjector();
    }


}
