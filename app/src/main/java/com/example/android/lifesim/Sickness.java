package com.example.android.lifesim;

import java.io.Serializable;

public class Sickness implements Serializable {
    private String title;
    private String description;
    private int damagePerTurn;
    private int happyDamagePerTurn;
    private int yearsWith;
    private double costToTreat;
    private String type = "physical";


    Sickness(String title){
        this.title = title;

        switch (title){
            case "Influenza":
                this.description = "the normal flu, doc says you should be fine if you pay him a visit.\n";
                this.damagePerTurn = 6;
                this.happyDamagePerTurn = 12;
                this.costToTreat = 40;
                this.yearsWith = 0;
                break;
            case "Common Cold":
                this.description = "an easy fix by going to see a doctor\n";
                this.damagePerTurn = 3;
                this.happyDamagePerTurn = 6;
                this.costToTreat = 10;
                this.yearsWith = 0;
                break;
            case "Pneumonia":
                this.description = "brought on by cold weather, you should see a doctor for this.\n";
                this.damagePerTurn = 10;
                this.happyDamagePerTurn = 9;
                this.costToTreat = 235;
                this.yearsWith = 0;
                break;
            case "a Gambling Addiction":
                this.description = "Excessive or uncontrollable gambling to an unhealthy extent. You should see a therapist about this.\n";
                this.damagePerTurn = 0;
                this.happyDamagePerTurn = 15;
                this.costToTreat = 100;
                this.yearsWith = 0;
                this.type = "mental";
                break;
            case "a Sprained Ankle":
                this.description = "an injury that occurs when the ankle rolls, twists, or turns in an awkward way.\n";
                this.damagePerTurn = 2;
                this.happyDamagePerTurn = 10;
                this.costToTreat = 49;
                this.yearsWith = 0;
                break;
            case "a Broken Arm":
                this.description = "a complete or partial break in an arm bone.\n";
                this.damagePerTurn = 6;
                this.happyDamagePerTurn = 12;
                this.costToTreat = 210;
                this.yearsWith = 0;
                break;
            case "a Torn ACL":
                this.description = "a torn anterior cruciate ligament in the knee. You need surgery!\n";
                this.damagePerTurn = 20;
                this.happyDamagePerTurn = 15;
                this.costToTreat = 6852;
                this.yearsWith = 0;
                break;
        }
    }



    // Getter functions
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public int getDamagePerTurn() { return damagePerTurn; }
    public int getHappyDamagePerTurn() { return happyDamagePerTurn; }
    public int getYearsWith() { return yearsWith; }
    public double getCostToTreat() { return costToTreat; }
    public String getType() { return type; }

    // Setter functions
    public void setYearsWith(int yearsWith){ this.yearsWith = yearsWith; }

    // Adds a year to the sickness
    public void addYearToSickness(){
        this.yearsWith += 1;
    }



}
