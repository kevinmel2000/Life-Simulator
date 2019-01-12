package com.example.android.lifesim;

public class popuptemplate {
    private String title;
    private String description;
    private String shortdesc;
    private String option1;
    private String option2;
    private int yesHappy, noHappy;
    private int yesHealth, noHealth;

    public popuptemplate(String title){
        this.title = title;

        switch (title){
            case "Marijuana":
                this.description = "Your friend offers you marijuana, will you take it or not?";
                this.option1 = "Take it";
                this.option2 = "Don't take it";
                this.shortdesc = "take the marijuana";
                this.yesHappy = 10;
                this.yesHealth = -5;
                this.noHappy = -10;
                this.noHealth = 0;
                break;
            case "Bullying":
                this.description = "You see a kid being picked on in the hallway, will you step in?";
                this.option1 = "Help him";
                this.option2 = "Walk away";
                this.shortdesc = "help the kid getting bullied";
                this.yesHappy = 10;
                this.yesHealth = 0;
                this.noHappy = -10;
                this.noHealth = 0;
                break;
            case "Amusement Park":
                this.description = "Your friend offers to take you to an amusement park with him, do you go?";
                this.option1 = "Yes, go";
                this.option2 = "No, stay home";
                this.shortdesc = "go to the amusement park with your friend";
                this.yesHappy = 15;
                this.yesHealth = 0;
                this.noHappy = -15;
                this.noHealth = 0;
                break;

        }

    }

    // Getters
    public String getTitle() { return title; }
    public String getDescription() {
        return description;
    }
    public String getShortdesc() {
        return shortdesc;
    }
    public String getOption1() {
        return option1;
    }
    public String getOption2() {
        return option2;
    }
    public int getYesHappy() {
        return yesHappy;
    }
    public int getNoHappy() {
        return noHappy;
    }
    public int getYesHealth() {
        return yesHealth;
    }
    public int getNoHealth() {
        return noHealth;
    }


    // Setters

}
