package com.example.android.lifesim;

import java.util.Random;

public class Person {
    String[] sicknessNames = {"Influenza", "Common Cold", "Pneumonia"};

    // Attributes
    private String name;
    private int age;
    private double bankBalance;
    private int happiness;
    private int health;
    private Sickness sickness;

    public Person(String name, int age, double bankBalance, int happiness, int health){
        this.name = name;
        this.age = age;
        this.bankBalance = bankBalance;
        this.happiness = happiness;
        this.health = health;
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

    /*SETTER FUNCTIONS*/
    public void setHealth(int health){ this.health = health; }

    // makes person's age increase by one
    public void upAge() {
        this.age += 1;
    }

    // Returns a random number in between a min/max
    int randomNumberInBetweenMaxMin(int min, int max){
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
        if(this.getAge() < 12){
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
            }
        }
    }

}
