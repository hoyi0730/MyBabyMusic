package mybabymusic.coreplanet.co.kr.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import mybabymusic.coreplanet.co.kr.R;
import mybabymusic.coreplanet.co.kr.adapter.MusicListAdapter;
import mybabymusic.coreplanet.co.kr.data.Song;
import mybabymusic.coreplanet.co.kr.databinding.LayoutMusicListBinding;
import mybabymusic.coreplanet.co.kr.databinding.LayoutPlayerBinding;

public class ActMusicList extends Activity implements View.OnClickListener {
    LayoutMusicListBinding binding;
    Activity act;
    LinearLayoutManager lManager;
    RecyclerView.Adapter mAdapter;
    ArrayList<Song> songs;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.layout_music_list);
        setLayout();
    }

    public void setLayout(){
        act = this;
        songs = new ArrayList<>();
        binding.rcvList.setHasFixedSize(true);
        lManager = new LinearLayoutManager(act);
        mAdapter = new MusicListAdapter(songs,act);
        binding.rcvList.setAdapter(mAdapter);

    }

    @Override
    public void onClick(View v) {

    }

}
