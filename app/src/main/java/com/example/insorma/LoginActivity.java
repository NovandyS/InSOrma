package com.example.insorma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.insorma.helper.UserHelper;
import com.example.insorma.models.Users;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnLogin, btnRedRegist;
    Integer flag1, flag2;
    UserHelper userHelper;
    String temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPass);

        btnLogin = findViewById(R.id.btnLogin);
        btnRedRegist = findViewById(R.id.redRegister);

        btnLogin.setOnClickListener(v -> {
            String userEmail = etEmail.getText().toString();
            String userPass = etPassword.getText().toString();

            Users user = userHelper.dbRead(userEmail, userPass);

            flag1 = 0;
            flag2 = 0;

            //validate email
            if (userEmail.length() == 0){
                etEmail.setError("This field must be filled!");
            }
            else {
                if (user != null){
                    flag1 = 1;
                    temp = user.getUserPass();
                }
                else if (user == null) {
                    Toast.makeText(this, "Account not Found!", Toast.LENGTH_SHORT).show();
                }
            }

            //validate password
            if (userPass.length() == 0){
                etPassword.setError("This field must be filled!");
            }
            else {
                if (temp.equals(userPass)){
                    flag2 = 1;
                }
                else {
                    etPassword.setError("Password incorrect!");
                }
            }

            //all correct and login
            if (flag1 == 1 && flag2 == 1){
                if (user != null){
                    Intent goHome = new Intent(this, ProfileActivity.class);
                    goHome.putExtra("user", user);
                    startActivity(goHome);
                }
            }
        });

        btnRedRegist.setOnClickListener(v -> {
            Intent redRegist = new Intent(this, RegisterActivity.class);
            startActivity(redRegist);
        });

    }
}