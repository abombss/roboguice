package roboguice.event;

import android.content.Context;

import java.lang.reflect.Method;

/**
 * No-op event manager used for disabling and stubbing out the use of the event functionality.
 *
 * @author Adam Tybor
 * @author John Ericksen
 */
public class NoOpEventManagerImpl implements EventManager {
    @Override
    public void registerObserver(Context context, Object instance, Method method, Class event) {
        //noop
    }

    @Override
    public void unregisterObserver(Context context, Object instance, Class event) {
        //noop
    }

    @Override
    public <T> void registerObserver(Class<T> event, EventListener listener) {
        //noop
    }

    @Override
    public <T> void registerObserver(Object instance, Method method, Class<T> event) {
        //noop
    }

    @Override
    public <T> void unregisterObserver(Class<T> event, EventListener<T> listener) {
        //noop
    }

    @Override
    public <T> void unregisterObserver(Object instance, Class<T> event) {
        //noop
    }

    @Override
    public void clear() {
        //noop
    }

    @Override
    public <T> void registerObserver(Context context, Class<T> event, EventListener listener) {
        //noop
    }

    @Override
    public <T> void unregisterObserver(Context context, Class<T> event, EventListener<T> listener) {
        //noop
    }

    @Override
    public void clear(Context context) {
        //noop
    }

    @Override
    public void fire(Context context, Object event) {
        //noop
    }

    @Override
    public void fire(Object event) {
        //noop
    }
}
