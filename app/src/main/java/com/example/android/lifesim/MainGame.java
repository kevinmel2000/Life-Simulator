package com.example.android.lifesim;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.RestrictTo;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Random;

public class MainGame extends AppCompatActivity {

    String doctorNames[] = {"Mike Rable", "Murphy Morgan", "John Seplin", "Morgan Johnson", "Hank Freeman", "Wilson Bennett", "Amy Peterson", "Ty Milone"};
    String physicalInjuries[] = {"a Sprained Ankle", "a Broken Arm", "a Torn ACL"};
    String popuptemplates11to17[] = {"Marijuana", "Bullying", "Amusement Park"};
    String popuptemplates5to17[] = {"Grandma", "Bicycle", "Study"};
    String popuptemplates18forLife[] = {"Homeless", "Cousin", "Horse Race"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        // Hides Action Bar on this page
        ActionBar actionBar = getActionBar();
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        /* loads in data from inputActivity screen */
        Intent intent = getIntent();
        String genderText = intent.getStringExtra("gender");
        String firstNameText = intent.getStringExtra("firstName");
        String lastNameText = intent.getStringExtra("lastName");


        TextView nameView = (TextView) findViewById(R.id.nameView);
        TextView nameViewLast = (TextView) findViewById(R.id.nameViewLast);
        nameView.setText(firstNameText);
        nameViewLast.setText(lastNameText);

        // Initializing main character
        final Person mainPerson = new Person(firstNameText + " " + lastNameText, 0, 0, randomNumberInBetweenMaxMin(70, 100), 100);
        printFirstTextView(mainPerson);


        // BankAccount Button onClick Function
        Button bankButton = (Button) findViewById(R.id.bankView);
        bankButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


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

        // Up Age button onClick function
        Button upAgeButton = (Button) findViewById(R.id.progressAge);
        upAgeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPerson.upAge();
                nextAgeTextView(mainPerson);
            }
        });

        // Activity Popup Back Button function
        ImageButton backButton = (ImageButton) findViewById(R.id.activityPopupBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                activityBackButtonFunction();
            }
        });


        // Activity Button onClick function
        final Button activitiesButton = (Button) findViewById(R.id.buttonActivities);
        activitiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LinearLayout activityPopup = (LinearLayout) findViewById(R.id.activityPopup);
                LinearLayout topBarLayout = (LinearLayout) findViewById(R.id.topbarlayout);
                LinearLayout activityBarLinearLayout = (LinearLayout) findViewById(R.id.activityBarLinearLayout);

                if (activityPopup.getVisibility() == View.GONE) {
                    activityPopup.setVisibility(View.VISIBLE);
                    topBarLayout.setVisibility(View.GONE);
                    activityBarLinearLayout.setVisibility(View.GONE);

                }

            }
        });

        // Go to doctor office onclick button
        final LinearLayout doctorOfficeButton = (LinearLayout) findViewById(R.id.doctorOfficeButton);
        doctorOfficeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // if person is not sick
                if (mainPerson.getSickness() == null) {
                    Toast drerrortoast = Toast.makeText(getApplicationContext(),
                            "You must be sick to go to the doctor.",
                            Toast.LENGTH_SHORT);

                    drerrortoast.show();
                }
                // if they are sick
                else {
                    hideActivityBarBringBackTopBar();
                    chooseDoctor(mainPerson);

                }
            }
        });

        // Buy Lottery Ticket onClick button
        final LinearLayout lotteryTicketButton = (LinearLayout)findViewById(R.id.lotteryTicketButton);
        lotteryTicketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lotteryOddsFunc(mainPerson);
            }
        });

        // if they choose Doctor 1
        Button drButton1 = (Button) findViewById(R.id.drbutton1);
        drButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mainPerson.isWentToDoctorThisTurn()) {
                    curePerson(mainPerson, 1);
                } else {
                    Toast failToast = Toast.makeText(getApplicationContext(),
                            "You already went to the doctor. Wait until next year",
                            Toast.LENGTH_LONG);
                    failToast.show();
                }
            }
        });

        // if they choose Doctor 2
        Button drButton2 = (Button) findViewById(R.id.drbutton2);
        drButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mainPerson.isWentToDoctorThisTurn()) {
                    curePerson(mainPerson, 2);
                } else {
                    Toast failToast = Toast.makeText(getApplicationContext(),
                            "You already went to the doctor. Wait until next year",
                            Toast.LENGTH_LONG);
                    failToast.show();
                }
            }
        });

        // if they choose Doctor 3
        Button drButton3 = (Button) findViewById(R.id.drbutton3);
        drButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mainPerson.isWentToDoctorThisTurn()) {
                    curePerson(mainPerson, 3);
                } else {
                    Toast failToast = Toast.makeText(getApplicationContext(),
                            "You already went to the doctor. Wait until next year",
                            Toast.LENGTH_LONG);
                    failToast.show();
                }

            }
        });

        // Doctor Popup Back Button function
        ImageButton drBackButton = (ImageButton) findViewById(R.id.drbackbutton);
        drBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout doctorLayout = (RelativeLayout) findViewById(R.id.doctorPopup);
                doctorLayout.setVisibility(View.GONE);
            }
        });

        // Workout button onclick function
        LinearLayout workoutButton = (LinearLayout)findViewById(R.id.workoutButton);
        workoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mainPerson.isWentToWorkoutThisTurn()){
                    Toast workedOut = Toast.makeText(getApplicationContext(),
                            "You already worked out. Wait until next year",
                            Toast.LENGTH_LONG);
                    workedOut.show();

                }

                // must be 12 or older to workout
                if(mainPerson.getAge() > 12){
                    workoutFunc(mainPerson);
                }
                else{
                    Toast underage = Toast.makeText(getApplicationContext(),
                            "You must be at least 13 to workout",
                            Toast.LENGTH_LONG);
                    underage.show();
                }
            }
        });

        // Therapist button onClick function
        LinearLayout therapistButton = (LinearLayout)findViewById(R.id.therapistButton);
        therapistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visitTherapist(mainPerson);
            }
        });


    }

    void nextAgeTextView(Person mainPerson) {

        // if their health hits 0 they die
        if (mainPerson.getHealth() <= 0) {
            killPerson(mainPerson);
        }

        mainPerson.setWentToDoctorThisTurn(false);
        mainPerson.setWentToWorkoutThisTurn(false);

        // update person's bank account if they have a job
        if(mainPerson.getJob() != null){
            mainPerson.setBankBalance(mainPerson.getBankBalance() + mainPerson.getJob().getJobSalary());
        }


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
        tv.setPadding(0, 0, 0, 40);

        /*Sets Drawable for border on bottom of textview in scrollview*/
        final int sdk = android.os.Build.VERSION.SDK_INT;   // gets int version of os build
        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) // if os is less than Jelly Bean then make it drawable
        {
            tv.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.maingametextviewborderbottom));
        } else {
            tv.setBackground(ContextCompat.getDrawable(this, R.drawable.maingametextviewborderbottom));
        }

        //get the parent layout for your new TextView and add the new TextView to it
        LinearLayout linearLayout = findViewById(R.id.insideScrollView);
        linearLayout.addView(topView);
        linearLayout.addView(tv);

        ageFuncs(mainPerson, mainPerson.getAge(), tv);
        maintainScrollViewDown();


        // Displays bankaccount balance now
        String bankBalanceString = formatToCurrency(mainPerson.getBankBalance());
        Button bankAccountView = (Button) findViewById(R.id.bankView);
        bankAccountView.setText("Bank Account\n" + bankBalanceString);

        // Updates ProgressBars
        ProgressBar healthBar = (ProgressBar) findViewById(R.id.progressbarHealth);
        healthBar.setProgress(mainPerson.getHealth());
        ProgressBar happyBar = (ProgressBar) findViewById(R.id.progressbarHappy);
        happyBar.setProgress(mainPerson.getHappiness());


        // Person possibly gets sick here
        mainPerson.randomSickness();
        printSickness(mainPerson, tv);


        maintainScrollViewDown(); // keeps scroll focused downward.


        // What happens when you click the assets button
        Button assetsButton = (Button)findViewById(R.id.assetsButton);
        assetsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    // Depending on age, can make life choices
    private void ageFuncs(Person mainPerson, int personAge, TextView tv){

        boolean specAge = false;

        switch (personAge){
            case 13:
                thirteenSexualOrientation(mainPerson, tv);
                specAge = true;
                break;
        }

        // if no popup has been shown and they're from ages 5 - 17
        if(!specAge && personAge >= 5 && personAge <= 17){

            int randNum = randomNumberInBetweenMaxMin(1, 10);

            // 10% chance of this popup
            if(randNum == 6){
                String popupEvent = randArrayTitle(popuptemplates5to17); // generates string in this array
                popuptemplate randGenEvent = new popuptemplate(popupEvent); // makes object of event

                alertDialogFunc(randGenEvent.getDescription(), randGenEvent.getOption1(), randGenEvent.getOption2(), randGenEvent.getYesHappy(), randGenEvent.getYesHealth(), randGenEvent.getNoHappy(), randGenEvent.getNoHealth(), randGenEvent.getWealthEffect(), randGenEvent.getWealthEffectNo(), mainPerson, tv);
                // String questionTitle, final String option1, final String option2, final int yesHappy, final int yesHealth, final int noHappy, final int noHealth, final double wealthEffect, final double wealthEffectNo, final Person mainPerson, final TextView tv

            }
        }


        // if they haven't already had a popup at this age and they're between 11-17
        if(!specAge && personAge >= 11 && personAge <= 17){
            int randNum = randomNumberInBetweenMaxMin(1, 10);
                // 20% chance
            if(randNum < 8 && randNum >= 6)
            {
                String popupEvent = randArrayTitle(popuptemplates11to17); // generates string in this array
                popuptemplate randGenEvent = new popuptemplate(popupEvent); // makes object of event

                alertDialogFunc(randGenEvent.getDescription(), randGenEvent.getOption1(), randGenEvent.getOption2(), randGenEvent.getYesHappy(), randGenEvent.getYesHealth(), randGenEvent.getNoHappy(), randGenEvent.getNoHealth(), randGenEvent.getWealthEffect(), randGenEvent.getWealthEffectNo(), mainPerson, tv);

            }
        }

        if(!specAge && personAge >= 18){
            int randNum = randomNumberInBetweenMaxMin(1, 10);
            if(randNum < 8 && randNum >= 6){
                String popupEvent = randArrayTitle(popuptemplates18forLife);
                popuptemplate randGenEvent = new popuptemplate(popupEvent);
                alertDialogFunc(randGenEvent.getDescription(), randGenEvent.getOption1(), randGenEvent.getOption2(), randGenEvent.getYesHappy(), randGenEvent.getYesHealth(), randGenEvent.getNoHappy(), randGenEvent.getNoHealth(), randGenEvent.getWealthEffect(), randGenEvent.getWealthEffectNo(), mainPerson, tv);

            }
        }

    }

    // Template code for building a popup scenario
    private void popupTemplate(final Person mainPerson, final TextView tv, final String title, String description, String option1, String option2, final int yesHappy, final int yesHealth, final int noHappy, final int noHealth, final String shortDesc){

        RelativeLayout.LayoutParams buttonParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        buttonParams.setMargins(0,0,0,120);


        // Hide Layouts
        final LinearLayout activityBarButtons = (LinearLayout)findViewById(R.id.activityBarLinearLayout);
        final ScrollView scroll = (ScrollView)findViewById(R.id.scrollviewmain);
        scroll.setVisibility(View.GONE);
        activityBarButtons.setVisibility(View.GONE);

        // Show layouts
        final LinearLayout emptypopuplayout = (LinearLayout)findViewById(R.id.emptypopuplayout);
        emptypopuplayout.setVisibility(View.VISIBLE);
        final LinearLayout emptypopupbuttonlayout = (LinearLayout)findViewById(R.id.emptypopupbuttonlayout);
        emptypopupbuttonlayout.setVisibility(View.VISIBLE);

        TextView emptypopuptitletext = (TextView)findViewById(R.id.emptypopuptitletext);
        emptypopuptitletext.setText(title);

        final TextView emptypopupdescription = (TextView)findViewById(R.id.emptypopupdescription);
        emptypopupdescription.setText(description);
        emptypopupdescription.setVisibility(View.VISIBLE);

        // Add Buttons
        final Button button1 = new Button(this);
        Button button2 = new Button(this);
        button1.setLayoutParams(buttonParams);
        button2.setLayoutParams(buttonParams);
        button1.setText(option1); // yes option
        button2.setText(option2); // no option
        button1.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.buttonbackground));
        button2.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.buttonbackground));
        button1.setTextSize(18);
        button2.setTextSize(18);
        button1.setPadding(60, 30, 60 ,30);
        button2.setPadding(60,30,60,30);

        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                activityBarButtons.setVisibility(View.VISIBLE); // Makes old views visible
                scroll.setVisibility(View.VISIBLE);
                emptypopupbuttonlayout.removeAllViews(); // Deletes all buttons
                emptypopupdescription.setVisibility(View.GONE);
                emptypopuplayout.setVisibility(View.GONE); // Hides empty linear layout

                mainPerson.setHappiness(mainPerson.getHappiness() + yesHappy);
                mainPerson.setHealth(mainPerson.getHealth() + yesHealth);

                maintainScrollViewDown();
                tv.setText("You decided to " + shortDesc + "\n");

                popupTemplateTextHelper(tv, yesHappy, yesHealth, noHappy, noHealth, true);

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityBarButtons.setVisibility(View.VISIBLE); // Makes old views visible
                scroll.setVisibility(View.VISIBLE);
                emptypopupbuttonlayout.removeAllViews(); // Deletes all buttons
                emptypopupdescription.setVisibility(View.GONE);
                emptypopuplayout.setVisibility(View.GONE); // Hides empty linear layout

                mainPerson.setHappiness(mainPerson.getHappiness() + noHappy);
                mainPerson.setHealth(mainPerson.getHealth() + noHealth);


                maintainScrollViewDown();
                tv.setText("You decided not to " + shortDesc + "\n");

                popupTemplateTextHelper(tv, yesHappy, yesHealth, noHappy, noHealth, false);
            }
        });

        emptypopupbuttonlayout.addView(button1);
        emptypopupbuttonlayout.addView(button2);


    }

    // Used in popupTemplate to help display text to TV
    private void popupTemplateTextHelper(TextView tv, final int yesHappy, final int yesHealth, final int noHappy, final int noHealth, boolean yesOrNo){

        if(yesOrNo == true){
            // Output to console
            if(yesHappy < 0){
                tv.append("Happiness: " + yesHappy + "\n");
            }else{
                tv.append("Happiness: +" + yesHappy + "\n");
            }
            if(yesHealth < 0){
                tv.append("Health: " + yesHealth + "\n");
            }else{
                tv.append("Health: +" + yesHealth + "\n");
            }
        }
        else if(yesOrNo == false){
            // Output to console
            if(noHappy < 0){
                tv.append("Happiness: " + noHappy + "\n");
            }else{
                tv.append("Happiness: +" + noHappy + "\n");
            }
            if(noHealth < 0){
                tv.append("Health: " + noHealth + "\n");
            }else{
                tv.append("Health: +" + noHealth + "\n");
            }
        }

    }

    // Popup at 13 years old which asks them for sexual orientation
    private void thirteenSexualOrientation(final Person mainPerson, final TextView tv){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sexual Orientation");
        builder.setCancelable(false);

        // add a list
        String[] options = {"Gay", "Straight", "Bisexual"};
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Button bankView = findViewById(R.id.bankView);
                switch (which) {
                    case 0:
                        // Option 1 clicked
                        mainPerson.setSexualOrientation("Gay");
                        tv.append("You are gay");
                        maintainScrollViewDown();
                        break;
                    case 1:
                        // Option 2 clicked
                        mainPerson.setSexualOrientation("Straight");
                        tv.append("You are straight");
                        maintainScrollViewDown();
                        break;
                    case 2:
                        // Option 3 Clicked
                        mainPerson.setSexualOrientation("Bisexual");
                        tv.append("You are bisexual");
                        maintainScrollViewDown();
                        break;

                }
            }
        });

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    // Allows player to play the lottery
    private void lotteryOddsFunc(Person mainPerson) {
        if(mainPerson.getAge() < 18){
            Toast underage = Toast.makeText(getApplicationContext(),
                    "You must be 18 to gamble",
                    Toast.LENGTH_LONG);
            underage.show();
            return;
        }

        int randomNum = randomNumberInBetweenMaxMin(1, 1000);
        int winning;
        boolean won = true;

        // Winning logic
        if(randomNum == 1000){
            winning = 150000;
        }else if(randomNum <= 999 && randomNum >= 996){
            winning = 10000;
        }else if(randomNum <= 995 && randomNum >= 990){
            winning = 1000;
        }else if(randomNum <= 989 && randomNum >= 980){
            winning = 500;
        }else if(randomNum <= 979 && randomNum >= 930){
            winning = 50;
        }else if(randomNum <= 929 && randomNum >= 830){
            winning = 20;
        }else if(randomNum <= 829 && randomNum >= 700){
            winning = 10;
        }else if(randomNum <= 699 && randomNum >= 500){
            winning = 5;
        }else{
            winning = 0;
            won = false;
        }

        if(won){
            Toast winToast = Toast.makeText(getApplicationContext(),
                    "Congrats! You won " + formatToCurrency(winning),
                    Toast.LENGTH_LONG);
            winToast.show();
        }else{
            Toast loseToast = Toast.makeText(getApplicationContext(),
                    "Sorry, you didn't win anything...",
                    Toast.LENGTH_LONG);
            loseToast.show();
        }

        activityBackButtonFunction();

        mainPerson.setBankBalance(mainPerson.getBankBalance() - 5 + winning);
        Button bankButton = (Button)findViewById(R.id.bankView);
        bankButton.setText("Bank Account\n" + formatToCurrency(mainPerson.getBankBalance()));

        int randNum2 = randomNumberInBetweenMaxMin(1,200);

        // 0.5% chance to get sick with Gambling Addiction
        if(randNum2 == 157 && mainPerson.getSickness() == null){
               Sickness gambleSick = new Sickness("a Gambling Addiction");
               mainPerson.setSickness(gambleSick);
        }

    }

    // Prints sickness to tv if they're sick and takes away their health/happiness and adds year to sickness
    void printSickness(Person mainPerson, TextView tv) {

        // if person is sick, display to screen
        if (mainPerson.getSickness() != null) {

            if(mainPerson.getSickness().getYearsWith() > 0){
                tv.append("You're still suffering from " + mainPerson.getSickness().getTitle() + ".");
            }
            else if(mainPerson.getSickness().getYearsWith() == 0){
                tv.append("Oh no! You just got " + mainPerson.getSickness().getTitle() + ", which is " + mainPerson.getSickness().getDescription());
            }

            mainPerson.getSickness().addYearToSickness();

            // Takes away their health/happy based on damage per turn
            int currentHappy = mainPerson.getHappiness();
            int currentHealth = mainPerson.getHealth();
            int damageHappy = mainPerson.getSickness().getHappyDamagePerTurn();
            int damageHealth = mainPerson.getSickness().getDamagePerTurn();
            mainPerson.setHealth(currentHealth - damageHealth);
            mainPerson.setHappiness(currentHappy - damageHappy);
        }
    }

    // Prints first TextView to the ScrollView
    void printFirstTextView(Person mainPerson) {


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
        tv.setPadding(0, 0, 0, 40);

        /*Sets Drawable for border on bottom of textview in scrollview*/
        final int sdk = android.os.Build.VERSION.SDK_INT;   // gets int version of os build
        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) // if os is less than Jelly Bean then make it drawable
        {
            tv.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.maingametextviewborderbottom));
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
        Button bankAccountView = (Button) findViewById(R.id.bankView);
        bankAccountView.setText("Bank Account\n" + bankBalanceString);

        // Updates ProgressBars
        ProgressBar healthBar = (ProgressBar) findViewById(R.id.progressbarHealth);
        healthBar.setProgress(mainPerson.getHealth());
        ProgressBar happyBar = (ProgressBar) findViewById(R.id.progressbarHappy);
        happyBar.setProgress(mainPerson.getHappiness());

    }

    // Constant --> Keeps scrollview focused downwards
    void maintainScrollViewDown() {

        final ScrollView scrollView = (ScrollView) findViewById(R.id.scrollviewmain);
        scrollView.post(new Runnable() {
            public void run() {
                scrollView.fullScroll(View.FOCUS_DOWN);
            }
        });
    }

    /*Takes in a double and returns a string formatted to currency*/
    public String formatToCurrency(double money) {

        NumberFormat format = NumberFormat.getCurrencyInstance();

        return format.format(money);
    }

    // Returns a random number in between a min/max
    int randomNumberInBetweenMaxMin(int min, int max) {
        return (new Random()).nextInt((max - min) + 1) + min;

    }

    // hides activity bar and brings back top bar
    private void hideActivityBarBringBackTopBar() {
        LinearLayout activityPopup = (LinearLayout) findViewById(R.id.activityPopup);
        LinearLayout topBarLayout = (LinearLayout) findViewById(R.id.topbarlayout);
        LinearLayout activityBarLinearLayout = (LinearLayout) findViewById(R.id.activityBarLinearLayout);

        activityPopup.setVisibility(View.GONE);
        topBarLayout.setVisibility(View.VISIBLE);
        activityBarLinearLayout.setVisibility(View.VISIBLE);
        maintainScrollViewDown();
    }

    // Randomly returns a string in an array
    public String randArrayTitle(String[] jobTitles) {

        int size = jobTitles.length - 1; // size of array


        int randomNum = (int) (Math.random() * ((size) + 1));
        return jobTitles[randomNum];

    }

    // Allows them to choose a doctor from the list
    private void chooseDoctor(Person mainPerson) {
        // Makes Doctor Bar visible
        RelativeLayout doctorPopup = (RelativeLayout) findViewById(R.id.doctorPopup);
        doctorPopup.setVisibility(View.VISIBLE);

        Button drbutton1 = (Button) findViewById(R.id.drbutton1);
        Button drbutton2 = (Button) findViewById(R.id.drbutton2);
        Button drbutton3 = (Button) findViewById(R.id.drbutton3);
        TextView feedbackText = (TextView) findViewById(R.id.feedbacktextdr);

        String docName1 = randArrayTitle(doctorNames);
        String docName2 = "";
        String docName3 = "";
        do {
            docName2 = randArrayTitle(doctorNames);
        } while (docName2.equals(docName1));
        do {
            docName3 = randArrayTitle(doctorNames);
        } while (docName3.equals(docName1) || docName3.equals(docName2));

        double costToTreat = mainPerson.getSickness().getCostToTreat();

        drbutton1.setText(docName1 + " " + formatToCurrency(costToTreat / 2));
        drbutton2.setText(docName2 + " " + formatToCurrency(costToTreat));
        drbutton3.setText(docName3 + " " + formatToCurrency(costToTreat * 2));


    }

    // Makes back button work after you click activities button
    void activityBackButtonFunction() {
        hideActivityBarBringBackTopBar();
    }

    // Possibly cures the person if they are sick
    private void curePerson(Person mainPerson, int doctorChosen) {
        RelativeLayout doctorPopup = (RelativeLayout) findViewById(R.id.doctorPopup);

        // If they have a sickness needing a therapist
        if(mainPerson.getSickness().getTitle().equals("Gambling Addiction")){
            Toast therapistToast = Toast.makeText(getApplicationContext(),
                    "The doctor says you should see a therapist",
                    Toast.LENGTH_LONG);
            therapistToast.show();
            maintainScrollViewDown();
            doctorPopup.setVisibility(View.GONE);
            return;
        }

        int randomInt = randomNumberInBetweenMaxMin(1, 10);
        double costToTreat = mainPerson.getSickness().getCostToTreat();
        Toast failToast = Toast.makeText(getApplicationContext(),
                "You failed to get cured. Better luck next time!",
                Toast.LENGTH_LONG);
        Toast successToast = Toast.makeText(getApplicationContext(),
                "You were cured!", Toast.LENGTH_LONG);


        if (doctorChosen == 1) {
            // 40% chance of cure
            if (randomInt <= 4) {
                mainPerson.setSickness(null);
                successToast.show();
                doctorPopup.setVisibility(View.GONE);
            } else {
                failToast.show();
                doctorPopup.setVisibility(View.GONE);
            }

            mainPerson.setBankBalance(mainPerson.getBankBalance() - (costToTreat / 2));
        } else if (doctorChosen == 2) {
            if (randomInt <= 6) {
                mainPerson.setSickness(null);
                successToast.show();
                doctorPopup.setVisibility(View.GONE);
            } else {
                failToast.show();
                doctorPopup.setVisibility(View.GONE);
            }
            mainPerson.setBankBalance(mainPerson.getBankBalance() - (costToTreat));
        } else if (doctorChosen == 3) {
            if (randomInt <= 8) {
                mainPerson.setSickness(null);
                successToast.show();
                doctorPopup.setVisibility(View.GONE);
            } else {
                failToast.show();
                doctorPopup.setVisibility(View.GONE);
            }
            mainPerson.setBankBalance(mainPerson.getBankBalance() - (costToTreat * 2));
        }

        Button bankButton = (Button) findViewById(R.id.bankView);
        bankButton.setText("Bank Account: \n" + formatToCurrency(mainPerson.getBankBalance()));
        mainPerson.setWentToDoctorThisTurn(true);
        maintainScrollViewDown();

    }

    // Ends the life of the person and ends the game
    private void killPerson(Person mainPerson) {

        AlertDialog alertDialog = new AlertDialog.Builder(MainGame.this).create();
        alertDialog.setCancelable(false);
        alertDialog.setTitle("You died!");
        alertDialog.setMessage(mainPerson.getName() + " died at age " + mainPerson.getAge() + ".");
        alertDialog.setIcon(R.drawable.deathsymbol);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                });
        alertDialog.show();
    }

    // Person works out and health/happy update
    private void workoutFunc(Person mainPerson) {
        int randNum = randomNumberInBetweenMaxMin(1, 20);

        // 5% chance of injury
        if(randNum == 9){
            // if they're already sick
            if(mainPerson.getSickness() != null){
                Toast badWorkout = Toast.makeText(getApplicationContext(),
                        "You left since there were too many people there working out.",
                        Toast.LENGTH_LONG);
                badWorkout.show();
            }else{
                String randomInjury = randArrayTitle(physicalInjuries);
                Sickness injury = new Sickness(randomInjury);
                mainPerson.setSickness(injury);
                Button bankButton = (Button)findViewById(R.id.bankView);
                bankButton.setText("Bank Account\n" + mainPerson.getBankBalance());
            }
        }else {

            int newHappy = mainPerson.getHappiness() + 5;
            int newHealth = mainPerson.getHealth() + 5;
            mainPerson.setHappiness(newHappy);
            mainPerson.setHealth(newHealth);
            Toast goodWorkout = Toast.makeText(getApplicationContext(),
                    "You had a successful workout",
                    Toast.LENGTH_LONG);
            goodWorkout.show();

            ProgressBar happyBar = (ProgressBar)findViewById(R.id.progressbarHappy);
            ProgressBar healthBar = (ProgressBar)findViewById(R.id.progressbarHealth);

            happyBar.setProgress(newHappy);
            healthBar.setProgress(newHealth);
        }

        mainPerson.setWentToWorkoutThisTurn(true);
    }

    // Allows mentally sick people to see the therapist
    private void visitTherapist(Person mainPerson) {
        // if person is not sick
        if (mainPerson.getSickness() == null) {
            Toast drerrortoast = Toast.makeText(getApplicationContext(),
                    "You must be unwell to go to the therapist.",
                    Toast.LENGTH_SHORT);

            drerrortoast.show();
        }
        // if it is not a mental illness
        else if(!mainPerson.getSickness().getType().equals("mental")){

            Toast notMental = Toast.makeText(getApplicationContext(),
                    "You don't have a mental illness. Go see the doctor.", Toast.LENGTH_LONG);
            notMental.show();

        }
        // if they really are mentally ill
        else {
            hideActivityBarBringBackTopBar();
            chooseDoctor(mainPerson);
        }
    }

    private void addOrRemoveProperty(View view, int property, boolean flag){
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
        if(flag){
            layoutParams.addRule(property);
        }else {
            layoutParams.removeRule(property);
        }
        view.setLayoutParams(layoutParams);
    }

    // For Alert Dialogs in ageFunc
    private void alertDialogFunc(String questionTitle, final String option1, final String option2, final int yesHappy, final int yesHealth, final int noHappy, final int noHealth, final double wealthEffect, final double wealthEffectNo, final Person mainPerson, final TextView tv){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(questionTitle);
        builder.setCancelable(false);

        // add a list
        String[] options = {option1, option2};
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Button bankView = findViewById(R.id.bankView);
                switch (which) {
                    case 0:
                        // Option 1 clicked
                        mainPerson.setHealth(mainPerson.getHealth() + yesHealth);
                        mainPerson.setHappiness(mainPerson.getHappiness() + yesHappy);
                        mainPerson.setBankBalance(mainPerson.getBankBalance() + wealthEffect);
                        bankView.setText("Bank Account\n" + formatToCurrency(mainPerson.getBankBalance()));
                        tv.append("You chose to " + option1 + ".\n");
                        if(yesHappy != 0){
                            tv.append("Happiness: " + plusMinusString((double)yesHappy) + "\n");
                        }
                        if(yesHealth != 0){
                            tv.append("Health: " + plusMinusString((double)yesHealth) + "\n");
                        }
                        if(wealthEffect != 0){
                            String wealthEffectString = "";
                            if(wealthEffectNo < 0)
                            {
                                wealthEffectString = "-" + formatToCurrency(wealthEffect);
                            }
                            else
                            {
                                wealthEffectString = "+" + formatToCurrency(wealthEffect);
                            }
                            tv.append("Bank Account: " + wealthEffectString + "\n");
                        }
                        maintainScrollViewDown();
                        break;
                    case 1:
                        // Option 2 clicked
                        mainPerson.setHealth(mainPerson.getHealth() + noHealth);
                        mainPerson.setHappiness(mainPerson.getHappiness() + noHappy);
                        mainPerson.setBankBalance(mainPerson.getBankBalance() + wealthEffectNo);
                        bankView.setText("Bank Account\n" + formatToCurrency(mainPerson.getBankBalance()));
                        tv.append("You chose to " + option2 + ".\n");
                        if(noHappy != 0){
                            tv.append("Happiness: " + plusMinusString((double)noHappy) + "\n");
                        }
                        if(noHappy != 0){
                            tv.append("Health: " + plusMinusString((double)noHappy) + "\n");
                        }
                        if(wealthEffectNo != 0){
                            String wealthEffectString = "";
                            if(wealthEffectNo < 0)
                            {
                                wealthEffectString = "-" + formatToCurrency(wealthEffectNo);
                            }
                            else
                            {
                                wealthEffectString = "+" + formatToCurrency(wealthEffectNo);
                            }
                            tv.append("Bank Account: " + wealthEffectString + "\n");
                        }
                        maintainScrollViewDown();

                        break;

                }
            }
        });

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // Returns + and - in front of attribute
    private String plusMinusString(double attribute) {
        if(attribute < 0){
            return "" + attribute;
        }else if(attribute > 0){
            return "+" + attribute;
        }else{
            return "Unchanged";
        }
    }

}
