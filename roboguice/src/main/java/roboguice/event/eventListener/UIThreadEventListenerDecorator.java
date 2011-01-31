package roboguice.event.eventListener;

import android.os.Handler;
import roboguice.event.EventListener;

/**
 * @author John Ericksen
 */
public class UIThreadEventListenerDecorator<T> implements EventListener<T> {

    protected EventListener<T> eventListener;
    protected Handler handler;
    protected EventFireRunnableFactory eventFireFactory;

    public UIThreadEventListenerDecorator(EventListener<T> eventListener, Handler handler, EventFireRunnableFactory eventFireFactory) {
        this.eventListener = eventListener;
        this.handler = handler;
        this.eventFireFactory = eventFireFactory;
    }

    public void onEvent(T event) {
        handler.post(eventFireFactory.build(event, eventListener));
    }
}
