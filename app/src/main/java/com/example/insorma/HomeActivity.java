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
import android.view.View;
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
    static TextView nodata2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        userHelper = new UserHelper(this);
        furnitureHelper = new FurnitureHelper(this);

        Users user = getIntent().getParcelableExtra("user");
        nodata2 = findViewById(R.id.noProdData);
        loginUser = findViewById(R.id.tvLoginUser);
        loginUser.setText(user.getUserUName());

        recyclerView = findViewById(R.id.rvFurnitures);

        String url = "https://bit.ly/InSOrmaJSON";

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(url, response -> {
            try {
                JSONArray furnitures = response.getJSONArray("furnitures");

                furnitureHelper.dbClear();

                for (int i = 0; i < furnitures.length(); i++){
                    JSONObject furn = furnitures.getJSONObject(i);

                    String furnName = furn.getString("product_name");
                    Double furnRating = furn.getDouble("rating");
                    Integer furnPrice = furn.getInt("price");
                    String furnImg = furn.getString("image");
                    String furnDesc = furn.getString("description");

                    Furnitures newFurn = new Furnitures(furnImg, furnName, furnRating, furnPrice, furnDesc);
//                    listFurnitures.add(newFurn);
                    furnitureHelper.dbInsert(newFurn);

                    Log.wtf("test a",i+"a");
                }

                FurnitureAdapter adapter = new FurnitureAdapter(this, furnitureHelper.dbRead(), new FurnitureAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Furnitures furnitures, int position) {
                        Intent goDetail = new Intent(HomeActivity.this,DetailActivity.class);
                        goDetail.putExtra("user", user);
                        DetailActivity.getFurniture(furnitures);
                        startActivity(goDetail);

                    }
                });
                recyclerView.setAdapter(adapter);
                LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
                recyclerView.setLayoutManager(manager);

            } catch (JSONException e){
                e.printStackTrace();
            }
        }, error -> {
            Log.wtf("error", error.toString());
        });

        requestQueue.add(request);
    }

    public static void nodata2show(){
        nodata2.setVisibility(View.VISIBLE);
    }
    public static void nodata2hide(){
        nodata2.setVisibility(View.INVISIBLE);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_navigbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
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