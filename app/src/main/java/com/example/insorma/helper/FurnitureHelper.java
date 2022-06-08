package com.example.insorma.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.insorma.databases.Databases;
import com.example.insorma.models.Users;

public class FurnitureHelper {

    private final String TABLE_NAME = "users";
    private Databases dbHelper;
    private SQLiteDatabase db;

    public FurnitureHelper(Context context){
        dbHelper = new Databases(context);
    }

    // insert
//    public void dbInsert(Users user){
//        db = dbHelper.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put("useremail", user.getUserEmail());
//        cv.put("useruname", user.getUserUName());
//        cv.put("userphone", user.getUserPhone());
//        cv.put("userpass", user.getUserPass());
//
//        db.insert(TABLE_NAME, null, cv);
//        db.close();
//    }
}
