package com.example.travelkitapp;

import android.app.Application;

import com.example.travelkitapp.Utilities.ImageLoader;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoader.initImageLoader(this);
    }
}
