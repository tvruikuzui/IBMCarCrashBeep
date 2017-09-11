package com.example.aharon.ibmcarcrashbeep;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class PopUo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_uo);
    }

    public void noOk(View view) {
        Toast.makeText(this, " No ok", Toast.LENGTH_SHORT).show();
    }

    public void ok(View view) {
        Toast.makeText(this, "OK :)", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void error(View view) {
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
        finish();
    }
}
