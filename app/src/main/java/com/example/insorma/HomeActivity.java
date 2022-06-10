package com.example.insorma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.insorma.helper.FurnitureHelper;
import com.example.insorma.helper.UserHelper;
import com.example.insorma.models.Furnitures;
import com.example.insorma.models.Users;
import com.example.insorma.recyclerview.FurnitureAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Vector;

public class HomeActivity extends AppCompatActivity {

    TextView loginUser;
    UserHelper userHelper;
    RecyclerView recyclerView;
    FurnitureHelper furnitureHelper;
    Vector<Furnitures> listFurnitures = new Vector<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        userHelper = new UserHelper(this);

        Users user = getIntent().getParcelableExtra("user");

        loginUser = findViewById(R.id.tvLoginUser);
        loginUser.setText(user.getUserUName());

        String url = "mocki.io/v1/5f379081-2473-4494-9cc3-9e808772dc54";

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(url, response -> {
            try {
                JSONArray furnitures = response.getJSONArray("furnitures");

                for (int i = 0; i < furnitures.length(); i++){
                    JSONObject furn = furnitures.getJSONObject(i);

                    String furnName = furn.getString("product_name");
                    Double furnRating = furn.getDouble("rating");
                    Integer furnPrice = furn.getInt("price");
                    String furnImg = furn.getString("image");
                    String furnDesc = furn.getString("description");

                    Furnitures newFurn = new Furnitures(furnImg, furnName, furnRating, furnPrice, furnDesc);


                }

            } catch (JSONException e){
                e.printStackTrace();
            }
        }, error -> {
            Log.wtf("error", error.toString());
        });

        recyclerView = findViewById(R.id.rvFurnitures);
        furnitureHelper = new FurnitureHelper(this);

        //dummy data test
//        for(int i =0;i<10;i++){
//            furnitures.add(new Furnitures("kursi"+i,500000*i,50.0+i));
//        }
        FurnitureAdapter adapter = new FurnitureAdapter(this,listFurnitures);
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