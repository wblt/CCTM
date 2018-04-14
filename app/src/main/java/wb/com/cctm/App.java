package wb.com.cctm;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.tencent.bugly.crashreport.CrashReport;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by wb on 2018/4/12.
 */

public class App extends Application {
    private List<Activity> mList = new LinkedList<>();
    private static App instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        // 腾讯bugly
        CrashReport.initCrashReport(getApplicationContext(), "a92ccabdd7", true);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static App getInstance() {
        return instance;
    }

    public void addActivity(Activity activity){
        mList.add(activity);
    }

    public void closeActivitys() {
        try {
            for (Activity activity : mList) {
                if (activity != null)
                    activity.finish();
            }
            mList.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
