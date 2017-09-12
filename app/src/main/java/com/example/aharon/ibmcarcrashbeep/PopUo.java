package com.example.aharon.ibmcarcrashbeep;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

public class PopUo extends AppCompatActivity {

    private FrameLayout con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_uo);

        con = (FrameLayout) findViewById(R.id.con);
    }

    public void noOk(View view) {
        con.setVisibility(View.VISIBLE);

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
