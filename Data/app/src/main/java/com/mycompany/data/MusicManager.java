package com.mycompany.data;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by xingze on 15/8/3.
 */
public class MusicManager {

    private static MediaPlayer mediaPlayer;

    public static void start(Context context) {

        mediaPlayer = MediaPlayer.create(context,R.raw.summer);

        if(mediaPlayer != null){
            if(!mediaPlayer.isPlaying()){
                //mediaPlayer.start();
            }
            mediaPlayer.setLooping(true);
        }
    }

    public static void pause() {
        mediaPlayer.pause();
    }

    public static void release() {
        try{
            if(mediaPlayer != null) {
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                mediaPlayer.release();
            }
        }catch (Exception e) {

        }
    }

}
