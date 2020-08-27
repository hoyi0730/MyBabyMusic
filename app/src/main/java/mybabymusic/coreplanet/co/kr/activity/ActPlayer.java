package mybabymusic.coreplanet.co.kr.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import mybabymusic.coreplanet.co.kr.R;
import mybabymusic.coreplanet.co.kr.databinding.LayoutPlayerBinding;
import mybabymusic.coreplanet.co.kr.service.NotiService;
import mybabymusic.coreplanet.co.kr.utils.HoUtils;
import mybabymusic.coreplanet.co.kr.utils.NofiticationCenter;

public class ActPlayer extends Activity implements View.OnClickListener {
    LayoutPlayerBinding binding;
    Activity act;
    private static MediaPlayer mediaPlayer;
    private static ActPlayer instance;
    public static boolean playin;
    protected NofiticationCenter nofiticationCenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.layout_player);
        setLayout();
    }

    public void setLayout(){
        act = this;
        nofiticationCenter = new NofiticationCenter();
        if(instance!=null){
            binding.tvSongArtist.setText(instance.binding.tvSongArtist.getText().toString());
            binding.tvSongTitle.setText(instance.binding.tvSongTitle.getText().toString());
            binding.currentTime.setText(instance.binding.currentTime.getText().toString());
            binding.totalTime.setText(instance.binding.totalTime.getText().toString());
            initiateSeekBar(binding.seek);
        }else{
            if(mediaPlayer!=null){
                mediaPlayer.stop();
            }
            instance = this;
            initPlayer(0);
        }
        binding.btnNext.setOnClickListener(this);
        binding.btnPlay.setOnClickListener(this);
        binding.btnPre.setOnClickListener(this);
        binding.btnRandom.setOnClickListener(this);
        binding.btnSuffle.setOnClickListener(this);

    }

    public static void initiateSeekBar(SeekBar seekBar) {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress * 1000);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void play() {

        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            playin = true;
            mediaPlayer.start();
            /*Intent startIntent = new Intent(act, NotiService.class);
            startIntent.setAction("com.mypackage.ACTION_PAUSE_MUSIC");
            if(Build.VERSION.SDK_INT>=26){
                act.startForegroundService(startIntent);
            }else{
                act.startService(startIntent);
            }*/
            //pause.setBackgroundResource(R.drawable.pause_24dp);
            ActMain.getInstance().sendOnChannel("음악", "아티스트", 0);
            //ActMain.imageView.setBackgroundResource(R.drawable.pause_24dp);
        } else {
            pause();
        }
    }

    public void pause() {
        if (mediaPlayer.isPlaying()) {
            playin = false;
            mediaPlayer.pause();
            //pause.setBackgroundResource(R.drawable.play_arrow_24dp);
            ActMain.getInstance().sendOnChannel("음악", "아티스트", 0);
            //ActMain.imageView.setBackgroundResource(R.drawable.play_arrow_24dp);
        }

    }

    public void initPlayer(final int position) {

        playin = true;
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.reset();
        }
        //String name = Asongs.get(position).getName();
        //String artist = Asongs.get(position).getArtist();
        //setData(position);
        ActMain.getInstance().sendOnChannel("음악", "아티스트", position);


        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.marry_widow); // create and load mediaplayer with song resources
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                String totalTime = createTimeLabel(mediaPlayer.getDuration());
                binding.totalTime.setText(totalTime);
                binding.seek.setMax(mediaPlayer.getDuration() / 1000);
                mediaPlayer.start();
                //binding.btnPlay.setBackgroundResource(R.drawable.iv_noti_pause);

            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.d(HoUtils.TAG,"재생끝");
                /*int curSongPoition = position;
                curSongPoition = (curSongPoition + 1) % (Asongs.size());
                initPlayer(curSongPoition);*/

            }
        });
        initiateSeekBar(binding.seek);


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mediaPlayer != null) {
                    try {
                        if (mediaPlayer.isPlaying()) {
                            Message msg = new Message();
                            msg.what = mediaPlayer.getCurrentPosition();
                            msg.arg1 = mediaPlayer.getDuration();
                            handler.sendMessage(msg);
                            Thread.sleep(1000);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int current_position = msg.what;
            binding.seek.setMax(mediaPlayer.getDuration() / 1000);
            binding.seek.setProgress(current_position / 1000);
            System.out.println(binding.seek.getProgress());
            String cTime = createTimeLabel(current_position);
            binding.currentTime.setText(cTime);
            binding.totalTime.setText(createTimeLabel(msg.arg1));
        }
    };

    public String createTimeLabel(int duration) {
        String timeLabel = "";
        int min = duration / 1000 / 60;
        int sec = duration / 1000 % 60;

        timeLabel += min + ":";
        if (sec < 10) timeLabel += "0";
        timeLabel += sec;

        return timeLabel;


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_next:
                break;
            case R.id.btn_play:
                play();
                break;
            case R.id.btn_pre:
                break;
            case R.id.btn_random:
                break;
            case R.id.btn_suffle:
                break;
        }
    }

    public static ActPlayer getInstance() {
        return instance;
    }

}
