package com.example.aharon.ibmcarcrashbeep.Services;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

/**
 * Created by Aharon on 29/08/2017.
 */

public class AccelerometerWork implements SensorEventListener {

    private float prevSum,x,y,z;
    private Sensor sensor;
    private SensorManager sensorManager;

    public AccelerometerWork(Context c) {
        sensorManager = (SensorManager) c.getSystemService(c.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        x = event.values[0];
        y = event.values[1];
        z = event.values[2];
        float sum = x+y+z;
        //Log.d("ahron",String.valueOf(sum));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
