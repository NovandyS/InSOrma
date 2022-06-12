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
import android.view.View;
import android.widget.TextView;

import com.example.insorma.helper.FurnitureHelper;
import com.example.insorma.helper.TransactionHelper;
import com.example.insorma.helper.UserHelper;
import com.example.insorma.models.Furnitures;
import com.example.insorma.models.Transactions;
import com.example.insorma.models.Users;
import com.example.insorma.recyclerview.HistoryAdapter;

import java.util.Vector;

public class HistoryActivity extends AppCompatActivity {

    static TextView nodata;
    RecyclerView recyclerView;
    TransactionHelper transactionHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerView = findViewById(R.id.rvTransHist);
        nodata = findViewById(R.id.noData);

        transactionHelper = new TransactionHelper(this);

        Users user = getIntent().getParcelableExtra("user");

        Vector<Transactions> transactions = transactionHelper.dbRead(user);
        if (transactions != null){
          //  nodata.setVisibility(View.INVISIBLE);
            HistoryAdapter adapter = new HistoryAdapter(this, transactions);
            recyclerView.setAdapter(adapter);
            LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
            recyclerView.setLayoutManager(manager);
        }



    }

    public static void nodatashow(){
        nodata.setVisibility(View.VISIBLE);
    }
    public static void nodatahide(){
        nodata.setVisibility(View.INVISIBLE);
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
                startActivity(goHome);
                break;
            case R.id.history:
                Intent goHistory = new Intent(this, HistoryActivity.class);
                goHistory.putExtra("user", user);
                this.finish();
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