package roboguice.event.eventListener;

import roboguice.event.EventListener;

/**
 * @author John Ericksen
 */
public class UIThreadEventListenerDecoratorFactory {
    public <T> EventListener<T> buildDecorator(EventListener<T> eventListener) {
        return new UIThreadEventListenerDecorator<T>(eventListener);
    }
}
