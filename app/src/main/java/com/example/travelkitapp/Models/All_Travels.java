package com.example.travelkitapp.Models;

import java.util.HashMap;

public class All_Travels {
    private HashMap<String,Travel> allTravels = new HashMap<>();

    public All_Travels() {
    }


    public HashMap<String, Travel> getAllTravels() {
        return allTravels;
    }

    public All_Travels setAllTravels(HashMap<String, Travel> allTravels) {
        this.allTravels = allTravels;
        return this;
    }
}
