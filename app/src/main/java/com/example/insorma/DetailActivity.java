package com.example.insorma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.insorma.helper.TransactionHelper;
import com.example.insorma.models.Furnitures;
import com.example.insorma.models.Transactions;
import com.example.insorma.models.Users;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

public class DetailActivity extends AppCompatActivity {

    TextView tvFurnitureName, tvFurniturePrice, tvFurnitureRating, tvFurnitureDesc;
    ImageView ivFurniture;
    EditText edtQuantity;
    Button btnBuy;

    Calendar calendar; SimpleDateFormat dateFormat; String date;

    TransactionHelper transactionHelper;

    static Furnitures furniture;
    public static void getFurniture(Furnitures furnitures) {
        furniture = furnitures;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvFurnitureName = findViewById(R.id.furnitureName);
        tvFurniturePrice = findViewById(R.id.furniturePrice);
        tvFurnitureRating = findViewById(R.id.furnitureRating);
        tvFurnitureDesc = findViewById(R.id.furnitureDesc);
        ivFurniture = findViewById(R.id.ivDetail);
        edtQuantity = findViewById(R.id.edtQuantity);
        btnBuy = findViewById(R.id.btnBuy);

        tvFurnitureName.setText(furniture.getFurnitureName());
        tvFurniturePrice.setText("$ " + furniture.getFurniturePrice().toString());
        tvFurnitureRating.setText(furniture.getFurnitureRating().toString());
        tvFurnitureDesc.setText(furniture.getFurnitureDesc());
        Glide.with(this)
                .load(furniture.getFurnitureImage())
                .into(ivFurniture);

        transactionHelper = new TransactionHelper(this);
        Users user = getIntent().getParcelableExtra("user");

        int permissionSMS = ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        int permissionPhoneNum = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        int permissionReadSMS = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS);
        if (permissionSMS == PackageManager.PERMISSION_DENIED &&
                permissionPhoneNum == PackageManager.PERMISSION_DENIED &&
                permissionReadSMS == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 1);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, 2);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 3);
        }

        btnBuy.setOnClickListener(v -> {
            Integer quantity = Integer.parseInt(edtQuantity.getText().toString());
            if (quantity <= 0){
                edtQuantity.setError("Quantity must be more than zero!");
            }
            else {
                calendar = calendar.getInstance();
                dateFormat = new SimpleDateFormat("d MMM yyyy");
                date = dateFormat.format(calendar.getTime());

                Transactions transactions = new Transactions(user.getUserID(), furniture.getFurnitureName(), date, quantity);
                transactionHelper.dbInsert(transactions);

                String message = "You have bought " + quantity + " pc(s) of " + furniture.getFurnitureName() + " at " + date;

                //Send to Emulator Phone Number
//                String phone;
//                TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
//                phone = telephonyManager.getLine1Number();
//
//                SmsManager manager = SmsManager.getDefault();
//                manager.sendTextMessage(phone, null, message, null, null);

                //Send to User Phone Number
                String phone;
                phone = user.getUserPhone();

                SmsManager manager = SmsManager.getDefault();
                manager.sendTextMessage(phone, null, message, null, null);

                Vector<Transactions> trans = transactionHelper.dbRead(user);
                for (int i = 0; i < trans.size(); i++){
                    Log.wtf("TestBuy", trans.get(i).getProdID() + " " + trans.get(i).getTransQuant());
                }

                Toast.makeText(this, "Purchasement Success", Toast.LENGTH_SHORT).show();
                this.finish();
            }
        });

    }
}