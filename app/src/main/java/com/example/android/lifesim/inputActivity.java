package com.example.android.lifesim;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

        String firstNameMale[] = {"Mike", "Rob", "Carson", "Brandon", "Norman", "John"};
        String firstNameFemale[] = {"Reyna","Carolina","Carley","Rebecca","Gretchen","Mercedes","Tiara","Macie","Tamara","Makenna","Madeline","Tiffany","Sasha","Kathy","Frances","Rihanna","Ally","Deanna","Payton","Kenzie","Carla","Melody","Jacey","Carmen","Eileen","Elaina","Avery","Katelynn","Kendra","Kyra","Hannah","Cadence","Amanda","Karlee","Paulina","Alexis","Haley","Jaylin","Carson","Iyana","Halle","Elsa","Liana","Elizabeth","Danielle","Crystal","April","Taylor","Delaney","Kaylyn","Jaylyn","Kate","Leslie","Amy","Jocelyn","Kara","Olivia","Piper","Kaylynn","Patricia","Cecilia","Linda","Hazel","Jasmine","Emily","Anna","Mila","Mylie","Kylie","Meghan","Tessa","Rory","Jaiden","Bella","Alayna","Paula","Tanya","Larissa","Veronica","Lucy","Courtney","Marley","Lillian","Cynthia","Nora","Kelly","Murphy","Sydney","Mia","Melissa","Addy","Kay","Rylie","Jessica"};
        String lastNameArray[] = {"Carroll","Bradley","Rice","Vargas","Huber","Allison","Mullins","Burton","Frye","White","Bradshaw","Salas","Melendez","Mcintosh","Petersen","Shaffer","Richmond","Ferrell","Alvarado","Cameron","Cook","Horn","Mcbride","Woods","Marks","Dickson","Aguirre","Castaneda","Dyer","Hughes","Odom","Huerta","Farrell","Benson","Mcpherson","Johns","Weber","Glenn","Tran","King","Swanson","Ryan","Durham","Daniels","Ashley","Braun","Fletcher","Valenzuela","Singh","Hogan","Rosales","Fuentes","Pollard","Wyatt","Davidson","Ray","Pace","Hobbs","Rasmussen","Mclaughlin","Mcdowell","Vincent","Vaughn","Potts","Cooper","Zimmerman","Morales","Ware","Nunez","Decker","Hess","Cunningham","Everett","Mccall","Moyer","Whitehead","Gentry","Chung","Li","Hicks","Flynn","Pena","Meyer","Solomon","Branch","Conley","Mann","Howe","Roberson","Mooney","Caldwell","Figueroa","Kaiser","Koch","Zuniga","Mckinney","Lester","Lin","Lloyd","Stout"};



        Intent intent = new Intent(this, MainGame.class);

        EditText firstName = (EditText)findViewById(R.id.firstName);
        EditText lastName = (EditText)findViewById(R.id.lastName);
        ToggleButton btnMale = (ToggleButton)findViewById(R.id.btnMale);
        ToggleButton btnFemale = (ToggleButton)findViewById(R.id.btnFemale);
        TextView errorView = (TextView)findViewById(R.id.errorTextBox);

        CheckBox randomCheckbox = (CheckBox)findViewById(R.id.randomCheckbox);
        if(randomCheckbox.isChecked()){
            String genderText = maleOrFemale();
            String firstNameText = "";
            String lastNameText = randArrayTitle(lastNameArray);

            if(genderText == "Male"){
                firstNameText = randArrayTitle(firstNameMale);
            }else{
                firstNameText = randArrayTitle(firstNameFemale);
            }


            intent.putExtra("firstName", firstNameText);
            intent.putExtra("lastName", lastNameText);
            intent.putExtra("gender", genderText);

            startActivity(intent);
            return;
        }

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

    public String maleOrFemale(){
        int size = 1;
        int randomNum = (int)(Math.random() * ((size) + 1));

        if(randomNum == 1){
            return "Male";
        } else if(randomNum == 0){
            return "Female";
        }

        return "";
    }

    // Randomly returns a string in an array
    public String randArrayTitle(String[] jobTitles){

        int size = jobTitles.length - 1; // size of array


        int randomNum = (int)(Math.random() * ((size) + 1));
        return jobTitles[randomNum];

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
