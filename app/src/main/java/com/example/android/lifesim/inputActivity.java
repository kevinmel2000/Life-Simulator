package com.example.android.lifesim;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class inputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
    }

    public void beginMainGame(View view) {
        Intent intent = new Intent(this, MainGame.class);

        EditText firstName = (EditText)findViewById(R.id.firstName);
        EditText lastName = (EditText)findViewById(R.id.lastName);
        ToggleButton btnMale = (ToggleButton)findViewById(R.id.btnMale);
        ToggleButton btnFemale = (ToggleButton)findViewById(R.id.btnFemale);
        TextView errorView = (TextView)findViewById(R.id.errorTextBox);

        String firstNameText = firstName.getText().toString().substring(0,1).toUpperCase() + firstName.getText().toString().substring(1);
        String lastNameText = lastName.getText().toString().substring(0,1).toUpperCase() + lastName.getText().toString().substring(1);
        String genderText = "";

        /*     Error checking here     */
        if(firstName.getText().toString().isEmpty() || lastName.getText().toString().isEmpty()){ // if either name is blank
            errorView.setText("No characters in name");
            return;
        }
        if(checkWhiteSpace(firstNameText) || checkWhiteSpace(lastNameText)){ // if there is whitespace
            errorView.setText("No Spaces in Name");
            return;
        }
        if(isNameOverTenChars(firstNameText) || isNameOverTenChars(lastNameText)){ // if names are over 10 characters
            errorView.setText("Keep name under 10 characters");
            return;
        }
        if((btnFemale.isChecked() && btnMale.isChecked()) || (!btnFemale.isChecked() && !btnMale.isChecked())){
            errorView.setText("Choose one gender");
            return;
        }

        if(btnMale.isChecked()){
            genderText = "Male";
        } else if (btnFemale.isChecked()){
            genderText = "Female";
        }

        intent.putExtra("firstName", firstNameText);
        intent.putExtra("lastName", lastNameText);
        intent.putExtra("gender", genderText);

        startActivity(intent);
    }

    // returns bool if string has any whitespace or not
    public static boolean checkWhiteSpace(String testString){
        if(testString != null){
            for(int i = 0; i < testString.length(); i++){
                if(Character.isWhitespace(testString.charAt(i))){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isNameOverTenChars(String testString){
        int len = 0;
        if(testString != null){
            for(int i = 0; i < testString.length(); i++){
                len++;
                if(i >= 10){
                    return true;
                }
            }
        }
        return false;

    }


}
