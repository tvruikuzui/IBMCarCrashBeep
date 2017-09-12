package com.example.aharon.ibmcarcrashbeep;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.aharon.ibmcarcrashbeep.Services.DidCrashAccord;

import java.io.File;

public class PopUo extends AppCompatActivity implements LocationListener {

    private FrameLayout con;
    private LocationManager lm;
    public double latitude, longitude;
    public String No1, No2;
    private DidCrashAccord thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_uo);

        thread = new DidCrashAccord();
        thread.start();
        con = (FrameLayout) findViewById(R.id.con);

    }

    public void noOk(View view) {
        con.setVisibility(View.VISIBLE);

    }

    public void ok(View view) {
        Toast.makeText(this, "OK :)", Toast.LENGTH_SHORT).show();
        thread.interrupt();
        finish();
    }

    public void error(View view) {
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
        thread.interrupt();
        finish();
    }

    @Override
    public void onLocationChanged(Location location){
        latitude=location.getLatitude();
        longitude=location.getLongitude();
        Toast.makeText(getApplicationContext(),"Lat and Long extracted",Toast.LENGTH_LONG).show();
    }
    @Override
    public void onProviderDisabled(String provider){
    }
    @Override
    public void onProviderEnabled(String provider){
    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
    }

    void sendSMS(){
        SharedPreferences sharedPreferences = getSharedPreferences("MY_PREF",MODE_PRIVATE);

        No1 = sharedPreferences.getString("NO1","");
        No2 = sharedPreferences.getString("NO2","");

        final SmsManager sms = SmsManager.getDefault();
        lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 3000, 10, this);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                sms.sendTextMessage(No1, null, "Help! I've met with an accident at http://maps.google.com/?q="+String.valueOf(latitude)+","+String.valueOf(longitude), null, null);
                sms.sendTextMessage(No1, null, "Nearby Hospitals http://maps.google.com/maps?q=hospital&mrt=yp&sll="+String.valueOf(latitude)+","+String.valueOf(longitude)+"&output=kml", null, null);
                sms.sendTextMessage(No2, null, "Help! I've met with an accident at http://maps.google.com/?q="+String.valueOf(latitude)+","+String.valueOf(longitude), null, null);
                sms.sendTextMessage(No2, null, "Nearby Hospitals http://maps.google.com/maps?q=hospital&mrt=yp&sll="+String.valueOf(latitude)+","+String.valueOf(longitude)+"&output=kml", null, null);
                System.exit(1);
            }
        }, 15000);

    }
}
