package roboguice.config;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import roboguice.inject.CustomInjectionRegistrationListener;

/**
 * @author John Ericksen
 */
public class CustomInjectionModule extends AbstractModule {
    
    private CustomInjectionRegistrationListener customInjectionRegistrationListener;

    public CustomInjectionModule(CustomInjectionRegistrationListener customInjectionRegistrationListener) {
        this.customInjectionRegistrationListener = customInjectionRegistrationListener;
    }

    @Override
    protected void configure() {
        bindListener(Matchers.any(), customInjectionRegistrationListener);
    }
}
