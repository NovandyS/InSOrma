package com.example.insorma.viewholder_n_adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.TextView;

import com.example.insorma.R;

public class FurnitureViewHolder extends RecyclerView.ViewHolder {

    TextView tvFurnitureName,tvFureniturePrice,tvFurnitureRating;
    public FurnitureViewHolder(@NonNull View itemView) {
        super(itemView);
        tvFurnitureName = itemView.findViewById(R.id.tvFurnitureName);
        tvFureniturePrice = itemView.findViewById(R.id.tvFurniturePrice);
        tvFurnitureRating = itemView.findViewById(R.id.tvRating);

    }
}