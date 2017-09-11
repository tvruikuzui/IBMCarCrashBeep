package com.example.aharon.ibmcarcrashbeep.Services;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.Toast;

import com.example.aharon.ibmcarcrashbeep.PopUo;

public class MyService extends Service implements AccelerometerWork2.OnShakeListener {

    public int check;
    private AccelerometerWork2 mShaker;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private FragmentManager manager;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public void onCreate() {

        super.onCreate();
        this.mSensorManager = ((SensorManager)getSystemService(Context.SENSOR_SERVICE));
        this.mAccelerometer = this.mSensorManager.getDefaultSensor(1);
        mShaker = new AccelerometerWork2(this);
        mShaker.setOnShakeListener(this);
        Toast.makeText(MyService.this, "Service is created!",Toast.LENGTH_LONG).show();
        Log.d(getPackageName(), "Created the Service!");
        check=1;
    }
    @Override
    public void onShake() {
        if(check==1) {
//            TODO:implements an alert window!!!
            IBMConversationService ibmConversationService = new IBMConversationService();
            ibmConversationService.setC(this);
            Toast.makeText(MyService.this, "SHAKEN!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this,PopUo.class);
            startActivity(intent);

            //       final Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            //         vib.vibrate(500);
//            Intent i = new Intent();
//            i.setClass(this, CheckCertainty.class);
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(i);
        }

    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);

    }
    public void onDestroy(){
        super.onDestroy();
        check=0;
        Log.d(getPackageName(),"Service Destroyed.");
    }


}
