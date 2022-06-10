package com.example.insorma;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.insorma.models.Furnitures;

public class DetailActivity extends AppCompatActivity {

    static Furnitures furniture;
    public static void getFurniture(Furnitures furnitures) {
        furniture=furnitures;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }
}