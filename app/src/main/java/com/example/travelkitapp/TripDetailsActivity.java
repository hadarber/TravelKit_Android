package com.example.travelkitapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.travelkitapp.Models.HelperClass;
import com.example.travelkitapp.Models.Travel;
import com.example.travelkitapp.Models.UserModel;
import com.example.travelkitapp.auth.SignInActivity;
import com.example.travelkitapp.databinding.ActivityMainBinding;
import com.example.travelkitapp.databinding.ActivityTripDetailsBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class TripDetailsActivity extends AppCompatActivity {
    ActivityTripDetailsBinding binding;
    ProgressDialog progressDialog;
    FirebaseAuth auth;
    DatabaseReference dbRefTravel;
    Travel previousModel;
    Travel travelModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTripDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        if (getIntent().getExtras() != null) {
            previousModel = (Travel) getIntent().getSerializableExtra("data");
        }

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(getString(R.string.app_name));
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        auth = FirebaseAuth.getInstance();
        dbRefTravel = FirebaseDatabase.getInstance().getReference("Travels");

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (travelModel != null) {
                    Intent intent = new Intent(TripDetailsActivity.this, EditorActivity.class);
                    intent.putExtra("data", travelModel);
                    startActivity(intent);
                }
            }
        });

        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (travelModel != null) {
                    dbRefTravel.child(travelModel.getTravelId()).removeValue();
                    Toast.makeText(TripDetailsActivity.this, "Successfully Deleted", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        binding.btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (travelModel != null) {
                    Intent intent = new Intent(TripDetailsActivity.this, MapActivity.class);
                    intent.putExtra("data", previousModel);
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        dbRefTravel.child(previousModel.getTravelId()).addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint({"NotifyDataSetChanged", "SetTextI18n"})
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    travelModel = snapshot.getValue(Travel.class);
                    if (travelModel != null) {
                        binding.etTravelName.setText(travelModel.getTravelName());
                        binding.etCountries.setText(travelModel.getCountries());
                        binding.etTravelers.setText(travelModel.getTravelers());
                        binding.etLocation.setText(travelModel.getLocation());
                        binding.etStartDate.setText(travelModel.getStartTripDate());
                        binding.etDuration.setText(travelModel.getDuration()+" days");
                        binding.etOverview.setText(travelModel.getOverview());
                        Glide.with(binding.ivImage).load(travelModel.getImageUri()).into(binding.ivImage);
                        binding.ratingBar.setRating(travelModel.getRating());
                    }
                }
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressDialog.dismiss();
                Toast.makeText(TripDetailsActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}