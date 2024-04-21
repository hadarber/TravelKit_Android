package com.example.travelkitapp.Utilities;

import android.text.NoCopySpan;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import com.example.travelkitapp.Models.Travel;


public class DataManager {


//    public static ArrayList<Travel> getTravels() {
//        ArrayList<Travel> travels = new ArrayList<>();
//
//        travels.add(new Travel()
//                .setTravelName("Paris")
//                .setContinents(new ArrayList<String>(Arrays.asList("Europe")))
//                .setTravelers(new ArrayList<String>(Arrays.asList("Gal", "Niv", "Samuel")))
//                .setDuration(5)
//                .setPicture("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/qhb1qOilapbapxWQn9jtRCMwXJF.jpg")
//                .setOverview("A romantic city, a trip that is especially suitable for a couple. It is recommended to stay at least 4 nights. Especially suitable for marriage proposals")
//                .setRating(7.2f)
//                .setStartTripDate(LocalDate.parse("13/12/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
//
//        travels.add(new Travel()
//                .setTravelName("New York")
//                .setContinents(new ArrayList<String>(Arrays.asList("USA")))
//                .setTravelers(new ArrayList<String>(Arrays.asList("Yakov", "Maya", "Samuel")))
//                .setDuration(14)
//                .setPicture("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/qhb1qOilapbapxWQn9jtRCMwXJF.jpg")
//                .setOverview("A perfect trip for the whole family, many attractions such as an amusement park, a rope park, shopping centers and more. Suitable for at least 8 days.")
//                .setRating(7.2f)
//                .setStartTripDate(LocalDate.parse("13/12/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
//
//        travels.add(new Travel()
//                .setTravelName("Brazil")
//                .setContinents(new ArrayList<String>(Arrays.asList("USA")))
//                .setTravelers(new ArrayList<String>(Arrays.asList("Yakov", "Maya", "Samuel")))
//                .setDuration(14)
//                .setPicture("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/qhb1qOilapbapxWQn9jtRCMwXJF.jpg")
//                .setOverview("A perfect trip for the whole family, many attractions such as an amusement park, a rope park, shopping centers and more. Suitable for at least 8 days.")
//                .setRating(7.2f)
//                .setStartTripDate(LocalDate.parse("13/12/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
//
//        travels.add(new Travel()
//                .setTravelName("Israel")
//                .setContinents(new ArrayList<String>(Arrays.asList("USA")))
//                .setTravelers(new ArrayList<String>(Arrays.asList("Yakov", "Maya", "Samuel")))
//                .setDuration(14)
//                .setPicture("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/qhb1qOilapbapxWQn9jtRCMwXJF.jpg")
//                .setOverview("A perfect trip for the whole family, many attractions such as an amusement park, a rope park, shopping centers and more. Suitable for at least 8 days.")
//                .setRating(7.2f)
//                .setStartTripDate(LocalDate.parse("13/12/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
//
//        travels.add(new Travel()
//                .setTravelName("Russia")
//                .setContinents(new ArrayList<String>(Arrays.asList("USA")))
//                .setTravelers(new ArrayList<String>(Arrays.asList("Yakov", "Maya", "Samuel")))
//                .setDuration(14)
//                .setPicture("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/qhb1qOilapbapxWQn9jtRCMwXJF.jpg")
//                .setOverview("A perfect trip for the whole family, many attractions such as an amusement park, a rope park, shopping centers and more. Suitable for at least 8 days.")
//                .setRating(7.2f)
//                .setStartTripDate(LocalDate.parse("13/12/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
//
//        travels.add(new Travel()
//                .setTravelName("London")
//                .setContinents(new ArrayList<String>(Arrays.asList("USA")))
//                .setTravelers(new ArrayList<String>(Arrays.asList("Yakov", "Maya", "Samuel")))
//                .setDuration(14)
//                .setPicture("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/qhb1qOilapbapxWQn9jtRCMwXJF.jpg")
//                .setOverview("A perfect trip for the whole family, many attractions such as an amusement park, a rope park, shopping centers and more. Suitable for at least 8 days.")
//                .setRating(7.2f)
//                .setStartTripDate(LocalDate.parse("13/12/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
//
//        travels.add(new Travel()
//                .setTravelName("טיול אחרי צבא")
//                .setContinents(new ArrayList<String>(Arrays.asList("Brazil","Argentina")))
//                .setTravelers(new ArrayList<String>(Arrays.asList("Yakov", "Maya", "Samuel")))
//                .setDuration(14)
//                .setPicture("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/qhb1qOilapbapxWQn9jtRCMwXJF.jpg")
//                .setOverview("A perfect trip for the whole family, many attractions such as an amusement park, a rope park, shopping centers and more. Suitable for at least 8 days.")
//                .setRating(7.2f)
//                .setStartTripDate(LocalDate.parse("13/12/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
//
//        travels.add(new Travel()
//                .setTravelName("טיול בר מצווה לברק")
//                .setContinents(new ArrayList<String>(Arrays.asList("New york","Las vegas")))
//                .setTravelers(new ArrayList<String>(Arrays.asList("Yakov", "Maya", "Samuel")))
//                .setDuration(14)
//                .setPicture("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/qhb1qOilapbapxWQn9jtRCMwXJF.jpg")
//                .setOverview("A perfect trip for the whole family, many attractions such as an amusement park, a rope park, shopping centers and more. Suitable for at least 8 days.")
//                .setRating(7.2f)
//                .setStartTripDate(LocalDate.parse("13/12/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
//
//        travels.add(new Travel()
//                .setTravelName("טיול מאורגן לונדון-פריז")
//                .setContinents(new ArrayList<String>(Arrays.asList("England","France")))
//                .setTravelers(new ArrayList<String>(Arrays.asList("Yakov", "Maya", "Samuel")))
//                .setDuration(14)
//                .setPicture("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/qhb1qOilapbapxWQn9jtRCMwXJF.jpg")
//                .setOverview("A perfect trip for the whole family, many attractions such as an amusement park, a rope park, shopping centers and more. Suitable for at least 8 days.")
//                .setRating(7.2f)
//                .setStartTripDate(LocalDate.parse("13/12/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
//
//        travels.add(new Travel()
//                .setTravelName("ירח דבש")
//                .setContinents(new ArrayList<String>(Arrays.asList("Thailand","Viatnam")))
//                .setTravelers(new ArrayList<String>(Arrays.asList("Yakov", "Maya", "Samuel")))
//                .setDuration(14)
//                .setPicture("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/qhb1qOilapbapxWQn9jtRCMwXJF.jpg")
//                .setOverview("A perfect trip for the whole family, many attractions such as an amusement park, a rope park, shopping centers and more. Suitable for at least 8 days.")
//                .setRating(7.2f)
//                .setStartTripDate(LocalDate.parse("13/12/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
//
//        travels.add(new Travel()
//                .setTravelName("מסיבת רווקות לגלוש")
//                .setContinents(new ArrayList<String>(Arrays.asList("Cyrpus")))
//                .setTravelers(new ArrayList<String>(Arrays.asList("Yakov", "Maya", "Samuel")))
//                .setDuration(14)
//                .setPicture("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/qhb1qOilapbapxWQn9jtRCMwXJF.jpg")
//                .setOverview("A perfect trip for the whole family, many attractions such as an amusement park, a rope park, shopping centers and more. Suitable for at least 8 days.")
//                .setRating(7.2f)
//                .setStartTripDate(LocalDate.parse("13/12/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
//
//        travels.add(new Travel()
//                .setTravelName("מסיבת רווקות לאופיר")
//                .setContinents(new ArrayList<String>(Arrays.asList("Bukarest")))
//                .setTravelers(new ArrayList<String>(Arrays.asList("Yakov", "Maya", "Samuel")))
//                .setDuration(14)
//                .setPicture("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/qhb1qOilapbapxWQn9jtRCMwXJF.jpg")
//                .setOverview("A perfect trip for the whole family, many attractions such as an amusement park, a rope park, shopping centers and more. Suitable for at least 8 days.")
//                .setRating(7.2f)
//                .setStartTripDate(LocalDate.parse("13/12/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
//
//        travels.add(new Travel()
//                .setTravelName("New York")
//                .setContinents(new ArrayList<String>(Arrays.asList("USA")))
//                .setTravelers(new ArrayList<String>(Arrays.asList("Yakov", "Maya", "Samuel")))
//                .setDuration(14)
//                .setPicture("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/qhb1qOilapbapxWQn9jtRCMwXJF.jpg")
//                .setOverview("A perfect trip for the whole family, many attractions such as an amusement park, a rope park, shopping centers and more. Suitable for at least 8 days.")
//                .setRating(7.2f)
//                .setStartTripDate(LocalDate.parse("13/12/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
//
//        travels.add(new Travel()
//                .setTravelName("New York")
//                .setContinents(new ArrayList<String>(Arrays.asList("USA")))
//                .setTravelers(new ArrayList<String>(Arrays.asList("Yakov", "Maya", "Samuel")))
//                .setDuration(14)
//                .setPicture("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/qhb1qOilapbapxWQn9jtRCMwXJF.jpg")
//                .setOverview("A perfect trip for the whole family, many attractions such as an amusement park, a rope park, shopping centers and more. Suitable for at least 8 days.")
//                .setRating(7.2f)
//                .setStartTripDate(LocalDate.parse("13/12/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
//
//        return travels;
//    }


}