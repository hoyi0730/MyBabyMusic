package mybabymusic.coreplanet.co.kr.service;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.TaskStackBuilder;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import mybabymusic.coreplanet.co.kr.R;
import mybabymusic.coreplanet.co.kr.activity.ActMain;
import mybabymusic.coreplanet.co.kr.utils.AppUserData;
import mybabymusic.coreplanet.co.kr.utils.HoUtils;
import mybabymusic.coreplanet.co.kr.utils.HonotificationManager;
import mybabymusic.coreplanet.co.kr.utils.JsonUrl;


/**
 * Created by Administrator on 2018-01-10.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";
    private static final String NOTIFICATION_CHANNEL_ID = "meshop";
    String type, adImg, yIdx;

    //UserData uData;
    String roomIdx, tName, tAge, tGender, tArea, tAreaDetail, tPhoto, tJob, tKm, notiTitle, notiSub, tIdx;
    String s_distance, s_gender, s_age, s_idx, s_msg, s_name;

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        Log.d(HoUtils.TAG, "새로운 토큰 : " + s);
    }

    public String getGender(String gender) {
        if (gender.equalsIgnoreCase("M")) {
            return "남";
        } else {
            return "여";
        }
    }

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            //{type=GONGSI} 푸쉬 로그
        }

        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        if (!HoUtils.isNull(AppUserData.getData(getApplication(), "idx"))) {
            type = remoteMessage.getData().get("type");
        }
        if (type.equalsIgnoreCase("like_profile")) {
            try {
                JSONObject profile = new JSONObject(remoteMessage.getData().get("msg_from"));
                notiTitle = "관심친구등록 알림";
                notiSub = HoUtils.getStr(profile, "name") + remoteMessage.getData().get("message");
                if (HoUtils.fixNull(AppUserData.getData(getApplication(), "push"), "on").equalsIgnoreCase("on")) {
                    sendNotification();
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
        } else if (type.equalsIgnoreCase("gift_send")) {
            try {
                JSONObject profile = new JSONObject(remoteMessage.getData().get("msg_from"));
                notiTitle = "별풍선 선물도착!";
                notiSub = HoUtils.getStr(profile, "name") + "님이 별풍선" + remoteMessage.getData().get("star") + "개를 선물하였습니다.";
                ActivityManager activityManager = (ActivityManager) getApplication().getSystemService(Context.ACTIVITY_SERVICE);
                List<ActivityManager.RunningTaskInfo> runningTaskInfos = activityManager.getRunningTasks(1);
                //Log.d(HoUtils.TAG, "채팅왔을때 액티비티이름 : " + runningTaskInfos.get(0).topActivity.getClassName());
                if (runningTaskInfos.get(0).topActivity.getClassName().contains("ActChatRtc")) {
                    Intent myIntent = new Intent("gift_send");
                    myIntent.putExtra("action", "gift_send");
                    myIntent.putExtra("star", remoteMessage.getData().get("star"));
                    myIntent.putExtra("name", HoUtils.getStr(profile, "name"));
                    this.sendBroadcast(myIntent);
                } else {
                    if (HoUtils.fixNull(AppUserData.getData(getApplication(), "push"), "on").equalsIgnoreCase("on")) {
                        sendNotification();
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (type.equalsIgnoreCase("accept_chat")) {
            roomIdx = remoteMessage.getData().get("room_idx");
            notiTitle = remoteMessage.getData().get("nick")+"님께서 채팅수락하셨습니다";
            notiSub = remoteMessage.getData().get("nick")+"님에게 첫메세지를 보내보내세요.";
            if (HoUtils.fixNull(AppUserData.getData(getApplication(), "push"), "on").equalsIgnoreCase("on")) {
                sendNotification();
            }
        } else if (type.equalsIgnoreCase("reject_chat")) {
            Handler mHandler = new Handler(Looper.getMainLooper());
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // 사용하고자 하는 코드
                    //Toast.makeText(getApplicationContext(), remoteMessage.getData().get("message").replaceAll("채팅", "영상채팅"), Toast.LENGTH_SHORT).show();
                }
            }, 0);

        } else if (type.equalsIgnoreCase("reject_face")) {
            Handler mHandler = new Handler(Looper.getMainLooper());
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // 사용하고자 하는 코드
                    //Toast.makeText(getApplicationContext(), remoteMessage.getData().get("message"), Toast.LENGTH_SHORT).show();
                }
            }, 0);
            ActivityManager activityManager = (ActivityManager) getApplication().getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> runningTaskInfos = activityManager.getRunningTasks(1);
            //Log.d(HoUtils.TAG, "채팅왔을때 액티비티이름 : " + runningTaskInfos.get(0).topActivity.getClassName());
            if (runningTaskInfos.get(0).topActivity.getClassName().contains("ActChatRtc")) {
                Intent myIntent = new Intent("reject_face");
                myIntent.putExtra("action", "reject_face");
                this.sendBroadcast(myIntent);
            }
        } else if (type.equalsIgnoreCase("chatting")) {
            roomIdx = remoteMessage.getData().get("room_idx");
            notiTitle = "메시지도착";
            if (remoteMessage.getData().get("message").contains("coreplanet.kr")) {
                notiSub = "이미지";
            } else {
                notiSub = HoUtils.decodeEmoji(remoteMessage.getData().get("message"));
            }
            //roomIdx = remoteMessage.getData().get("room_idx");
            ActivityManager activityManager = (ActivityManager) getApplication().getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> runningTaskInfos = activityManager.getRunningTasks(1);
            Log.d(HoUtils.TAG,"채팅 : "+runningTaskInfos.get(0).topActivity.getClassName());
            if (!runningTaskInfos.get(0).topActivity.getClassName().contains("ActChat")) {
                if(runningTaskInfos.get(0).topActivity.getClassName().contains("ActMain")){
                    Intent myIntent = new Intent("receive");
                    myIntent.putExtra("action", "receive");
                    this.sendBroadcast(myIntent);
                }
                if (HoUtils.fixNull(AppUserData.getData(getApplication(), "push"), "on").equalsIgnoreCase("on")) {
                    sendNotification();
                }
            }
        }
    }






    public boolean isAppOnForeground() {
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcessInfos = activityManager.getRunningAppProcesses();
        if (appProcessInfos == null) {
            return false;
        }
        final String packageName = getPackageName();
        for (ActivityManager.RunningAppProcessInfo appProcessInfo : appProcessInfos) {
            if (appProcessInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND && appProcessInfo.processName.equalsIgnoreCase(packageName)) {
                return true;
            }
        }
        return false;
    }

    public int createID() {
        Date now = new Date();
        int id = Integer.parseInt(new SimpleDateFormat("ddHHmmss", Locale.KOREA).format(now));

        return id;
    }

    public long milliseconds(String date) {
        //String date_ = date;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date mDate = sdf.parse(date);
            long timeInMilliseconds = mDate.getTime();
            //System.out.println("Date in milli :: " + timeInMilliseconds + " 날짜 : " + date);
            return timeInMilliseconds;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return 0;
    }


    private void sendNotification() {
        Intent targetIntent = null;
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        PendingIntent pIntent;
        if (HoUtils.isNull(type)) {
            targetIntent = new Intent(this, ActMain.class);
        } else {
            if (type.equalsIgnoreCase("text") || type.equalsIgnoreCase("img")) {
                //targetIntent = new Intent(this, ActChat.class);
                targetIntent.putExtra("roomIdx", tIdx);
                stackBuilder.addParentStack(ActMain.class);
                stackBuilder.addNextIntentWithParentStack(targetIntent);
            }  else if (type.equalsIgnoreCase("accept_chat")) {
                //targetIntent = new Intent(this, ActChat.class);
                targetIntent.putExtra("idx", roomIdx);
                stackBuilder.addParentStack(ActMain.class);
                stackBuilder.addNextIntentWithParentStack(targetIntent);
            }  else if (type.equalsIgnoreCase("chatting")) {
                //targetIntent = new Intent(this, ActChat.class);
                targetIntent.putExtra("idx", roomIdx);
                stackBuilder.addParentStack(ActMain.class);
                stackBuilder.addNextIntentWithParentStack(targetIntent);
            }  else if(type.equalsIgnoreCase("like_profile")){
                targetIntent = new Intent(this, ActMain.class);
                targetIntent.putExtra("type","second");
                stackBuilder.addParentStack(ActMain.class);
                stackBuilder.addNextIntentWithParentStack(targetIntent);
            } else {
                targetIntent = new Intent(this, ActMain.class);
                stackBuilder.addParentStack(ActMain.class);
                stackBuilder.addNextIntentWithParentStack(targetIntent);
            }
        }
        pIntent = stackBuilder.getPendingIntent(1, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Activity.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (type.equalsIgnoreCase("chatting")) {
                //HonotificationManager.sendTextNotification(this, createID(), HonotificationManager.Channel.NOTIFICATION_CHATTING, notiTitle, notiSub, pIntent);
            } else if (type.equalsIgnoreCase("request_chat")) {
                //HonotificationManager.sendTextNotification(this, createID(), HonotificationManager.Channel.NOTIFICATION_CHAT_REQUEST, notiTitle, notiSub, pIntent);
            } else if (type.equalsIgnoreCase("request_face")) {
                //HonotificationManager.sendTextNotification(this, createID(), HonotificationManager.Channel.NOTIFICATION_CHAT_FACE_REQUEST, notiTitle, notiSub, pIntent);
            } else if (type.equalsIgnoreCase("chat_arrive")) {
                //HonotificationManager.sendTextNotification(this, createID(), HonotificationManager.Channel.NOTIFICATION_CHAT_ARRIVE, notiTitle, notiSub, pIntent);
            } else if (type.equalsIgnoreCase("face_chat_arrive")) {
               // HonotificationManager.sendTextNotification(this, createID(), HonotificationManager.Channel.NOTIFICATION_CHAT_FACE_ARRIVE, notiTitle, notiSub, pIntent);
            } else if (type.equalsIgnoreCase("notice")) {
                //HonotificationManager.sendTextNotification(this, createID(), HonotificationManager.Channel.NOTIFICATION_NOTICE, notiTitle, notiSub, pIntent);
            } else if (type.equalsIgnoreCase("gift_send")||type.equalsIgnoreCase("gift_send2")) {
                //HonotificationManager.sendTextNotification(this, createID(), HonotificationManager.Channel.NOTIFICATION_STAR_GIFT, notiTitle, notiSub, pIntent);
            } else if (type.equalsIgnoreCase("accept_chat")) {
                //HonotificationManager.sendTextNotification(this, createID(), HonotificationManager.Channel.NOTIFICATION_CHAT_ACCEPT, notiTitle, notiSub, pIntent);
            } else if(type.equalsIgnoreCase("like_profile")){
                //HonotificationManager.sendTextNotification(this, createID(), HonotificationManager.Channel.NOTIFICATION_CHAT_ACCEPT, notiTitle, notiSub, pIntent);
            }
        } else {
            if (Build.VERSION.SDK_INT > 15) {
                //신버전(Notification.Builder) 사용 (API16부터 사용 가능)
                Notification.Builder mNoti = new Notification.Builder(this);
                mNoti.setTicker(notiSub);   //상단 상태표시줄에 잠깐 나오는 메세지
                mNoti.setSmallIcon(R.mipmap.ic_launcher);
                mNoti.setContentTitle(notiTitle);   //알림창 제목
                mNoti.setContentText(notiSub);  //알림 창 내용
                mNoti.setStyle(new Notification.BigTextStyle().bigText(notiSub));
                mNoti.setAutoCancel(true);
                mNoti.setContentIntent(pIntent);
                if (!HoUtils.isNull(adImg)) {
                    mNoti.setLargeIcon(getBitmap(JsonUrl.DEFAULT_HTTP_ADDRESS + adImg));
                }
                notificationManager.notify(createID(), mNoti.build());
            } else {
                //구버전(Notification 사용)
                Notification.Builder builder = new Notification.Builder(this)
                        .setContentIntent(pIntent)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(notiTitle)
                        .setContentText(notiSub)
                        .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);
                if (!HoUtils.isNull(adImg)) {
                    builder.setLargeIcon(getBitmap(JsonUrl.DEFAULT_HTTP_ADDRESS + adImg));
                }
                Notification notification = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    notification = builder.build();
                }
                notificationManager.notify(createID(), notification);
            }
        }
    }

    private Bitmap getBitmap(String url) {
        URL imgUrl = null;
        HttpURLConnection connection = null;
        InputStream is = null;

        Bitmap retBitmap = null;

        try {
            imgUrl = new URL(url);
            connection = (HttpURLConnection) imgUrl.openConnection();
            connection.setDoInput(true);
            connection.connect();
            is = connection.getInputStream();
            Bitmap tmp = BitmapFactory.decodeStream(is);
            retBitmap = Bitmap.createScaledBitmap(tmp, tmp.getWidth(), tmp.getHeight(), true);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            return retBitmap;
        }
    }

}