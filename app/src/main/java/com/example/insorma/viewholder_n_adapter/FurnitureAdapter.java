package com.example.insorma.viewholder_n_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.insorma.R;
import com.example.insorma.helper.FurnitureHelper;
import com.example.insorma.models.Furnitures;

import java.util.ArrayList;
import java.util.Vector;

public class FurnitureAdapter extends RecyclerView.Adapter<FurnitureViewHolder> {
    Context context;
    FurnitureHelper furnitureHelper;
    Vector<Furnitures> furnitures ;

    public FurnitureAdapter(Context context, Vector<Furnitures> Dummy){
        this.context=context;
        this.furnitures = Dummy;
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
        holder.tvFurnitureName.setText(furnitures.get(position).getFurnitureName());
//        holder.tvFurnitureRating.setText(furnitures.get(position).getFurnitureRating().toString()+"%");
//        holder.tvFureniturePrice.setText("Rp."+furnitures.get(position).getFurnitureRating().toString());

        holder.itemView.setOnClickListener(view ->{});
    }

    @Override
    public int getItemCount() {
        return furnitures.size();
    }
}
