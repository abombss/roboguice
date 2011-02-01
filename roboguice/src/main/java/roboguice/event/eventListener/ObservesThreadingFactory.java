package roboguice.event.eventListener;

import com.google.inject.Inject;
import roboguice.event.EventListener;
import roboguice.event.EventThread;

/**
 * @author John Ericksen
 */
public class ObservesThreadingFactory {

    @Inject
    protected UIThreadEventListenerDecoratorFactory uiThreadFactory;
    @Inject
    protected AsynchronousEventListenerDecoratorFactory asyncFactory;

    public <T> EventListener<T> buildMethodObserver(EventThread threadType, EventListener<T> eventListener){
        switch (threadType){
            case CURRENT:
                return eventListener;
            case UI:
                return uiThreadFactory.buildDecorator(eventListener);
            case ASYNCHRONOUS:
                return asyncFactory.buildDecorator(eventListener);
            default:
                return eventListener;
        }
    }
}
