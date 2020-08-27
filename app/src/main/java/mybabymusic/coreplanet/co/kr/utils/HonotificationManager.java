package mybabymusic.coreplanet.co.kr.utils;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.annotation.StringDef;

import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import mybabymusic.coreplanet.co.kr.R;
import mybabymusic.coreplanet.co.kr.activity.ActPlayer;
import mybabymusic.coreplanet.co.kr.service.NotiService;


public class HonotificationManager {

    private static final String GROUP_TED_PARK = "MyBabyMusic";

    @TargetApi(Build.VERSION_CODES.O)
    public static void createChannel(Context context) {
        NotificationChannelGroup group1 = new NotificationChannelGroup(GROUP_TED_PARK, GROUP_TED_PARK);
        getManager(context).createNotificationChannelGroup(group1);

        NotificationChannel noticeNoti = new NotificationChannel(Channel.NOTIFICATION_NOTICE,
                "공지사항", android.app.NotificationManager.IMPORTANCE_HIGH);
        noticeNoti.setDescription(context.getString(R.string.app_name));
        noticeNoti.setGroup(GROUP_TED_PARK);
        noticeNoti.setLightColor(Color.GREEN);
        noticeNoti.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        getManager(context).createNotificationChannel(noticeNoti);

        NotificationChannel profilelike = new NotificationChannel(Channel.NOTIFICATION_MUSIC,
                "태교음악", NotificationManager.IMPORTANCE_DEFAULT);
        profilelike.setDescription(context.getString(R.string.app_name));
        profilelike.setGroup(GROUP_TED_PARK);
        profilelike.setLightColor(Color.GREEN);
        profilelike.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        getManager(context).createNotificationChannel(profilelike);

        NotificationChannel channelPost = new NotificationChannel(Channel.NOTIFICATION_AD_POST,
                "광고알림", android.app.NotificationManager.IMPORTANCE_DEFAULT);
        channelPost.setDescription("광고알림을 받습니다");
        channelPost.setGroup(GROUP_TED_PARK);
        channelPost.setLightColor(Color.RED);
        channelPost.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        getManager(context).createNotificationChannel(channelPost);

    }

    private static android.app.NotificationManager getManager(Context context) {
        return (android.app.NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @TargetApi(Build.VERSION_CODES.O)
    public static void deleteChannel(Context context, @Channel String channel) {
        getManager(context).deleteNotificationChannel(channel);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void sendImgNotification(Context context, int id, @HonotificationManager.Channel String channel, String title, String body, PendingIntent intent, String img) {
        Notification.Builder builder = new Notification.Builder(context, channel)
                .setVibrate(new long[]{0, 100, 100, 100, 100, 100})
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(getSmallIcon())
                .setContentIntent(intent)
                .setLargeIcon(getBitmap(JsonUrl.DEFAULT_HTTP_ADDRESS+img))
                .setAutoCancel(true);
        getManager(context).notify(id, builder.build());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void sendTextNotification(Context context, int id, @HonotificationManager.Channel String channel, String title, String body, PendingIntent intent) {
        Notification.Builder builder = new Notification.Builder(context, channel)
                .setVibrate(new long[]{0, 100, 100, 100, 100, 100})
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(getSmallIcon())
                .setContentIntent(intent)
                .setAutoCancel(true);
        getManager(context).notify(id, builder.build());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void NotificationMusic(Context context, int id, @HonotificationManager.Channel String channel, String title, String body, PendingIntent intent) {

        int plaorpa;
        if(ActPlayer.playin){
            plaorpa=R.drawable.iv_noti_pause;
        }else{
            plaorpa=R.drawable.iv_noti_play;
        }

        Notification.Builder builder = new Notification.Builder(context, channel)
                .setVibrate(new long[]{0, 100, 100, 100, 100, 100})
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(getSmallIcon())
                .addAction(R.drawable.iv_noti_previous, "Previous", playbackAction(3,context))
                .addAction(plaorpa, "Pause", playbackAction(1,context))
                .addAction(R.drawable.iv_noti_next, "Next", playbackAction(2,context))
                .setContentIntent(intent)
                .setAutoCancel(true);

        getManager(context).notify(id, builder.build());
    }


    private static Bitmap getBitmap(String url) {
        URL imgUrl = null;
        HttpsURLConnection connection = null;
        InputStream is = null;

        Bitmap retBitmap = null;

        try {
            imgUrl = new URL(url);
            connection = (HttpsURLConnection) imgUrl.openConnection();
            connection.setDoInput(true);
            connection.connect();
            is = connection.getInputStream();
            Bitmap tmp = BitmapFactory.decodeStream(is);
            retBitmap = Bitmap.createScaledBitmap(tmp, tmp.getWidth(), tmp.getHeight(),true);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            return retBitmap;
        }
    }

    private static int getSmallIcon() {
        return R.mipmap.ic_launcher;
        //return R.drawable.icon_140;
        //return R.drawable.icon512;
    }

    private static PendingIntent playbackAction(int actionNumber,Context context) {
        Intent playbackAction = new Intent(context, NotiService.class);
        switch (actionNumber) {
            case 1:
                // Pause
                playbackAction.setAction("com.mypackage.ACTION_PAUSE_MUSIC");
                return PendingIntent.getService(context, actionNumber, playbackAction, 0);
            case 2:
                // Next track
                playbackAction.setAction("com.mypackage.ACTION_NEXT_MUSIC");
                return PendingIntent.getService(context, actionNumber, playbackAction, 0);
            case 3:
                // Previous track
                playbackAction.setAction("com.mypackage.ACTION_PREV_MUSIC");
                return PendingIntent.getService(context, actionNumber, playbackAction, 0);
            default:
                break;
        }
        return null;
    }

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({
            Channel.NOTIFICATION_NOTICE,
            Channel.NOTIFICATION_MUSIC,
            Channel.NOTIFICATION_AD_POST



    })
    public @interface Channel {
        String NOTIFICATION_NOTICE = "공지사항";
        String NOTIFICATION_MUSIC = "태교음악";
        String NOTIFICATION_AD_POST = "광고알림";

    }

}
