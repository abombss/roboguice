package roboguice.event.eventListener;

import roboguice.event.EventListener;

/**
 * @author John Ericksen
 */
public class AsynchronousEventListenerDecoratorFactory {
    
    public <T> EventListener<T> buildDecorator(EventListener<T> eventListener) {
        return new AsynchronousEventListenerDecorator<T>(eventListener);
    }
}
