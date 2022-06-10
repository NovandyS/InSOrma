package com.example.insorma.recyclerview;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.insorma.R;

public class FurnitureViewHolder extends RecyclerView.ViewHolder {

    ImageView ivFurniture;
    TextView tvFurnitureName, tvFureniturePrice, tvFurnitureRating;
    public FurnitureViewHolder(@NonNull View itemView) {
        super(itemView);
        ivFurniture = itemView.findViewById(R.id.imageView);
        tvFurnitureName = itemView.findViewById(R.id.tvFurnitureName);
        tvFureniturePrice = itemView.findViewById(R.id.tvFurniturePrice);
        tvFurnitureRating = itemView.findViewById(R.id.tvRating);

    }
}