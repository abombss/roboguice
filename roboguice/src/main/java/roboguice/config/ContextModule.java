package roboguice.config;

import android.app.Activity;
import android.content.Context;
import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import roboguice.inject.*;
import roboguice.inject.delayedInjection.DelayedInjectionFactory;
import roboguice.inject.delayedInjection.DelayedInjectionMemberInjectorFactory;
import roboguice.inject.delayedInjection.MemberInjectorFactory;

/**
 * @author John Ericksen
 */
public class ContextModule extends AbstractModule {

    private Context context;
    protected CustomInjectionRegistrationListener customInjectionRegistrationListener;
    protected boolean injectorPreferenceListener;

    public ContextModule(Context context, CustomInjectionRegistrationListener customInjectionRegistrationListener, boolean injectorPreferenceListener) {
        this.context = context;
        this.customInjectionRegistrationListener = customInjectionRegistrationListener;
        this.injectorPreferenceListener = injectorPreferenceListener;
    }

    @Override
    protected void configure() {

        Provider<Context> contextProvider = new Provider<Context>() {
            @Override
            public Context get() {
                return context;
            }
        };

        bind(Context.class).toProvider(contextProvider);
        bind(((Class<Context>) context.getClass())).toProvider(contextProvider);
        bind(Activity.class).toProvider(ActivityProvider.class);

        customInjectionRegistrationListener.registerMemberInjector(InjectExtra.class,
               buildDelayedInjecitonFactory(new ExtrasListenerFactory()));
        customInjectionRegistrationListener.registerMemberInjector(InjectView.class,
                buildDelayedInjecitonFactory(new ViewListenerFactory()));

        if (injectorPreferenceListener)
            customInjectionRegistrationListener.registerMemberInjector(InjectPreference.class,
                    buildDelayedInjecitonFactory(new PreferenceListenerFactory()));
    }

    private MemberInjectorFactory buildDelayedInjecitonFactory(DelayedInjectionFactory factory){
        requestInjection(factory);
        DelayedInjectionMemberInjectorFactory delayedInjectionFactory = new DelayedInjectionMemberInjectorFactory(factory);
        requestInjection(delayedInjectionFactory);
        return delayedInjectionFactory;
    }


}
