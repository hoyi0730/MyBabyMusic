package mybabymusic.coreplanet.co.kr.activity;

import android.app.Application;
import android.os.Build;

import mybabymusic.coreplanet.co.kr.utils.HonotificationManager;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            HonotificationManager.createChannel(this);
        }
    }
}
