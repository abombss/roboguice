package roboguice.util;

import android.os.Handler;

import java.util.concurrent.Executor;

/**
 * Allows injection to happen for tasks that execute in a background thread.
 * 
 * @param <ResultT>
 */
public abstract class RoboAsyncTask<ResultT> extends SafeAsyncTask<ResultT> {

    protected RoboAsyncTask() {
    }

    protected RoboAsyncTask(Handler handler) {
        super(handler);
    }

    protected RoboAsyncTask(Handler handler, Executor executor) {
        super(handler, executor);
    }

    protected RoboAsyncTask(Executor executor) {
        super(executor);
    }

    @Override
    protected Task<ResultT> newTask() {
        return new RoboTask<ResultT>(this);
    }

    protected class RoboTask<ResultT> extends SafeAsyncTask.Task<ResultT> {
        public RoboTask(SafeAsyncTask parent) {
            super(parent);
        }

        @Override
        protected ResultT doCall() throws Exception {
            return super.doCall();
        }
    }
}
