package roboguice.util;

/**
 * An extension to {@link Thread} which propogates the current
 * Context to the background thread.
 *
 * Current limitations:  any parameters set in the RoboThread are
 * ignored other than Runnable.  This means that priorities, groups,
 * names, etc. won't be honored. Yet.
 */
public class RoboThread extends Thread {

    public RoboThread() {
    }

    public RoboThread(Runnable runnable) {
        super(runnable);
    }

    @Override
    public void start() {

        // BUG any parameters set in the RoboThread are ignored other than Runnable.
        // This means that priorities, groups, names, etc. won't be honored. Yet.
        new Thread() {
            public void run() {
                RoboThread.this.run();
            }
        }.start();

    }
}
