package roboguice.activity.event;

import android.content.Intent;

/**
 * Class representing the event raised by RoboActivity.onActivityResult()
 *
 * @author Adam Tybor
 * @author John Ericksen
 */
public class OnActivityResultEvent {
    
        protected final int requestCode;
        protected final int resultCode;
        protected final Intent data;

        public OnActivityResultEvent(int requestCode, int resultCode, Intent data) {
            this.requestCode = requestCode;
            this.resultCode = resultCode;
            this.data = data;
        }

        public int getRequestCode() {
            return requestCode;
        }

        public int getResultCode() {
            return resultCode;
        }

        public Intent getData() {
            return data;
        }
    }