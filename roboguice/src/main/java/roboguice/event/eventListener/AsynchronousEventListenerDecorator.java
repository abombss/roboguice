package roboguice.event.eventListener;

import roboguice.event.EventListener;

/**
 * @author John Ericksen
 */
public class AsynchronousEventListenerDecorator<T> implements EventListener<T>{

    protected EventListener<T> eventListener;
    protected EventFireAsyncTaskFactory asyncTaskFactory;

    public AsynchronousEventListenerDecorator(EventListener<T> eventListener, EventFireAsyncTaskFactory asyncTaskFactory) {
        this.eventListener = eventListener;
        this.asyncTaskFactory = asyncTaskFactory;
    }

    public void onEvent(T event) {
        asyncTaskFactory.build(event, eventListener).execute();
    }
}
