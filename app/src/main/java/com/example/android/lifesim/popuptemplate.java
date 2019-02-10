package com.example.android.lifesim;

import java.util.Random;

public class popuptemplate {
    private String title;
    private String description;
    private String shortdesc;
    private String option1; // "You chose to"
    private String option2; // "You chose to"
    private int yesHappy, noHappy;
    private int yesHealth, noHealth;
    private int wealthEffect, wealthEffectNo;

    public popuptemplate(String title){
        this.title = title;

        switch (title){
            case "Marijuana":
                this.description = "Your friend offers you marijuana";
                this.option1 = "Take it";
                this.option2 = "Decline";
                this.yesHappy = 10;
                this.yesHealth = -5;
                this.noHappy = -10;
                this.noHealth = 0;
                this.wealthEffectNo = 0;
                this.wealthEffect = 0;
                break;
            case "Bullying":
                this.description = "You see a kid being picked on in the hallway";
                this.option1 = "Help him";
                this.option2 = "Walk away";
                this.yesHappy = 10;
                this.yesHealth = 0;
                this.noHappy = -10;
                this.noHealth = 0;
                this.wealthEffectNo = 0;
                this.wealthEffect = 0;
                break;
            case "Amusement Park":
                this.description = "Your friend offers to take you to an amusement park";
                this.option1 = "Go";
                this.option2 = "Stay home";
                this.yesHappy = 15;
                this.yesHealth = 0;
                this.noHappy = -15;
                this.noHealth = 0;
                this.wealthEffect = 0;
                this.wealthEffectNo = 0;
                break;
            case "Grandma":
                this.description = "Your grandma accidentally sent you two birthday cards";
                this.option1 = "Keep the money";
                this.option2 = "Give it back";
                this.yesHappy = -5;
                this.yesHealth = 0;
                this.noHealth = 0;
                this.noHappy = 5;
                this.wealthEffect = 100;
                this.wealthEffectNo = 50;
                break;
            case "Bicycle":
                this.description = "You crashed your bike and hit your head";
                this.option1 = "Go to the ER";
                this.option2 = "Keep riding";
                this.yesHappy = 0;
                this.yesHealth = 0;
                this.noHappy = -5;
                this.noHealth = -20;
                this.wealthEffect = 0;
                this.wealthEffectNo = 0;
                break;
            case "Study":
                this.description = "You forgot to study for your test";
                this.option1 = "Cheat";
                this.option2 = "Take test without cheating";
                this.yesHealth = 0;
                this.yesHappy = -5;
                this.noHealth = 0;
                this.noHappy = 5;
                this.wealthEffect = 0;
                this.wealthEffectNo = 0;
                break;
            case "Homeless":
                this.description = "You came across a homeless person";
                this.option1 = "Give him $10";
                this.option2 = "Walk on";
                this.yesHealth = 0;
                this.yesHappy = 10;
                this.noHealth = 0;
                this.noHappy = -10;
                this.wealthEffect = -10;
                this.wealthEffectNo = 0;
                break;
            case "Cousin":
                this.description = "Your cousin you haven't seen in years called";
                this.option1 = "Go to lunch with your cousin ($15)";
                this.option2 = "Ignore your cousin";
                this.yesHappy = 15;
                this.yesHealth =0;
                this.noHappy = -5;
                this.noHealth = 0;
                this.wealthEffect = -15;
                this.wealthEffectNo = 0;
                break;
            case "Horse Race":
                this.description = "You know a horse race is going to be rigged";
                this.option1 = "Bet $500";
                this.option2 = "Don't bet. That's wrong.";
                this.yesHappy = 0;
                this.yesHealth = 0;
                this.noHealth = 0;
                this.noHappy = 5;
                this.wealthEffect = 1000;
                this.wealthEffectNo = 0;
                break;

        }

    }

    // Returns a random number in between a min/max
    int randomNumberInBetweenMaxMin(int min, int max) {
        return (new Random()).nextInt((max - min) + 1) + min;

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
    public int getWealthEffect(){return wealthEffect;}
    public int getWealthEffectNo(){return wealthEffectNo;}


    // Setters

}
