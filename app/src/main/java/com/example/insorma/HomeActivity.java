package com.example.insorma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.insorma.helper.FurnitureHelper;
import com.example.insorma.helper.UserHelper;
import com.example.insorma.models.Furnitures;
import com.example.insorma.models.Users;
import com.example.insorma.viewholder_n_adapter.FurnitureAdapter;

import java.util.ArrayList;
import java.util.Vector;

public class HomeActivity extends AppCompatActivity {

    TextView loginUser;
    UserHelper userHelper;
    RecyclerView recyclerView;
    FurnitureHelper furnitureHelper;
    Vector<Furnitures> furnitures = new Vector<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        userHelper = new UserHelper(this);

        Users user = getIntent().getParcelableExtra("user");

        loginUser = findViewById(R.id.tvLoginUser);
        loginUser.setText(user.getUserUName());

        recyclerView = findViewById(R.id.rvFurnitures);
        furnitureHelper = new FurnitureHelper(this);

        //dummy data test
        for(int i =0;i<10;i++){
            furnitures.add(new Furnitures("kursi"+i));
        }
        FurnitureAdapter adapter = new FurnitureAdapter(this,furnitures);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_navigbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        userHelper = new UserHelper(this);
        Users user = getIntent().getParcelableExtra("user");
        switch (item.getItemId()){
            case R.id.home:
                Intent goHome = new Intent(this, HomeActivity.class);
                goHome.putExtra("user", user);
                this.finish();
                startActivity(goHome);
                break;
            case R.id.history:
                Intent goHistory = new Intent(this, HistoryActivity.class);
                goHistory.putExtra("user", user);
                startActivity(goHistory);
                break;
            case R.id.about:
                Intent goAbout = new Intent(this, AboutActivity.class);
                startActivity(goAbout);
                break;
            case R.id.profile:
                Intent goProfile = new Intent(this, ProfileActivity.class);
                goProfile.putExtra("user", user);
                startActivity(goProfile);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}