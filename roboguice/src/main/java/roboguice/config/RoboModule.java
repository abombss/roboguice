package roboguice.config;

import android.app.*;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.PowerManager;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.matcher.Matchers;
import roboguice.inject.*;
import roboguice.util.Ln;

import java.util.List;

/**
 * A Module that provides bindings and configuration to use Guice on Android.
 * Used by {@link roboguice.application.RoboApplication}.
 *
 * @author Mike Burton
 * @author Pierre-Yves Ricau (py.ricau+roboguice@gmail.com)
 */
public class RoboModule extends AbstractModule {


    protected ResourceListener resourceListener;
    protected ViewListenerFactory viewListenerFactory;
    protected ExtrasListenerFactory extrasListenerFactory;
    protected PreferenceListenerFactory preferenceListenerFactory;
    protected Application application;
    protected CustomInjectionRegistrationListener customInjectionRegistrationListener;
    protected boolean injectorPreferenceListener;

    public RoboModule(ResourceListener resourceListener, ViewListenerFactory viewListenerFactory, ExtrasListenerFactory extrasListenerFactory,
            PreferenceListenerFactory preferenceListenerFactory, Application application, CustomInjectionRegistrationListener customInjectionRegistrationListener,
            boolean injectorPreferenceListener) {
        this.resourceListener = resourceListener;
        this.viewListenerFactory = viewListenerFactory;
        this.extrasListenerFactory = extrasListenerFactory;
        this.preferenceListenerFactory = preferenceListenerFactory;
        this.application = application;
        this.customInjectionRegistrationListener = customInjectionRegistrationListener;
        this.injectorPreferenceListener = injectorPreferenceListener;
    }

    /**
     * Configure this module to define Android related bindings.<br />
     * <br />
     * If you want to provide your own bindings, you should <strong>NOT</strong>
     * override this method, but rather create a {@link Module} implementation
     * and add it to the configuring modules by overriding
     * {@link roboguice.application.RoboApplication#addApplicationModules(List)}.<br />
     */
    @SuppressWarnings("unchecked")
    @Override
    protected void configure() {
        // Context Scope bindings
        bind(AssetManager.class).toProvider( AssetManagerProvider.class );

        // Sundry Android Classes
        bind(SharedPreferences.class).toProvider(SharedPreferencesProvider.class);
        bind(Resources.class).toProvider(ResourcesProvider.class);
        bind(ContentResolver.class).toProvider(ContentResolverProvider.class);
        for (Class<?> c = application.getClass(); c != null && Application.class.isAssignableFrom(c); c = c.getSuperclass())
            bind((Class<Object>) c).toInstance(application);


        // System Services
        bind(LocationManager.class).toProvider(new SystemServiceProvider<LocationManager>(Context.LOCATION_SERVICE));
        bind(WindowManager.class).toProvider(new SystemServiceProvider<WindowManager>(Context.WINDOW_SERVICE));
        bind(LayoutInflater.class).toProvider(new SystemServiceProvider<LayoutInflater>(Context.LAYOUT_INFLATER_SERVICE));
        bind(ActivityManager.class).toProvider(new SystemServiceProvider<ActivityManager>(Context.ACTIVITY_SERVICE));
        bind(PowerManager.class).toProvider(new SystemServiceProvider<PowerManager>(Context.POWER_SERVICE));
        bind(AlarmManager.class).toProvider(new SystemServiceProvider<AlarmManager>(Context.ALARM_SERVICE));
        bind(NotificationManager.class).toProvider(new SystemServiceProvider<NotificationManager>(Context.NOTIFICATION_SERVICE));
        bind(KeyguardManager.class).toProvider(new SystemServiceProvider<KeyguardManager>(Context.KEYGUARD_SERVICE));
        bind(SearchManager.class).toProvider(new SystemServiceProvider<SearchManager>(Context.SEARCH_SERVICE));
        bind(Vibrator.class).toProvider(new SystemServiceProvider<Vibrator>(Context.VIBRATOR_SERVICE));
        bind(ConnectivityManager.class).toProvider(new SystemServiceProvider<ConnectivityManager>(Context.CONNECTIVITY_SERVICE));
        bind(WifiManager.class).toProvider(new SystemServiceProvider<WifiManager>(Context.WIFI_SERVICE));
        bind(InputMethodManager.class).toProvider(new SystemServiceProvider<InputMethodManager>(Context.INPUT_METHOD_SERVICE));
        bind(SensorManager.class).toProvider(new SystemServiceProvider<SensorManager>(Context.SENSOR_SERVICE));


        // Android Resources, Views and extras require special handling
        bindListener(Matchers.any(), resourceListener);
        
        requestStaticInjection( Ln.class );
    }



}
