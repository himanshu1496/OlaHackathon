package himanshu.creative.com.ola_hack;

import android.app.Application;
import android.content.Context;

public class OlaHackApplication extends Application {
    private static Context mContext;

    public static Context getAppContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }
}
