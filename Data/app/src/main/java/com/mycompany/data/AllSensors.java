package com.mycompany.data;

import android.content.Context;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;


public class AllSensors extends ActionBarActivity implements SensorEventListener{


    Long startTime;
    private float ax,ay,az,gx,gy,gz,mx,my,mz;
    Button start,stop,save;

    private SensorManager sensorManager;

    private Sensor accelerometer;
    private Sensor gyroscope;
    private Sensor magnetometer;

    String content = "";


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

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // register all the sensors
        if(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) !=null ){
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
        }

        if(sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) !=null ){
            gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
            sensorManager.registerListener(this,gyroscope,SensorManager.SENSOR_DELAY_NORMAL);
        }

        if(sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) !=null ){
            magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
            sensorManager.registerListener(this,magnetometer,SensorManager.SENSOR_DELAY_NORMAL);
        }


    }

    public void start(View view) {
        startTime = System.currentTimeMillis();
    }

    public void stop(View view) {

    }

    // saves all the data collected
    public void saveData(View view) {
        String filename = parse() + "txt";
        File file;

        FileOutputStream fileOutputStream;

        String title = "Timestamp        Ax     Ay     Az     Mx     My     Mz     Gx     Gy     Gz   ";

        try{
            file = new File(Environment.getExternalStorageDirectory(),filename);

            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(title.getBytes());
            fileOutputStream.close();
        }catch (IOException e) {
            Toast.makeText(AllSensors.this,"An error occurred!",Toast.LENGTH_SHORT).show();
            return;
        }


        Toast.makeText(AllSensors.this,"You have saved the sensors data!",Toast.LENGTH_SHORT).show();
    }


    // this method would return a string of formatted date and time
    private String parse() {
        String result = "";

        Date date = new Date();
        String data = date.toString();
        String[] parts = data.split(" ");

        // add year
        result += parts[5] + parts[1] + parts[2];

        // now result would be "2015Aug06" for example
        String[] time = parts[3].split(":");
        result += "_" + time[0] + time[1] + time[2];

        // now result would be "2015Aug06_154513" for example, indicating the date and time
        return result;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;

        if(sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            ax = event.values[0];
            ay = event.values[1];
            az = event.values[2];

        } else if (sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            gx = event.values[0];
            gy = event.values[1];
            gz = event.values[2];
        } else if (sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            mx = event.values[0];
            my = event.values[1];
            mz = event.values[2];
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
