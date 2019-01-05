package com.example.android.lifesim;

public class Person {
    private String name;
    private int age;
    private double bankBalance;
    private int happiness;
    private int health;

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

    public void upAge() {
        this.age += 1;
    }
}
