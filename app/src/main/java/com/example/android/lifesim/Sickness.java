package com.example.android.lifesim;

import java.io.Serializable;

public class Sickness implements Serializable {
    private String title;
    private String description;
    private int damagePerTurn;
    private int happyDamagePerTurn;
    private int yearsWith = 0;
    private double costToTreat;


    Sickness(String title){
        this.title = title;

        switch (title){
            case "Influenza":
                this.description = "Normal flu, doc says you should be fine";
                this.damagePerTurn = 6;
                this.happyDamagePerTurn = 12;
                this.costToTreat = 40;
                break;
            case "Common Cold":
                this.description = "Common cold, easily beatable with proper medicine";
                this.damagePerTurn = 3;
                this.happyDamagePerTurn = 6;
                this.costToTreat = 0;
                break;
            case "Pneumonia":
                this.description = "Brought on by cold weather, pneumonia";
                this.damagePerTurn = 10;
                this.happyDamagePerTurn = 9;
                this.costToTreat = 235;
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

    // Setter functions
    public void setYearsWith(int yearsWith){ this.yearsWith = yearsWith; }

    // Adds a year to the sickness
    public void addYearToSickness(){
        this.yearsWith += 1;
    }


}
