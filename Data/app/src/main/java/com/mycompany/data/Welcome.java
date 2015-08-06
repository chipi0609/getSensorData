package com.mycompany.data;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;


public class Welcome extends ActionBarActivity {

    // initially the App is not playing music
    private boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }


    @Override
    protected void onResume() {
        super.onResume();

        // if music is not playing, play the music
        if(!isPlaying){
            MusicManager.start(this);
            isPlaying = true;
        }

    }

    // onClick method for button start, which would start the main activity
    public void sendMessage(View view) {
        Button start = (Button) findViewById(R.id.start);

        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }


    // onClick method for button music, which would alternate according to the button state
    public void musicPlay(View view) {
        Button music = (Button) findViewById(R.id.music);
        if(isPlaying) {
            music.setPressed(true);
            MusicManager.release();
        }else{
            music.setPressed(false);
            MusicManager.start(this);
        }
        isPlaying = !isPlaying;
    }
}
