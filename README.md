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
  

##Firebase Integration Example
```java
// Save a new travel record to Firebase
DatabaseReference database = FirebaseDatabase.getInstance().getReference("travels");
Travel newTravel = new Travel("1", "user123", "Trip to Japan", "Japan", "John Doe", "Tokyo", "2024-07-01", "7 days", "Great trip!", "image_url", 5.0f, true);
database.child(newTravel.getTravelId()).setValue(newTravel);


##Firebase Authentication Example
```java
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


##Configuration Files
google-services.json for Firebase configuration.
Add your Google Maps API key in strings.xml.
Key Components
User Authentication
Using Firebase Authentication, the app allows users to sign up, log in, and manage their profiles. Passwords are securely stored using Firebase Authentication.


##Data Storage
Realtime Database stores users' trips and related information.
Firebase Storage stores images such as photos from each trip.


##Trip Management
RecyclerView is used to display trips in a list.
ImageLoader utility class is used to load images from Firebase Storage into ImageView components.


##Rating System
Each trip has a rating system implemented using a simple float variable. Users can rate their trips and mark them as favorites.






