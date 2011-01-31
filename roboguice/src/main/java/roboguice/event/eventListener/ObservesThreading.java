package roboguice.event.eventListener;

import roboguice.event.EventListener;

/**
 * @author John Ericksen
 */
public class ObservesThreading {

    public static final int CURRENT_THREAD = 0;
    public static final int UI_THREAD = 1;
    public static final int ASYNCHRONOUS = 2;

    public static <T> EventListener<T> buildMethodObserver(int threadType, EventListener<T> eventListener){
        switch (threadType){
            case CURRENT_THREAD:
                return eventListener;
            case UI_THREAD:
                return new UIThreadEventListenerDecorator<T>(eventListener);
            case ASYNCHRONOUS:
                return new AsynchronousEventListenerDecorator<T>(eventListener);
            default:
                return eventListener;
        }
    }
}
