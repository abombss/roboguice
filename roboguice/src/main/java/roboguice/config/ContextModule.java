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
    protected Provider<Context> contextProvider;

    public ContextModule(Context context, Provider<Context> contextProvider, CustomInjectionRegistrationListener customInjectionRegistrationListener, boolean injectorPreferenceListener) {
        this.context = context;
        this.contextProvider = contextProvider;
        this.customInjectionRegistrationListener = customInjectionRegistrationListener;
        this.injectorPreferenceListener = injectorPreferenceListener;
    }

    @Override
    protected void configure() {



        bind(Context.class).toProvider(contextProvider);
        bind(((Class<Context>) context.getClass())).toProvider(contextProvider);
        bind(Activity.class).toProvider(ActivityProvider.class);

        customInjectionRegistrationListener.registerMemberInjector(InjectExtra.class,
               buildDelayedInjecitonFactory(new ExtrasListenerFactory(contextProvider)));
        customInjectionRegistrationListener.registerMemberInjector(InjectView.class,
                buildDelayedInjecitonFactory(new ViewListenerFactory(contextProvider)));

        if (injectorPreferenceListener)
            customInjectionRegistrationListener.registerMemberInjector(InjectPreference.class,
                    buildDelayedInjecitonFactory(new PreferenceListenerFactory(contextProvider)));
    }

    private MemberInjectorFactory buildDelayedInjecitonFactory(DelayedInjectionFactory factory){
        DelayedInjectionMemberInjectorFactory delayedInjectionFactory = new DelayedInjectionMemberInjectorFactory(factory);
        return delayedInjectionFactory;
    }


}
