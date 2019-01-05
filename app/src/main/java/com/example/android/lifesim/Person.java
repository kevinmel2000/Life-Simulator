package com.example.android.lifesim;

public class Person {
    private String name;
    private int age;

    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }



    /*GETTER FUNCTIONS*/
    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
}
