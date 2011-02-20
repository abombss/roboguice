package roboguice.inject.delayedInjection;

import com.google.inject.MembersInjector;
import com.google.inject.spi.TypeEncounter;

/**
 * @author John Ericksen
 */
public abstract class DelayedMembersInjector<T> implements MembersInjector<T> {

    protected T instance;
    protected TypeEncounter<T> typeEncounter;

    @Override
    public void injectMembers(T instance) {
        getMembersInjector().injectMembers(instance);
    }

    public abstract MembersInjector<T> getMembersInjector();
}
