package roboguice.event.eventListener;

import com.google.inject.Inject;
import roboguice.event.EventListener;

/**
 * @author John Ericksen
 */
public class AsynchronousEventListenerDecorator<T> implements EventListener<T>{

    protected EventListener<T> eventListener;
    @Inject
    protected EventFireAsyncTaskFactory asyncTaskFactory;

    public AsynchronousEventListenerDecorator(EventListener<T> eventListener) {
        this.eventListener = eventListener;
    }

    public void onEvent(T event) {
        asyncTaskFactory.build(event, eventListener).execute();
    }
}
