package roboguice.event.eventListener;

import com.google.inject.Inject;
import roboguice.event.EventListener;

/**
 * @author John Ericksen
 */
public class RunnableAsyncTaskAdaptorFactory {

    @Inject
    protected EventFireRunnableFactory runnableFactory;

    public <T> RunnableAsyncTaskAdaptor build(T event, EventListener<T> eventListener) {
        return new RunnableAsyncTaskAdaptor(runnableFactory.build(event, eventListener));
    }
}
