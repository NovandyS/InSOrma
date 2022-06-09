package com.example.insorma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.insorma.helper.UserHelper;
import com.example.insorma.models.Users;

public class ProfileActivity extends AppCompatActivity {

    EditText etEditUName;
    TextView tvID, tvEmail, tvPhoneNumber, tvUserName;
    Button btnEditUserName, btnSaveUserName, btnLogOut, btnDelAcc;
    View editUName, saveUName;
    UserHelper userHelper;

    Integer flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        userHelper = new UserHelper(this);

        Users user = getIntent().getParcelableExtra("user");

        editUName = findViewById(R.id.thisUName);
        saveUName = findViewById(R.id.thisEditUName);
        saveUName.setVisibility(View.INVISIBLE);

        tvID = findViewById(R.id.uID);
        tvID.setText(user.getUserID().toString());
        tvEmail = findViewById(R.id.uEmail);
        tvEmail.setText(user.getUserEmail());
        tvUserName = findViewById(R.id.uUsername);
        tvUserName.setText(user.getUserUName());
        tvPhoneNumber = findViewById(R.id.uPhone);
        tvPhoneNumber.setText(user.getUserPhone());

        etEditUName = findViewById(R.id.newUsername);

        btnEditUserName = findViewById(R.id.btnEditUName);
        btnSaveUserName = findViewById(R.id.btnSaveUname);
        btnLogOut = findViewById(R.id.btnLogout);
        btnDelAcc = findViewById(R.id.btnDelAcc);

        btnEditUserName.setOnClickListener(v -> {
            if (editUName.getVisibility() == View.VISIBLE){
                editUName.setVisibility(View.INVISIBLE);
                saveUName.setVisibility(View.VISIBLE);
            }
        });

        btnSaveUserName.setOnClickListener(v -> {
            String newUName = etEditUName.getText().toString();
            flag = 1;
            if (newUName.length() != 0){
                Users temp = userHelper.dbReadUName(newUName);
                if (temp != null){
                    etEditUName.setError("This username is already taken!");
                    flag = 0;
                }
                if (flag == 1){
                    user.setUserUName(newUName);
                    userHelper.dbUpdate(user);
                    tvUserName.setText(newUName);
                }
            }
            Log.v("Username", user.getUserUName());
            if (flag == 1){
                editUName.setVisibility(View.VISIBLE);
                saveUName.setVisibility(View.INVISIBLE);
            }
        });

        btnLogOut.setOnClickListener(v -> {
            Intent goLogin = new Intent(this, LoginActivity.class);
            startActivity(goLogin);
            this.finish();
        });

        btnDelAcc.setOnClickListener(v -> {
            AlertDialog builder = new AlertDialog.Builder(ProfileActivity.this)
                    .setTitle("Delete Account")
                    .setMessage("Are you sure to delete your account?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            userHelper.dbDelete(user);
                            Intent delete = new Intent(ProfileActivity.this, LoginActivity.class);
                            startActivity(delete);
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    })
                    .show();
        });
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
                this.finish();
                startActivity(goProfile);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}