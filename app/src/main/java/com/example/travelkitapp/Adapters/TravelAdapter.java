package com.example.travelkitapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelkitapp.Interfaces.TravelCallback;
import com.example.travelkitapp.Models.OnItemClick;
import com.example.travelkitapp.Models.Travel;
import com.example.travelkitapp.R;
import com.example.travelkitapp.Utilities.ImageLoader;
import com.example.travelkitapp.Utilities.TimeFormatter;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class TravelAdapter extends RecyclerView.Adapter<TravelAdapter.TravelViewHolder> {
    private Context context;
    private ArrayList<Travel> travels;
    private OnItemClick onItemClick;


    public TravelAdapter(Context context, ArrayList<Travel> travels, OnItemClick onItemClick) {
        this.context = context;
        this.travels = travels;
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public TravelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horisontal_travel_item,parent,false);
        return new TravelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TravelViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Travel travel = getItem(position);

        ImageLoader.getInstance().load(travel.getImageUri(),holder.ivPic);
        holder.travel_LBL_name.setText(travel.getTravelName());
        holder.travel_LBL_year.setText(String.valueOf(travel.getStartTripDate()));
        holder.travel_LBL_duration.setText(travel.getDuration());
        holder.travel_LBL_continent.setText(travel.getCountries());
        if (travel.isFavorite())
            holder.travel_IMG_favorite.setImageResource(R.drawable.heart);
        else
            holder.travel_IMG_favorite.setImageResource(R.drawable.empty_heart);

        holder.ivDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick.onClick("details", position);
            }
        });

        holder.travel_IMG_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick.onClick("favorite", position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return travels == null ? 0 : travels.size();
    }

    private Travel getItem (int position) {
        return travels.get(position);
    }

    public class TravelViewHolder extends RecyclerView.ViewHolder {
        private ShapeableImageView travel_IMG_favorite;
        private MaterialTextView travel_LBL_name;
        private MaterialTextView travel_LBL_year;
        private MaterialTextView travel_LBL_duration;
        private MaterialTextView travel_LBL_continent;
        ShapeableImageView ivPic, ivDetails;

        public TravelViewHolder(@NonNull View itemView) {
            super(itemView);
            ivDetails = itemView.findViewById(R.id.ivDetails);
            ivPic = itemView.findViewById(R.id.ivPic);
            travel_IMG_favorite = itemView.findViewById(R.id.travel_IMG_favorite);
            travel_LBL_name = itemView.findViewById(R.id.travel_LBL_name);
            travel_LBL_year = itemView.findViewById(R.id.travel_LBL_year);
            travel_LBL_duration = itemView.findViewById(R.id.travel_LBL_duration);
            travel_LBL_continent = itemView.findViewById(R.id.travel_LBL_continent);
        }
    }
}

