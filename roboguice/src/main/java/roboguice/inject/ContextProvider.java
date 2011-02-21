package roboguice.inject;

import android.content.Context;
import com.google.inject.Provider;

/**
 * @author John Ericksen
 */
public class ContextProvider implements Provider<Context> {

    private Context context;

    public ContextProvider(Context context) {
        this.context = context;
    }

    @Override
    public Context get() {
        return context;
    }
}
