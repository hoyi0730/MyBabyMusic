package mybabymusic.coreplanet.co.kr.data;

import android.graphics.Bitmap;

import lombok.Data;

@Data
public class Song {
    private String name ,album, artist,path,AlbumArt;
    private long albumID;
    private int im;
    private Bitmap songImage;
}
