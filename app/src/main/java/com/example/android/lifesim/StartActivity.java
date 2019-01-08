package com.example.android.lifesim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    // Button click starts input Activity
    public void inputScreenActivity(View view) {
        Intent intent = new Intent(this, inputActivity.class);
        startActivity(intent);
        finish();

    }
}
