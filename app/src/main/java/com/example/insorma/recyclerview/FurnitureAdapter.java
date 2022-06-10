package com.example.insorma.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.insorma.R;
import com.example.insorma.helper.FurnitureHelper;
import com.example.insorma.models.Furnitures;

import java.util.Vector;

public class FurnitureAdapter extends RecyclerView.Adapter<FurnitureViewHolder> {
    Context context;
    FurnitureHelper furnitureHelper;
    Vector<Furnitures> furnitures ;

    public FurnitureAdapter(Context context, Vector<Furnitures> listFurnitures){
        this.context = context;
        this.furnitures = listFurnitures;
    }
    @NonNull
    @Override
    public FurnitureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.furniture,parent,false);
        FurnitureViewHolder viewHolder = new FurnitureViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FurnitureViewHolder holder, int position) {
        Furnitures f = furnitures.get(position);
        Glide.with(context)
                .load(f.getFurnitureImage())
                .into(holder.ivFurniture);
        holder.tvFurnitureName.setText(furnitures.get(position).getFurnitureName());
        String rating = furnitures.get(position).getFurnitureRating().toString();
        holder.tvFurnitureRating.setText(rating);
        String price = "$ "+furnitures.get(position).getFurniturePrice();
        holder.tvFureniturePrice.setText(price);

        holder.itemView.setOnClickListener(view ->{});
    }

    @Override
    public int getItemCount() {
        return furnitures.size();
    }
}
