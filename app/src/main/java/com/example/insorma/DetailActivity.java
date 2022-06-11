package com.example.insorma;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.insorma.models.Furnitures;

public class DetailActivity extends AppCompatActivity {

    TextView tvfurniturename,Tvfurnitureprice,Tvfurniturerating,Tvfurnituredesc;
    ImageView imageView;
    Button btnbuy;
    static Furnitures furniture;
    public static void getFurniture(Furnitures furnitures) {
        furniture=furnitures;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvfurniturename = findViewById(R.id.furnitureName);
        Tvfurnitureprice = findViewById(R.id.furniturePrice);
        Tvfurniturerating = findViewById(R.id.furnitureRating);
        Tvfurnituredesc = findViewById(R.id.furnitureDesc);
        imageView = findViewById(R.id.ivDetail);

        tvfurniturename.setText(furniture.getFurnitureName().toString());
        Tvfurnitureprice.setText("$ " + furniture.getFurniturePrice().toString());
        Tvfurniturerating.setText(furniture.getFurnitureRating().toString());
        Tvfurnituredesc.setText(furniture.getFurnitureDesc().toString());
        Glide.with(this)
                .load(furniture.getFurnitureImage())
                .into(imageView);

    }
}