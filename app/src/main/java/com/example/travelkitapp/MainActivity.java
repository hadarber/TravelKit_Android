package com.example.travelkitapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.travelkitapp.Adapters.TravelAdapter;
import com.example.travelkitapp.Interfaces.TravelCallback;
import com.example.travelkitapp.Models.Travel;
import com.example.travelkitapp.Utilities.DataManager;
import com.example.travelkitapp.databinding.ActivityMainBinding;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ProgressDialog progressDialog;
    FirebaseAuth auth;
    DatabaseReference dbRefTravel;
    ArrayList<Travel> list = new ArrayList<>();
    TravelAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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

        binding.addTravel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, EditorActivity.class));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        progressDialog.show();
        dbRefTravel.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint({"NotifyDataSetChanged", "SetTextI18n"})
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    list.clear();
                    progressDialog.dismiss();
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        try {
                            Travel model = ds.getValue(Travel.class);
                            if (Objects.equals(model.getUserId(), auth.getCurrentUser().getUid())) {
                                list.add(model);
                            }
                        } catch (DatabaseException e) {
                            e.printStackTrace();
                        }
                    }

                    setAdapter();

                    if (list.isEmpty()) {
                        binding.tvNoDataFound.setVisibility(View.VISIBLE);
                        binding.rvTravels.setVisibility(View.GONE);
                    } else {
                        binding.tvNoDataFound.setVisibility(View.GONE);
                        binding.rvTravels.setVisibility(View.VISIBLE);
                    }
                } else {
                    progressDialog.dismiss();
                    binding.tvNoDataFound.setVisibility(View.VISIBLE);
                    binding.rvTravels.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setAdapter() {
        adapter = new TravelAdapter(this, list, (from, pos) -> {
            Travel model = list.get(pos);
            if (Objects.equals(from, "details")) {
                Intent intent = new Intent(this, TripDetailsActivity.class);
                intent.putExtra("data", model);
                startActivity(intent);
            }else{
                if (model.isFavorite()){
                    list.get(pos).setFavorite(false);
                    dbRefTravel.child(model.getTravelId()).child("favorite").setValue(false);
                }else{
                    list.get(pos).setFavorite(true);
                    dbRefTravel.child(model.getTravelId()).child("favorite").setValue(true);
                }
                adapter.notifyDataSetChanged();
            }
        });
        binding.rvTravels.setLayoutManager(new LinearLayoutManager(this));
        binding.rvTravels.setAdapter(adapter);
    }

}