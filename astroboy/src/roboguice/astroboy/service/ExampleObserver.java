package roboguice.astroboy.service;

import android.content.Context;
import com.google.inject.Inject;
import roboguice.activity.event.OnContentViewAvailableEvent;
import roboguice.activity.event.OnDestroyEvent;
import roboguice.event.Observes;
import roboguice.util.Ln;

/**
 * Example of the @Observes usage with the defined RoboActivity Events
 *
 * @author John Ericksen
 */
public class ExampleObserver {

    @Inject protected Context context;

    public void logOnCreate(@Observes OnContentViewAvailableEvent event) {
        Ln.v("onCreate");
    }

    public void logOnDestroy(@Observes OnDestroyEvent event){
        Ln.v("onDestroy");
    }
}
