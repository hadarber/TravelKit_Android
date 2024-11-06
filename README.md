# TravelKit App - A Mobile Travel Tracker

## Description
TravelKit is an Android application designed to help users track and organize their trips. It allows users to:

- Add trip details such as name, location, duration, countries visited, and more.
- Upload photos for each trip.
- Rate trips and mark them as favorites.
- View and edit trip information easily.

The app integrates with **Firebase** for data storage and authentication, and uses the **Google Maps API** for location selection. The application provides users with a seamless and enjoyable way to remember their travels, organize their experiences, and share them with friends.

## Features
- **Firebase Realtime Database**: Stores user data and trip details.
- **Firebase Storage**: Saves photos from each trip.
- **Firebase Authentication**: Handles user registration and login.
- **Google Maps API**: Allows users to select a location and display it on the map.
- **Rating System**: Rate each trip and mark it as a favorite.
- **User Profile**: Users can manage their profile with name, email, and profile picture.
- **Trip Overview**: Display detailed information about each trip.

## Libraries & Tools Used
- **Firebase**: Realtime Database, Storage, Authentication.
- **Glide**: For loading images from the internet into ImageViews.
- **Lottie**: For animations while the app is loading.
- **Google Maps API**: For location selection and displaying on the map.
- **Binding**: For linking XML layout files to Java code.
- **Progress Dialog**: To show loading messages.
- **Toast**: For displaying short messages to the user.
- **DatePicker Dialog**: For selecting the start date of trips.
- **Android Patterns**: For email validation.
  
## Code Overview

### TravelAdapter.java

```java
package com.example.travelkitapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelkitapp.Models.Travel;
import com.example.travelkitapp.R;
import com.example.travelkitapp.Utilities.ImageLoader;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class TravelAdapter extends RecyclerView.Adapter<TravelAdapter.TravelViewHolder> {
    private Context context;
    private ArrayList<Travel> travels;

    public TravelAdapter(Context context, ArrayList<Travel> travels) {
        this.context = context;
        this.travels = travels;
    }

    @NonNull
    @Override
    public TravelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horisontal_travel_item, parent, false);
        return new TravelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TravelViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Travel travel = travels.get(position);
        ImageLoader.getInstance().load(travel.getImageUri(), holder.ivPic);
        holder.travel_LBL_name.setText(travel.getTravelName());
        holder.travel_LBL_year.setText(String.valueOf(travel.getStartTripDate()));
        holder.travel_LBL_duration.setText(travel.getDuration());
        holder.travel_LBL_continent.setText(travel.getCountries());
    }

    @Override
    public int getItemCount() {
        return travels.size();
    }

    public class TravelViewHolder extends RecyclerView.ViewHolder {
        private ShapeableImageView ivPic;
        private MaterialTextView travel_LBL_name, travel_LBL_year, travel_LBL_duration, travel_LBL_continent;

        public TravelViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPic = itemView.findViewById(R.id.ivPic);
            travel_LBL_name = itemView.findViewById(R.id.travel_LBL_name);
            travel_LBL_year = itemView.findViewById(R.id.travel_LBL_year);
            travel_LBL_duration = itemView.findViewById(R.id.travel_LBL_duration);
            travel_LBL_continent = itemView.findViewById(R.id.travel_LBL_continent);
        }
    }
}
Firebase Integration Example
java
Copy code
// Save a new travel record to Firebase
DatabaseReference database = FirebaseDatabase.getInstance().getReference("travels");
Travel newTravel = new Travel("1", "user123", "Trip to Japan", "Japan", "John Doe", "Tokyo", "2024-07-01", "7 days", "Great trip!", "image_url", 5.0f, true);
database.child(newTravel.getTravelId()).setValue(newTravel);
Firebase Authentication Example
java
Copy code
// Sign up a new user using Firebase Authentication
FirebaseAuth mAuth = FirebaseAuth.getInstance();
mAuth.createUserWithEmailAndPassword("user@example.com", "password123")
    .addOnCompleteListener(task -> {
        if (task.isSuccessful()) {
            // User registration successful
        } else {
            // Registration failed, handle error
        }
    });
Installation
Prerequisites
Android Studio (latest version).
Firebase Project setup (Realtime Database, Firebase Authentication, and Firebase Storage).
Google Maps API key for location services.
Steps to Run the Project
Clone the repository:
bash
Copy code
git clone <repository-url>
Open the project in Android Studio.
Connect your Firebase project with the app by following the instructions on Firebase console.
Ensure that the necessary permissions are added in AndroidManifest.xml (for internet access, location, etc.).
Build and run the app on an emulator or physical device.
Configuration Files
google-services.json for Firebase configuration.
Add your Google Maps API key in strings.xml.
Key Components
User Authentication
Using Firebase Authentication, the app allows users to sign up, log in, and manage their profiles. Passwords are securely stored using Firebase Authentication.

Data Storage
Realtime Database stores users' trips and related information.
Firebase Storage stores images such as photos from each trip.
Trip Management
RecyclerView is used to display trips in a list.
ImageLoader utility class is used to load images from Firebase Storage into ImageView components.
Rating System
Each trip has a rating system implemented using a simple float variable. Users can rate their trips and mark them as favorites.

