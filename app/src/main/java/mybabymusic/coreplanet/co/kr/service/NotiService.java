package mybabymusic.coreplanet.co.kr.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import mybabymusic.coreplanet.co.kr.activity.ActMain;
import mybabymusic.coreplanet.co.kr.activity.ActPlayer;
import mybabymusic.coreplanet.co.kr.utils.HoUtils;

public class NotiService extends Service {
    IBinder mBinder = new LocalBinder();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String input = intent.getStringExtra("inputExtra");
        Intent notificationIntent = new Intent(this, ActMain.class);

        handleIncomingActions(intent);

        return START_NOT_STICKY;
    }
    private void handleIncomingActions(Intent playbackAction) {
        if (playbackAction == null || playbackAction.getAction() == null) return;
        //int pos= PlayerActivity.getInstance().getPosition();

        String actionString = playbackAction.getAction();

        if (actionString.equalsIgnoreCase("com.mypackage.ACTION_PAUSE_MUSIC")) {
            Log.d(HoUtils.TAG,"결과값 : "+ActPlayer.playin+" 상태 : ");
            if( ActPlayer.playin){
                ActPlayer.getInstance().pause();
            }
            else{
                ActPlayer.getInstance().play();
            }
        } /*else if (actionString.equalsIgnoreCase("com.mypackage.ACTION_NEXT_MUSIC")) {
            if(SongAdapter.songs.get((pos+1)% SongAdapter.songs.size()).getName().equals("shufflee"))pos++;
            PlayerActivity.getInstance().initPlayer(((pos+1)% SongAdapter.songs.size()));
            PlayerActivity.getInstance().setPosition(((pos+1)% SongAdapter.songs.size()));
        } else if (actionString.equalsIgnoreCase("com.mypackage.ACTION_PREV_MUSIC")) {
            if(SongAdapter.songs.get((pos+1)% SongAdapter.songs.size()).getName().equals("shufflee"))pos--;
            if(pos<=0)pos=SongAdapter.songs.size()-1;
            else pos-=1;
            PlayerActivity.getInstance().setPosition(pos);
            PlayerActivity.getInstance().initPlayer((pos));
        }*/
    }

    class LocalBinder extends Binder {
        NotiService getService() {
            return NotiService.this;
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }



}
