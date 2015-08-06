package com.mycompany.data;

import android.content.Context;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class AllSensors extends ActionBarActivity implements SensorEventListener{


    Long startTime;
    private float ax,ay,az,gx,gy,gz,mx,my,mz;
    Button start,stop,save;

    private SensorManager sensorManager;

    private Sensor accelerometer;
    private Sensor gyroscope;
    private Sensor magnetometer;


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

    public void saveData(View view) {
        Toast.makeText(AllSensors.this,"You have saved the sensors data!",Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
