package mybabymusic.coreplanet.co.kr.adapter;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import mybabymusic.coreplanet.co.kr.R;
import mybabymusic.coreplanet.co.kr.data.Song;
import mybabymusic.coreplanet.co.kr.databinding.RecyclerMusicListBinding;

public class MusicListAdapter extends RecyclerView.Adapter implements Filterable {
    private ArrayList<Song> song;
    private Context ctx;

    public class MainHoder extends RecyclerView.ViewHolder {
        RecyclerMusicListBinding binding;
        public MainHoder(RecyclerMusicListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


    public MusicListAdapter(ArrayList<Song> historyData, Context con) {
        this.ctx = con;
        this.song = historyData;
        //Log.d(CommonUtil.TAG,"확인 : "+storeItemData);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vhItem;
        RecyclerMusicListBinding mainBinding = RecyclerMusicListBinding.inflate(LayoutInflater.from(ctx), parent, false);
        vhItem = new MusicListAdapter.MainHoder(mainBinding);
        return vhItem;
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        MusicListAdapter.MainHoder mainHoder = (MusicListAdapter.MainHoder) holder;
        final RecyclerMusicListBinding binding = mainHoder.binding;
        if (position == 0) {
            binding.textViewSongTitle.setText(song.get(position).getName());
            binding.textViewArtistName.setText(song.get(position).getArtist());

            Glide
                    .with(ctx)
                    .load(R.mipmap.ic_launcher)
                    .apply(new RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true)
                    )
                    .thumbnail(0.1f)
                    .transition(new DrawableTransitionOptions()
                            .crossFade()
                    )
                    .into(binding.imageView);

        } else {

            binding.textViewSongTitle.setText(song.get(position).getName());
            binding.textViewArtistName.setText(song.get(position).getArtist());
            /*Song song = songs.get(i);
            viewHolder.textview1.setX(viewHolder.textview1.getX());
            viewHolder.textview1.setY(viewHolder.textview1.getY());
            viewHolder.textview1.setTextSize(18);
            viewHolder.textview1.setText(song.getName());
            viewHolder.textView2.setText(song.getArtist());
            viewHolder.textView2.setX(viewHolder.textView2.getX());
            viewHolder.textview1.setTextColor(Color.parseColor("#E7DBDB"));*/
            try {

                Glide
                        .with(ctx)
                        .load(ContentUris.withAppendedId(Uri.parse("content://media/external/audio/albumart"), song.get(position).getAlbumID()).toString())
                        .apply(new RequestOptions()
                                .placeholder(R.mipmap.ic_launcher)
                                .diskCacheStrategy(DiskCacheStrategy.NONE)
                                .skipMemoryCache(true)
                        )
                        .thumbnail(0.1f)
                        .transition(new DrawableTransitionOptions()
                                .crossFade()
                        )
                        .into(binding.imageView);
                return;
            } catch (Exception e) {
            }
        }
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Song> filteredList = new ArrayList<>();
            /*if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(allSongs);
            } else {
                String filterpattern = constraint.toString().toLowerCase().trim();

                for (Song oneSong : allSongs) {
                    if (oneSong.getName().toLowerCase().startsWith(filterpattern) || oneSong.getArtist().toLowerCase().startsWith(filterpattern)) {
                        filteredList.add(oneSong);
                    }
                }

            }*/
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            /*songs.clear();
            songs.add(new Song("shufflee"));
            songs.addAll((List) results.values);
            notifyDataSetChanged();*/
        }
    };


    @Override
    public int getItemCount() {
        return song.size();
    }

    public void setItem(ArrayList<Song> userDatas){
        if(song.size()!=0){
            for(int i=song.size();i<userDatas.size();i++){
                song.add(i,userDatas.get(i));
            }
        }else{
            song.addAll(userDatas);
        }
        notifyDataSetChanged();
    }

    //여기 부터
    public Song getItem(int pos) {
        return song.get(pos);
    }

    public void addItem(ArrayList<Song> carBrands){
        song.addAll(carBrands);
        notifyDataSetChanged();
    }

    public void deleteAll(){
        song.clear();
        notifyDataSetChanged();
    }

    public void deleteItem(int pos){
        song.remove(pos);
    }

}