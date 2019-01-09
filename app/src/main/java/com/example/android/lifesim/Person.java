package com.example.android.lifesim;

import java.io.Serializable;
import java.util.Random;

public class Person implements Serializable {
    String[] sicknessNames = {"Influenza", "Common Cold", "Pneumonia"};

    // Attributes
    private String name;
    private int age;
    private double bankBalance;
    private int happiness;
    private int health;
    private Sickness sickness;
    private boolean wentToDoctorThisTurn;
    private boolean wentToWorkoutThisTurn;
    private String sexualOrientation;

    Person(String name, int age, double bankBalance, int happiness, int health){
        this.name = name;
        this.age = age;
        this.bankBalance = bankBalance;
        this.happiness = happiness;
        this.health = health;
        this.wentToDoctorThisTurn = false;
        this.wentToWorkoutThisTurn = false;
    }


    /*GETTER FUNCTIONS*/
    public int getAge() {
        return age;
    }
    public String getName() {
        return name;
    }
    public double getBankBalance() {
        return bankBalance;
    }
    public int getHappiness() { return happiness; }
    public int getHealth() { return health; }
    public Sickness getSickness() { return sickness; }
    public boolean isWentToDoctorThisTurn(){return wentToDoctorThisTurn;}
    public boolean isWentToWorkoutThisTurn(){return wentToWorkoutThisTurn;}
    public String getSexualOrientation() { return sexualOrientation; }

    /*SETTER FUNCTIONS*/
    public void setHealth(int health){ this.health = health; }
    public void setHappiness(int happiness){this.happiness = happiness;}
    public void setSickness(Sickness sickness){this.sickness = sickness;}
    public void setBankBalance(double bankBalance){this.bankBalance = bankBalance;}
    public void setWentToDoctorThisTurn(boolean wentToDoctorThisTurn){this.wentToDoctorThisTurn = wentToDoctorThisTurn;}
    public void setWentToWorkoutThisTurn(boolean wentToWorkoutThisTurn){this.wentToWorkoutThisTurn = wentToWorkoutThisTurn;}
    public void setSexualOrientation(String sexualOrientation) { this.sexualOrientation = sexualOrientation; }

    // makes person's age increase by one
    public void upAge() {
        this.age += 1;
    }

    // Returns a random number in between a min/max
    private int randomNumberInBetweenMaxMin(int min, int max){
        return (new Random()).nextInt((max - min) + 1) + min;

    }

    // Randomly returns a string in an array
    public String randArrayTitle(String[] jobTitles){

        int size = jobTitles.length - 1; // size of array


        int randomNum = (int)(Math.random() * ((size) + 1));
        return jobTitles[randomNum];

    }




    // Possibly makes person sick, unless they already have a sickness or are under 12
    public void randomSickness(){
        if(this.getAge() < 15){
            // Can't get sick
        }
        // if the person is not sick
        else if(this.getSickness() == null){
            // 10% chance to get sick each turn
            int randNum = randomNumberInBetweenMaxMin(1, 100); // generate sickness name
            if(randNum >= 40 && randNum <= 49){ // 10% chance
                Sickness sickness = new Sickness(randArrayTitle(sicknessNames));

                // Sets their new sickness as this sickness
                this.sickness = sickness;
                this.sickness.setYearsWith(0);
            }
        }
    }


}
