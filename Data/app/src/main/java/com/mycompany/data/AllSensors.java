package com.mycompany.data;

import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;


public class AllSensors extends ActionBarActivity {

    Button start,stop,save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_sensors);

        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);
        save = (Button) findViewById(R.id.save);

        Typeface scribble = Typeface.createFromAsset(getAssets(), "fonts/warning.otf");

        start.setTypeface(scribble);
        start.setTextSize(80);

        stop.setTypeface(scribble);
        stop.setTextSize(80);

        save.setTypeface(scribble);
        save.setTextSize(80);


    }


}
