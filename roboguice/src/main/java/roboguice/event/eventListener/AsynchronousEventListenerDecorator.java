package roboguice.event.eventListener;

import roboguice.event.EventListener;
import roboguice.util.SafeAsyncTask;

/**
 * @author John Ericksen
 */
public class AsynchronousEventListenerDecorator<T> implements EventListener<T>{

    protected EventListener<T> eventListener;

    public AsynchronousEventListenerDecorator(EventListener<T> eventListener) {
        this.eventListener = eventListener;
    }

    public void onEvent(T event) {
        new EventFireAsyncTask<T>(event, eventListener).execute();
    }

    protected class EventFireAsyncTask<T> extends SafeAsyncTask<Void>{

        protected EventFireRunnable runnable;

        public EventFireAsyncTask(T event, EventListener<T> eventListener) {
            runnable = new EventFireRunnable(event, eventListener);
        }

        public Void call() throws Exception {
            runnable.run();
            return null;
        }
    }
}
