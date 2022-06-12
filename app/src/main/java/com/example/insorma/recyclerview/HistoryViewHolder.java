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
        totalprice = itemView.findViewById(R.id.tvTotal);
        namaproduk = itemView.findViewById(R.id.tvProdNameHistory);
        quantity = itemView.findViewById(R.id.tvQuantity);
        tanggal = itemView.findViewById(R.id.tvTransDate);
        transactionid = itemView.findViewById(R.id.tvTransID);
        imageView = itemView.findViewById(R.id.ivHistory);
    }
}
