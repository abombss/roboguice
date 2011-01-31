package roboguice.event.eventListener;

import android.os.Handler;
import com.google.inject.Inject;
import roboguice.event.EventListener;

/**
 * @author John Ericksen
 */
public class UIThreadEventListenerDecoratorFactory {

    @Inject
    protected Handler handler;
    @Inject
    protected EventFireRunnableFactory eventFireFactory;

    public <T> EventListener<T> buildDecorator(EventListener<T> eventListener) {
        return new UIThreadEventListenerDecorator<T>(eventListener, handler, eventFireFactory);
    }
}
