package roboguice.inject.delayedInjection;

import com.google.inject.spi.InjectionListener;

/**
 * @author John Ericksen
 */
public abstract class DelayedInjectionListener<T> implements InjectionListener<T>{

    protected T instance;

    @Override
    public void afterInjection(T instance) {
        getMembersInjector().afterInjection(instance);
    }

    public abstract InjectionListener<T> getMembersInjector();
}
