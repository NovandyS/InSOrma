package com.example.insorma.recyclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.insorma.R;

public class HistoryViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView totalprice, namaproduk,quantity,tanggal,transactionid;

    public HistoryViewHolder(@NonNull View itemView) {
        super(itemView);
        totalprice = itemView.findViewById(R.id.tvFurnitureTotalPrice);
        namaproduk = itemView.findViewById(R.id.tvFurnitureNamehistory);
        quantity = itemView.findViewById(R.id.tvQuantity);
        tanggal = itemView.findViewById(R.id.transactiondate);
        transactionid = itemView.findViewById(R.id.transactionid);
        imageView = itemView.findViewById(R.id.imageViewhistory);
    }
}
