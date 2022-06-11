package com.example.insorma.recyclerview;

import android.content.Context;
import android.util.Log;
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
    OnItemClickListener itemClick;


    public FurnitureAdapter(Context context, Vector<Furnitures> listFurnitures, OnItemClickListener listener){
        this.context = context;
        this.furnitures = listFurnitures;
        this.itemClick = listener;

        furnitureHelper = new FurnitureHelper(context);

        for (int i = 0; i < listFurnitures.size(); i++){
            furnitureHelper.dbInsert(listFurnitures.get(i));
            Log.wtf("test b",i+"a"+listFurnitures.get(i).getFurnitureName());
        }

        Vector<Furnitures> furn1 = furnitureHelper.dbRead();
//        Log.wtf("Test", String.valueOf(furn1.size()));
        for (int i = 0; i < furn1.size(); i++){
            Log.wtf("TestName", furn1.get(i).getFurnitureName() + furn1.size());
        }

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

        holder.itemView.setOnClickListener(view ->{
            itemClick.onItemClick(furnitures.get(position),position);
        });
    }

    @Override
    public int getItemCount() {
        return furnitures.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Furnitures furnitures, int position);
    }
}
