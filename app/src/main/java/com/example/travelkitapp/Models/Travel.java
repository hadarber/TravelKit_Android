package com.example.travelkitapp.Models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Travel implements Serializable {
    String travelId, userId, travelName, countries, travelers, location, startTripDate, duration, overview, imageUri;
    private float rating = 0.0f;
    private boolean isFavorite = false;

    public Travel() {
    }

    public Travel(String travelId, String userId, String travelName, String countries, String travelers, String location, String startTripDate, String duration, String overview, String imageUri, float rating, boolean isFavorite) {
        this.travelId = travelId;
        this.userId = userId;
        this.travelName = travelName;
        this.countries = countries;
        this.travelers = travelers;
        this.location = location;
        this.startTripDate = startTripDate;
        this.duration = duration;
        this.overview = overview;
        this.imageUri = imageUri;
        this.rating = rating;
        this.isFavorite = isFavorite;
    }

    public String getTravelId() {
        return travelId;
    }

    public void setTravelId(String travelId) {
        this.travelId = travelId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTravelName() {
        return travelName;
    }

    public void setTravelName(String travelName) {
        this.travelName = travelName;
    }

    public String getCountries() {
        return countries;
    }

    public void setCountries(String countries) {
        this.countries = countries;
    }

    public String getTravelers() {
        return travelers;
    }

    public void setTravelers(String travelers) {
        this.travelers = travelers;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStartTripDate() {
        return startTripDate;
    }

    public void setStartTripDate(String startTripDate) {
        this.startTripDate = startTripDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
