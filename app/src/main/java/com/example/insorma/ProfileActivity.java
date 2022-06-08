package com.example.insorma;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

        Users user = getIntent().getParcelableExtra("user");

        editUName = findViewById(R.id.thisUName);
        saveUName = findViewById(R.id.thisEditUName);
        saveUName.setVisibility(View.INVISIBLE);

        tvID = findViewById(R.id.uID);
        tvID.setText(user.getUserID());
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
            AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
            builder.setTitle("Delete Account");
            builder.setMessage("Are you sure to delete your account?");
            builder.setCancelable(false);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    userHelper.dbDelete(user);
                    Intent delete = new Intent(ProfileActivity.this, LoginActivity.class);
                    startActivity(delete);
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });


        });

    }
}