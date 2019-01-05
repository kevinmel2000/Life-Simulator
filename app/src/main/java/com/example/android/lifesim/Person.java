package com.example.android.lifesim;

public class Person {
    private String name;
    private int age;
    private double bankBalance;

    public Person(String name, int age, double bankBalance){
        this.name = name;
        this.age = age;
        this.bankBalance = bankBalance;
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
}
