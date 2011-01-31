package roboguice.event.eventListener;

import roboguice.event.EventListener;

/**
* @author John Ericksen
*/
public class EventFireRunnable<T> implements Runnable {

    protected T event;
    protected EventListener<T> eventListener;

    public EventFireRunnable(T event, EventListener<T> eventListener) {
        this.event = event;
        this.eventListener = eventListener;
    }

    public void run(){
        eventListener.onEvent(event);
    }
}
