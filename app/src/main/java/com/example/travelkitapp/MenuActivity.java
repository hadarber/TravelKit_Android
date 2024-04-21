package com.example.travelkitapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travelkitapp.Models.HelperClass;
import com.example.travelkitapp.auth.SignInActivity;
import com.example.travelkitapp.databinding.ActivityMenuBinding;

public class MenuActivity extends AppCompatActivity {
    ActivityMenuBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViews();
    }

    @SuppressLint("SetTextI18n")
    private void initViews() {

        binding.tvWelcome.setText("Welcome, "+ HelperClass.users.getName());
        binding.cvManageTravels.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
        });
        binding.cvFavorites.setOnClickListener(v -> {
            startActivity(new Intent(this, FavoritesActivity.class));
        });
        binding.cvLogout.setOnClickListener(v -> {
            startActivity(new Intent(this, SignInActivity.class));
            finishAffinity();
        });

    }
}