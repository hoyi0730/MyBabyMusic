package mybabymusic.coreplanet.co.kr.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.session.MediaSession;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat;
import android.util.Log;
import android.view.View;

import mybabymusic.coreplanet.co.kr.R;
import mybabymusic.coreplanet.co.kr.databinding.ActivityMainBinding;
import mybabymusic.coreplanet.co.kr.databinding.LayoutMusicListBinding;
import mybabymusic.coreplanet.co.kr.service.NotiService;
import mybabymusic.coreplanet.co.kr.utils.HoUtils;

import static mybabymusic.coreplanet.co.kr.utils.NofiticationCenter.channel_1_ID;

public class ActMain extends AppCompatActivity {
    ActivityMainBinding binding;
    Activity act;
    protected static ActMain instance;
    public static Notification notification;
    MediaMetadataRetriever metadataRetriever;
    private MediaSessionCompat mediaSession;
    NotificationManagerCompat notificationManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setLayout();
    }

    public void setLayout(){
        act = this;
        instance = this;
        mediaSession = new MediaSessionCompat(this, "tag");
        notificationManager = NotificationManagerCompat.from(this);
        binding.tvHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(act,ActPlayer.class);
                startActivity(intent);
            }
        });
    }

    public static ActMain getInstance() {
        return instance;
    }

    public void sendOnChannel(String name,String artist,int position) {

        Intent activityIntent = new Intent(this, ActMain.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,
                0, activityIntent, 0);

        int plaorpa;
        if(ActPlayer.playin){
            plaorpa=R.drawable.iv_noti_pause;
        }else{
            plaorpa=R.drawable.iv_noti_play;
        }
        metadataRetriever = new MediaMetadataRetriever();
        //metadataRetriever.setDataSource(songs.get(position).getPath());
        metadataRetriever.setDataSource(act, Uri.parse("android.resource://mybabymusic.coreplanet.co.kr/"+R.raw.marry_widow));
        /*arts= metadataRetriever.getEmbeddedPicture();
        Bitmap artwork;
        try {
            artwork= BitmapFactory.decodeByteArray(arts,0,arts.length);
        }catch (Exception e){
            artwork = BitmapFactory.decodeResource(getResources(), R.drawable.track_2);
        }*/
        notification = new NotificationCompat.Builder(this, channel_1_ID)
                .setShowWhen(false)
                .setSmallIcon(R.drawable.noti_song)
                .setContentTitle(name)
                .setContentText("Song")
                //.setLargeIcon(artwork)
                .addAction(R.drawable.iv_noti_previous, "Previous", playbackAction(3))
                .addAction(plaorpa, "Pause", playbackAction(1))
                .addAction(R.drawable.iv_noti_next, "Next", playbackAction(2))
                .setContentIntent(contentIntent)
                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(0, 1, 2)
                        .setMediaSession(mediaSession.getSessionToken()))
                .setSubText(artist)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(false)
                .build();

        Log.d(HoUtils.TAG,"노티피케이션 호출");
        //startForeground(1337, notification);
        if(ActPlayer.playin){
            notification.flags = Notification.FLAG_NO_CLEAR;
        }else{
            notification.flags = 0;
        }

        notificationManager.notify(1, notification);
    }

    private PendingIntent playbackAction(int actionNumber) {
        Intent playbackAction = new Intent(this, NotiService.class);
        switch (actionNumber) {
            case 1:
                // Pause
                playbackAction.setAction("com.mypackage.ACTION_PAUSE_MUSIC");
                return PendingIntent.getService(this, actionNumber, playbackAction, 0);
            case 2:
                // Next track
                playbackAction.setAction("com.mypackage.ACTION_NEXT_MUSIC");
                return PendingIntent.getService(this, actionNumber, playbackAction, 0);
            case 3:
                // Previous track
                playbackAction.setAction("com.mypackage.ACTION_PREV_MUSIC");
                return PendingIntent.getService(this, actionNumber, playbackAction, 0);
            default:
                break;
        }
        return null;
    }


}
