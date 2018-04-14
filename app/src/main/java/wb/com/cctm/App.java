package wb.com.cctm;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by wb on 2018/4/12.
 */

public class App extends Application {

    private static App instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static App getInstance() {
        return instance;
    }


}
