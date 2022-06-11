package com.example.insorma.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.insorma.R;
import com.example.insorma.models.Furnitures;
import com.example.insorma.models.Transcations;

import java.util.Vector;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryViewHolder> {
    Context context;
    Vector<Transcations> transcations = new Vector<>();
    Furnitures furnitures;

    public HistoryAdapter(Context context, Vector<Transcations> transcations, Furnitures furnitures) {
        this.context = context;
        this.transcations = transcations;
        this.furnitures = furnitures;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.history,parent,false);
        HistoryViewHolder viewHolder = new HistoryViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        holder.transactionid.setText(transcations.get(position).getTransID());
        holder.tanggal.setText(transcations.get(position).getTransDate());
        holder.namaproduk.setText(furnitures.getFurnitureName());
        holder.totalprice.setText(furnitures.getFurniturePrice()*transcations.get(position).getTransQuant());
        holder.quantity.setText(transcations.get(position).getTransQuant());
        Glide.with(context)
                .load(furnitures.getFurnitureImage())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return transcations.size();
    }
}
