package com.example.android.lifesim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        /* loads in data from inputActivity screen */
        Intent intent = getIntent();
        String genderText = intent.getStringExtra("gender");
        String firstNameText = intent.getStringExtra("firstName");
        String lastNameText = intent.getStringExtra("lastName");

        TextView nameView = (TextView)findViewById(R.id.nameView);
        TextView nameViewLast = (TextView)findViewById(R.id.nameViewLast);
        nameView.setText(firstNameText);
        nameViewLast.setText(lastNameText);


    }
}
