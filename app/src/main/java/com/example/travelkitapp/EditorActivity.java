package com.example.travelkitapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.travelkitapp.Models.All_Travels;
import com.example.travelkitapp.Models.Travel;
import com.example.travelkitapp.databinding.ActivityEditorBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.DeflaterOutputStream;

public class EditorActivity extends AppCompatActivity {
    ActivityEditorBinding binding;
    String travelName, countries, travelers, location, startTripDate, duration, overview, imageUri = "", rating;
    ProgressDialog progressDialog;
    FirebaseAuth auth;
    DatabaseReference dbRefTravel;
    StorageReference storageReference;
    Travel travelModel;
    private FusedLocationProviderClient fusedLocationClient;
    private PlacesClient placesClient;
    public static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getIntent().getExtras() != null) {
            travelModel = (Travel) getIntent().getSerializableExtra("data");
            if (travelModel != null) {
                binding.etTravelName.setText(travelModel.getTravelName());
                binding.etCountries.setText(travelModel.getCountries());
                binding.etTravelers.setText(travelModel.getTravelers());
                binding.etLocation.setText(travelModel.getLocation());
                binding.etStartDate.setText(travelModel.getStartTripDate());
                binding.etDuration.setText(travelModel.getDuration());
                binding.etOverview.setText(travelModel.getOverview());
                imageUri = travelModel.getImageUri();
                Glide.with(binding.ivImage).load(travelModel.getImageUri()).into(binding.ivImage);
                binding.ratingBar.setRating(travelModel.getRating());
                binding.tvLabel.setText("Update Travel");
                binding.btnSave.setText("Update");
            }
        }

        // Initialize Places API
        Places.initialize(getApplicationContext(), "AIzaSyD9-7VF_J-YFK2g8_UethYoeR73R_pNGtY");
        placesClient = Places.createClient(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(getString(R.string.app_name));
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        auth = FirebaseAuth.getInstance();
        dbRefTravel = FirebaseDatabase.getInstance().getReference("Travels");
        storageReference = FirebaseStorage.getInstance().getReference("TravelPictures");

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.etLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.llMain.setVisibility(View.INVISIBLE);
                binding.llSearch.setVisibility(View.VISIBLE);
                setupLocationBar();
            }
        });

        binding.ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
                startActivityForResult(Intent.createChooser(intent, "Select Travel Picture"), 123);
            }
        });

        binding.ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.llMain.setVisibility(View.VISIBLE);
                binding.llSearch.setVisibility(View.GONE);
            }
        });

        binding.etStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidated()) {
                    if (travelModel != null) {
                        updateTravel();
                    } else {
                        saveTravel();
                    }
                }
            }
        });

    }

    private void setupLocationBar() {
        List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME);
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);
        autocompleteFragment.setPlaceFields(fields); // Specify the fields to retrieve

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                binding.etLocation.setText(place.getName());
                binding.llMain.setVisibility(View.VISIBLE);
                binding.llSearch.setVisibility(View.GONE);
            }

            @Override
            public void onError(@NonNull com.google.android.gms.common.api.Status status) {
                binding.llMain.setVisibility(View.VISIBLE);
                binding.llSearch.setVisibility(View.GONE);
                Toast.makeText(EditorActivity.this, "Error: " + status.getStatusMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void updateTravel() {
        progressDialog.show();
        if (imageUri.equals(travelModel.getImageUri())) {
            Map<String, Object> update = new HashMap<String, Object>();
            update.put("travelName", travelName);
            update.put("countries", countries);
            update.put("travelers", travelers);
            update.put("location", location);
            update.put("startTripDate", startTripDate);
            update.put("duration", duration);
            update.put("overview", overview);
            update.put("rating", Float.parseFloat(rating));
            dbRefTravel.child(travelModel.getTravelId()).updateChildren(update).addOnCompleteListener(task -> {
                progressDialog.dismiss();
                showMessage("Successfully Saved");
                finish();
            }).addOnFailureListener(e -> {
                progressDialog.dismiss();
                showMessage(e.getMessage());
            });
        } else {
            Uri uriImage = Uri.parse(imageUri);
            StorageReference imageRef = storageReference.child(uriImage.getLastPathSegment());
            imageRef.putFile(uriImage).addOnSuccessListener(taskSnapshot -> imageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                String downloadUri = uri.toString();
                Map<String, Object> update = new HashMap<>();
                update.put("imageUri", downloadUri);
                update.put("travelName", travelName);
                update.put("countries", countries);
                update.put("travelers", travelers);
                update.put("location", location);
                update.put("startTripDate", startTripDate);
                update.put("duration", duration);
                update.put("overview", overview);
                update.put("rating", Float.parseFloat(rating));
                dbRefTravel.child(travelModel.getTravelId()).updateChildren(update).addOnCompleteListener(task -> {
                    progressDialog.dismiss();
                    showMessage("Successfully Saved");
                    finish();
                }).addOnFailureListener(e -> {
                    progressDialog.dismiss();
                    showMessage(e.getMessage());
                });
            }).addOnFailureListener(e -> {
                progressDialog.dismiss();
                showMessage(e.getLocalizedMessage());
            })).addOnFailureListener(e -> {
                progressDialog.dismiss();
                showMessage(e.getLocalizedMessage());
            });
        }
    }

    private void saveTravel() {
        progressDialog.show();
        Uri uriImage = Uri.parse(imageUri);
        StorageReference imageRef = storageReference.child(uriImage.getLastPathSegment());

        imageRef.putFile(uriImage).addOnSuccessListener(taskSnapshot -> imageRef.getDownloadUrl().addOnSuccessListener(uri -> {

            String downloadUri1 = uri.toString();

            String travelId = dbRefTravel.push().getKey();

            Travel model = new Travel(travelId, auth.getCurrentUser().getUid(), travelName, countries, travelers, location, startTripDate, duration, overview, downloadUri1, Float.parseFloat(rating), false);

            dbRefTravel.child(travelId).setValue(model).addOnSuccessListener(unused -> {
                showMessage("Added Successfully");
                progressDialog.dismiss();
                finish();
            }).addOnFailureListener(e -> {
                progressDialog.dismiss();
                showMessage(e.getLocalizedMessage());
            });

        }).addOnFailureListener(e -> {
            progressDialog.dismiss();
            showMessage(e.getLocalizedMessage());
        })).addOnFailureListener(e -> {
            progressDialog.dismiss();
            showMessage(e.getLocalizedMessage());
        });
    }

    private Boolean isValidated() {
        travelName = binding.etTravelName.getText().toString().trim();
        countries = binding.etCountries.getText().toString().trim();
        travelers = binding.etTravelers.getText().toString().trim();
        location = binding.etLocation.getText().toString().trim();
        startTripDate = binding.etStartDate.getText().toString().trim();
        duration = binding.etDuration.getText().toString().trim();
        overview = binding.etOverview.getText().toString().trim();
        rating = String.valueOf(binding.ratingBar.getRating());

        if (imageUri.isEmpty()) {
            showMessage("Please choose travel picture");
            return false;
        }

        if (travelName.isEmpty()) {
            showMessage("Please enter travel name");
            return false;
        }

        if (countries.isEmpty()) {
            showMessage("Please enter countries name");
            return false;
        }

        if (travelers.isEmpty()) {
            showMessage("Please enter travelers name");
            return false;
        }

        if (location.isEmpty()) {
            showMessage("Please enter location");
            return false;
        }

        if (startTripDate.isEmpty()) {
            showMessage("Please enter start trip date");
            return false;
        }

        if (duration.isEmpty()) {
            showMessage("Please enter duration");
            return false;
        }

        if (overview.isEmpty()) {
            showMessage("Please enter overview");
            return false;
        }

        if (rating.isEmpty() || rating.contains("0.0")) {
            showMessage("Please rate the travel");
            return false;
        }


        return true;
    }

    // Method to show DatePickerDialog
    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Update the TextView with the selected date
                        @SuppressLint("DefaultLocale")
                        String selectedDate = String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year);
                        binding.etStartDate.setText(selectedDate);
                    }
                },
                year, month, day);
        datePickerDialog.show();
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 123 && data != null) {
            imageUri = data.getData().toString();
            binding.ivImage.setImageURI(data.getData());
        }
    }

}