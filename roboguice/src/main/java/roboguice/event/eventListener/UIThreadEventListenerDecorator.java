package roboguice.event.eventListener;

import android.os.Handler;
import roboguice.event.EventListener;

/**
 * @author John Ericksen
 */
public class UIThreadEventListenerDecorator<T> implements EventListener<T> {

    protected EventListener<T> eventListener;
    protected Handler handler;

    public UIThreadEventListenerDecorator(EventListener<T> eventListener) {
        this.eventListener = eventListener;
        this.handler = new Handler();
    }

    public void onEvent(T event) {
        handler.post(new EventFireRunnable<T>(event, eventListener));
    }
}
