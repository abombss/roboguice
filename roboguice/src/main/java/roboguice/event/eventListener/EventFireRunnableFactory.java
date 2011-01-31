package roboguice.event.eventListener;

import roboguice.event.EventListener;

/**
 * @author John Ericksen
 */
public class EventFireRunnableFactory {
    public <T> EventFireRunnable<T> build(T event, EventListener<T> eventListener) {
        return new EventFireRunnable<T>(event, eventListener);
    }
}
