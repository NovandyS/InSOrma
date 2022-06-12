package com.example.insorma.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.insorma.HistoryActivity;
import com.example.insorma.R;
import com.example.insorma.helper.FurnitureHelper;
import com.example.insorma.models.Furnitures;
import com.example.insorma.models.Transactions;

import java.util.Vector;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryViewHolder> {
    Context context;
    Vector<Transactions> transactions;
    FurnitureHelper furnitureHelper;

    public HistoryAdapter(Context context, Vector<Transactions> transactions) {
        this.context = context;
        this.transactions = transactions;
        furnitureHelper = new FurnitureHelper(context);

        if (transactions.isEmpty()){
            HistoryActivity.nodatashow();
        }
        else{
            HistoryActivity.nodatahide();
        }
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
        Furnitures furnitures = furnitureHelper.dbReadFurn(transactions.get(position).getProdID());

        holder.transactionid.setText(transactions.get(position).getTransID().toString());
        holder.tanggal.setText(transactions.get(position).getTransDate());
        holder.namaproduk.setText(furnitures.getFurnitureName());
        Integer total = furnitures.getFurniturePrice()*transactions.get(position).getTransQuant();
        holder.totalprice.setText("$ " + total.toString());
        holder.quantity.setText(transactions.get(position).getTransQuant().toString());
        Glide.with(context)
                .load(furnitures.getFurnitureImage())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }
}
