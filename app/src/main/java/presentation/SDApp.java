package presentation;

import android.app.Application;
import android.content.Context;


public class SDApp extends Application {

    private static SDApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public static SDApp get(Context context) {
        return (SDApp) context.getApplicationContext();
    }



    public static synchronized SDApp getInstance() {
        return mInstance;
    }




}
