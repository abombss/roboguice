package roboguice.event.eventListener;

import com.google.inject.Inject;
import roboguice.event.EventListener;

/**
 * @author John Ericksen
 */
public class AsynchronousEventListenerDecoratorFactory {

    @Inject
    protected RunnableAsyncTaskAdaptorFactory taskFactory;
    
    public <T> EventListener<T> buildDecorator(EventListener<T> eventListener) {
        return new AsynchronousEventListenerDecorator<T>(eventListener, taskFactory);
    }
}
