package com.example.aharon.ibmcarcrashbeep;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.aharon.ibmcarcrashbeep.Services.AccelerometerWork;
import com.example.aharon.ibmcarcrashbeep.Services.IBMConversationService;
import com.example.aharon.ibmcarcrashbeep.Services.MyService;


public class MainActivity extends AppCompatActivity {

    LocationManager locationManager;
    LocationProvider provider;
    Button btnStartService;
    IBMConversationService service;
    AccelerometerWork work;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartService = (Button) findViewById(R.id.btnStartBeeping);

        service = new IBMConversationService();
        service.integrateWithIBM("message to watson");

        work = new AccelerometerWork(this);

    }

    @Override
    protected void onStart() {
        super.onStart();

        btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askGPSPermission();
            }
        });
    }

    //request permission for GPS may used again
    private void askGPSPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        }
    }

    //the user answer for the permission request
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "thenx", Toast.LENGTH_SHORT).show();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!Settings.canDrawOverlays(this)) {
                    Intent myIntent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                    myIntent.setData(Uri.parse("package:" + getPackageName()));
                    startActivityForResult(myIntent, 101);
                }else {
                    Intent intent = new Intent(this , MyService.class);
                    startService(intent);
                }
            }
        }else {
            Toast.makeText(this, "you have to grant permission", Toast.LENGTH_SHORT).show();
        }
    }

    //@RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 101){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (Settings.canDrawOverlays(this)){
                    Toast.makeText(this, "can overlay", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
