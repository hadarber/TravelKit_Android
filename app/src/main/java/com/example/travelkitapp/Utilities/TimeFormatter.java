package com.example.travelkitapp.Utilities;

public class TimeFormatter {

    public static String getTimeFormatted(int duration) {
        String dd = "0" + duration;
        String days = "days";
        return dd + " " + days;
    }
}
