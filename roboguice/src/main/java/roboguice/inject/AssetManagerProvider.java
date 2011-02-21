package roboguice.inject;

import android.app.Application;
import android.content.res.AssetManager;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class AssetManagerProvider implements Provider<AssetManager> {
    @Inject protected Application application;

    public AssetManager get() {
        return application.getAssets();
    }
}
