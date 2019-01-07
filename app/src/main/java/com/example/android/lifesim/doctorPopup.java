package com.example.android.lifesim;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import java.text.NumberFormat;

public class doctorPopup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_popup);

        // Hides Action Bar on this page
        ActionBar actionBar = getActionBar();
        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }




    }
}
