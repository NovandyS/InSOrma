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

public class RegisterActivity extends AppCompatActivity {

    Button btnRegist, btnRedLogin;
    EditText etEmail, etUsername, etPhoneNum, etPassword;
    Integer flag1, flag2, flag3, flag4;
    UserHelper userHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail = findViewById(R.id.inpEmail);
        etUsername = findViewById(R.id.inpUserName);
        etPhoneNum = findViewById(R.id.inpPhoneNumb);
        etPassword = findViewById(R.id.inpPass);

        btnRegist = findViewById(R.id.btnRegist);
        btnRedLogin = findViewById(R.id.redLogin);

        userHelper = new UserHelper(this);

        btnRegist.setOnClickListener(v -> {
            String emailTxt = etEmail.getText().toString();
            String usernameTxt = etUsername.getText().toString();
            String phonenumTxt = etPhoneNum.getText().toString();
            String passwordTxt = etPassword.getText().toString();

            //validate email
            if (emailTxt.length() == 0){
                etEmail.setError("This field must be filled!");
                flag1 = 0;
            }
            else {
                if (!emailTxt.endsWith(".com")){
                    etEmail.setError("Email must be end with '.com'");
                    flag1 = 0;
                }
                else {
                    flag1 = 1;
                    Log.wtf("UserEmailCheck", emailTxt);
                    Users user = userHelper.dbReadEmail(emailTxt);
                    if (user != null){
                        flag1 = 0;
                        etEmail.setError("Email already taken!");
                    }
                }
            }

            //validate username
            if (usernameTxt.length() == 0){
                etUsername.setError("This field must be filled!");
                flag2 = 0;
            }
            else if (usernameTxt.length() < 3 || usernameTxt.length() > 20){
                etUsername.setError("Username should be between 3 and 20 characters");
                flag2 = 0;
            }
            else {
                flag2 = 1;
                Users user = userHelper.dbReadUName(usernameTxt);
                if (user != null){
                    flag2 = 0;
                    etUsername.setError("Username already taken!");
                }

            }

            //validate phone number
            if (phonenumTxt.length() == 0){
                etPhoneNum.setError("This field must be filled!");
                flag3 = 0;
            }
            else {
                flag3 = 1;
            }

            //validate password
            if (passwordTxt.length() == 0){
                etPassword.setError("This field must be filled!");
                flag4 = 0;
            }
            else {
                if (passwordTxt.matches(".*[A-Za-z].*") && passwordTxt.matches(".*[0-9].*") && passwordTxt.matches("[A-Za-z0-9]*")){
                    flag4 = 1;
                }
                else {
                    etPassword.setError("Password should be an alphanumeric.");
                    flag4 = 0;
                }
            }

            //all success and registered
            if (flag1 == 1 && flag2 == 1 && flag3 == 1 && flag4 == 1){
                Users user = new Users(emailTxt, usernameTxt, passwordTxt, phonenumTxt);
                userHelper.dbInsert(user);

                Log.v("success register", user.getUserEmail());

                Toast.makeText(this, "Register Success!", Toast.LENGTH_SHORT).show();
                this.finish();
            }

        });

        btnRedLogin.setOnClickListener(v -> {
            this.finish();
        });

    }
}