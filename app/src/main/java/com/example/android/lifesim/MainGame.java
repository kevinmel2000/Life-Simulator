package com.example.android.lifesim;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

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

        // Initializing main character
        final Person mainPerson = new Person(firstNameText + " " + lastNameText, 0, 0, 100, 100);
        printFirstTextView(mainPerson);


        // BankAccount Button onClick Function
        Button bankButton = (Button) findViewById(R.id.bankView);
        bankButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {


                AlertDialog alertDialog = new AlertDialog.Builder(MainGame.this).create();
                alertDialog.setTitle("Financial Information");
                alertDialog.setMessage("Bank Account Balance: " + formatToCurrency(mainPerson.getBankBalance()));
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        });

        Button upAgeButton = (Button)findViewById(R.id.progressAge);
        upAgeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPerson.upAge();
                nextAgeTextView(mainPerson);
            }
        });


    }

    void nextAgeTextView(Person mainPerson){

        maintainScrollViewDown(); // keeps scroll focused downward.


        /* DYAMICALLY ADDS TEXVIEW TO SCROLLVIEW */
        //create a TextView with Layout parameters according to your needs
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //if your parent Layout is relativeLayout, just change the word LinearLayout with RelativeLayout
        final TextView topView = new TextView(this);
        topView.setLayoutParams(lparams);
        topView.setTextSize(15);
        topView.setTextColor(Color.parseColor("#3F51B5"));
        topView.setText(getString(R.string.InitialTextViewAge, mainPerson.getAge()));

        final TextView tv = new TextView(this);
        tv.setLayoutParams(lparams);
        tv.setTextSize(15);
        tv.setTextColor(Color.BLACK);
        tv.setPadding(0,0,0,40);

        /*Sets Drawable for border on bottom of textview in scrollview*/
        final int sdk = android.os.Build.VERSION.SDK_INT;   // gets int version of os build
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) // if os is less than Jelly Bean then make it drawable
        {
            tv.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.maingametextviewborderbottom) );
        } else {
            tv.setBackground(ContextCompat.getDrawable(this, R.drawable.maingametextviewborderbottom));
        }

        //get the parent layout for your new TextView and add the new TextView to it
        LinearLayout linearLayout = findViewById(R.id.insideScrollView);
        linearLayout.addView(topView);
        linearLayout.addView(tv);


        // Displays bankaccount balance now
        String bankBalanceString = formatToCurrency(mainPerson.getBankBalance());
        Button bankAccountView = (Button)findViewById(R.id.bankView);
        bankAccountView.setText("Bank Account\n" + bankBalanceString);

        // Updates ProgressBars to 100 to begin
        ProgressBar healthBar = (ProgressBar)findViewById(R.id.progressbarHealth);
        healthBar.setProgress(mainPerson.getHealth());
        ProgressBar happyBar = (ProgressBar)findViewById(R.id.progressbarHappy);
        happyBar.setProgress(mainPerson.getHappiness());

    }

    // Prints first TextView to the ScrollView
    void printFirstTextView(Person mainPerson){


        /* DYAMICALLY ADDS TEXVIEW TO SCROLLVIEW */
        //create a TextView with Layout parameters according to your needs
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //if your parent Layout is relativeLayout, just change the word LinearLayout with RelativeLayout
        final TextView topView = new TextView(this);
        topView.setLayoutParams(lparams);
        topView.setTextSize(15);
        topView.setTextColor(Color.parseColor("#3F51B5"));
        topView.setText(getString(R.string.InitialTextViewAge, mainPerson.getAge()));

        final TextView tv = new TextView(this);
        tv.setLayoutParams(lparams);
        tv.setTextSize(15);
        tv.setTextColor(Color.BLACK);
        tv.setPadding(0,0,0,40);

        /*Sets Drawable for border on bottom of textview in scrollview*/
        final int sdk = android.os.Build.VERSION.SDK_INT;   // gets int version of os build
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) // if os is less than Jelly Bean then make it drawable
        {
            tv.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.maingametextviewborderbottom) );
        } else {
            tv.setBackground(ContextCompat.getDrawable(this, R.drawable.maingametextviewborderbottom));
        }


        // Set Text Here !!!!!!!!!!!!!!!
        tv.setText(getString(R.string.InitialTextViewIntro, mainPerson.getName()));



        //get the parent layout for your new TextView and add the new TextView to it
        LinearLayout linearLayout = findViewById(R.id.insideScrollView);
        linearLayout.addView(topView);
        linearLayout.addView(tv);


        // Displays bankaccount balance now
        String bankBalanceString = formatToCurrency(mainPerson.getBankBalance());
        Button bankAccountView = (Button)findViewById(R.id.bankView);
        bankAccountView.setText("Bank Account\n" + bankBalanceString);

        // Updates ProgressBars to 100 to begin
        ProgressBar healthBar = (ProgressBar)findViewById(R.id.progressbarHealth);
        healthBar.setProgress(mainPerson.getHealth());
        ProgressBar happyBar = (ProgressBar)findViewById(R.id.progressbarHappy);
        happyBar.setProgress(mainPerson.getHappiness());

    }

    // Constant --> Keeps scrollview focused downwards
    void maintainScrollViewDown(){
        // All constant
        ScrollView scroll = findViewById(R.id.scrollviewmain);
        scroll.fullScroll(View.FOCUS_DOWN);
    }



    /*Takes in a double and returns a string formatted to currency*/
    String formatToCurrency(double money){

        NumberFormat format = NumberFormat.getCurrencyInstance();

        return format.format(money);
    }
}
