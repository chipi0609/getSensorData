package com.mycompany.data;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class Gyroscope extends ActionBarActivity implements SensorEventListener{

    private float dx = 0,dy = 0,dz = 0;
    private Long currentMillis;

    private SensorManager sensorManager;
    private Sensor gyroscope;

    private TextView currentX,currentY,currentZ,currentTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope);

        currentX = (TextView) findViewById(R.id.currentX);
        currentY = (TextView) findViewById(R.id.currentY);
        currentZ = (TextView) findViewById(R.id.currentZ);
        currentTime = (TextView) findViewById(R.id.currentTime);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if(sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) !=null ){
            gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
            sensorManager.registerListener(this,gyroscope,SensorManager.SENSOR_DELAY_NORMAL);
        }

        currentMillis = System.currentTimeMillis();

    }

    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        double timeNow = (System.currentTimeMillis()-currentMillis)/100;

        currentX.setText(Float.toString(dx));
        currentY.setText(Float.toString(dy));
        currentZ.setText(Float.toString(dz));
        currentTime.setText(Double.toString(timeNow));

        dx = event.values[0];
        dy = event.values[1];
        dz = event.values[2];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
