package roboguice.event.eventListener;

import com.google.inject.Inject;
import roboguice.event.EventListener;

/**
 * @author John Ericksen
 */
public class EventFireAsyncTaskFactory {

    @Inject
    protected EventFireRunnableFactory runnableFactory;

    public <T> RunnableAsyncTaskAdaptor<T> build(T event, EventListener<T> eventListener) {
        return new RunnableAsyncTaskAdaptor<T>(runnableFactory.build(event, eventListener));
    }
}
