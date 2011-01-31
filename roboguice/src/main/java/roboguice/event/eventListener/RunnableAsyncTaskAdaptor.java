package roboguice.event.eventListener;

import roboguice.util.SafeAsyncTask;

/**
* @author John Ericksen
*/
class RunnableAsyncTaskAdaptor<T> extends SafeAsyncTask<Void> {

    protected Runnable runnable;

    public RunnableAsyncTaskAdaptor(Runnable runnable) {
        this.runnable = runnable;
    }

    public Void call() throws Exception {
        runnable.run();
        return null;
    }
}
