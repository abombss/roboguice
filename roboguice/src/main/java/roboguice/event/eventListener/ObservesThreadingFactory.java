package roboguice.event.eventListener;

import com.google.inject.Inject;
import roboguice.event.EventListener;

/**
 * @author John Ericksen
 */
public class ObservesThreadingFactory {

    @Inject
    protected UIThreadEventListenerDecoratorFactory uiThreadFactory;
    @Inject
    protected AsynchronousEventListenerDecoratorFactory asyncFactory;

    public <T> EventListener<T> buildMethodObserver(int threadType, EventListener<T> eventListener){
        switch (threadType){
            case ObservesThreading.CURRENT_THREAD:
                return eventListener;
            case ObservesThreading.UI_THREAD:
                return uiThreadFactory.buildDecorator(eventListener);
            case ObservesThreading.ASYNCHRONOUS:
                return asyncFactory.buildDecorator(eventListener);
            default:
                return eventListener;
        }
    }
}
