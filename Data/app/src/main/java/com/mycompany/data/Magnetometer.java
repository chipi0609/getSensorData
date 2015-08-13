package com.mycompany.data;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class Magnetometer extends ActionBarActivity implements SensorEventListener{

    private float x,y,z;
    private float dx = 0,dy = 0,dz = 0;
    private long currentMillis;

    private String content = "Timestamp(ms)   x              y              z          " + "\r\n";

    private SensorManager sensorManager;
    private Sensor magnetometer;

    private TextView currentX,currentY,currentZ,currentTime;

    private boolean isPlaying = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magnetometer);

        currentX = (TextView) findViewById(R.id.currentX);
        currentY = (TextView) findViewById(R.id.currentY);
        currentZ = (TextView) findViewById(R.id.currentZ);
        currentTime = (TextView) findViewById(R.id.currentTime);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if(sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) !=null ){
            magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
            sensorManager.registerListener(this,magnetometer,SensorManager.SENSOR_DELAY_NORMAL);
        }

        currentMillis = System.currentTimeMillis();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_NORMAL);
        if(!isPlaying) {
            MusicManager.start(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
        isPlaying = true;

        File file;
        FileOutputStream fileOutputStream;
        String filename = AllSensors.parse() + "_Mag.txt";


        try{
            file = new File(Environment.getExternalStorageDirectory(), filename);

            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(content.getBytes());
            fileOutputStream.close();

        }catch(IOException e) {
            Toast.makeText(this, "An error occurred!", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float timeNow = (System.currentTimeMillis()-currentMillis)/100;

        dx = event.values[0];
        dy = event.values[1];
        dz = event.values[2];

        content += (System.currentTimeMillis()-currentMillis) + "          "+ dx + "    " + dy + "    " + dz + "    " + "\r\n";

        currentX.setText(Float.toString(dx));
        currentY.setText(Float.toString(dy));
        currentZ.setText(Float.toString(dz));
        currentTime.setText(Float.toString(timeNow));


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
