package com.example.android.lifesim;

public class Sickness {
    private String title;
    private String description;
    private int damagePerTurn;


    Sickness(String title){
        this.title = title;

        switch (title){
            case "Influenza":
                this.description = "Normal flu, doc says you should be fine";
                this.damagePerTurn = 6;
                break;
            case "Common Cold":
                this.description = "Common cold, easily beatable with proper medicine";
                this.damagePerTurn = 3;
                break;
            case "Pneumonia":
                this.description = "Brought on by cold weather, pneumonia";
                this.damagePerTurn = 10;
                break;
        }
    }

    // Getter functions
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public int getDamagePerTurn() { return damagePerTurn; }
}
