package mybabymusic.coreplanet.co.kr.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by POINBEE-Android2 on 2017-02-27.
 */

public class AppUserData {

    public static SharedPreferences mShared;

    public static void setData(Context context, String dataKey, String data) {
        //SharedPreferences.Editor prefs = context.getSharedPreferences("pref", MODE_PRIVATE).edit();
        SharedPreferences.Editor prefs = PreferenceManager.getDefaultSharedPreferences(context).edit();
        prefs.putString(dataKey, data);
        prefs.commit();
    }

    // dataKey에 해당되는 데이터를 반환(String)
    public static String getData(Context context, String dataKey) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        //SharedPreferences pref = context.getSharedPreferences("pref", MODE_PRIVATE);
        return pref.getString(dataKey, "");
    }

}
