package com.example.insorma.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.insorma.databases.Databases;
import com.example.insorma.models.Users;

public class UserHelper {

    private final String TABLE_NAME = "Users";
    private Databases databases;
    private SQLiteDatabase db;

    public UserHelper(Context context){
        databases = new Databases(context);
    }

    // insert
    public void dbInsert(Users user){
        db = databases.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("UserEmailAddress", user.getUserEmail());
        contentValues.put("UserUsername", user.getUserUName());
        contentValues.put("UserPhoneNumber", user.getUserPhone());
        contentValues.put("UserPassword", user.getUserPass());

        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }

    //read
    public Users dbRead(String email, String password){
        db = databases.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Users WHERE UserEmailAddress = ? and UserPassword = ?",
                                    new String[]{email, password});
        Users user = null;
        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            user = new Users();
            user.setUserID(cursor.getInt(0));
            user.setUserEmail(cursor.getString(1));
            user.setUserUName(cursor.getString(2));
            user.setUserPhone(cursor.getString(3));
            user.setUserPass(cursor.getString(4));
            cursor.close();
        }
        db.close();
        return user;
    }

    public Users dbReadEmail(String email){
        db = databases.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Users WHERE UserEmailAddress = ?",
                new String[]{email});
        Users user = null;
        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            user = new Users();
            user.setUserID(cursor.getInt(0));
            user.setUserEmail(cursor.getString(1));
            user.setUserUName(cursor.getString(2));
            user.setUserPhone(cursor.getString(3));
            user.setUserPass(cursor.getString(4));
            cursor.close();
        }
        db.close();
        return user;
    }

    public Users dbReadUName(String username){
        db = databases.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Users WHERE UserUsername = ?",
                new String[]{username});
        Users user = null;
        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            user = new Users();
            user.setUserID(cursor.getInt(0));
            user.setUserEmail(cursor.getString(1));
            user.setUserUName(cursor.getString(2));
            user.setUserPhone(cursor.getString(3));
            user.setUserPass(cursor.getString(4));
            cursor.close();
        }
        db.close();
        return user;
    }

    // update
    public void dbUpdate(Users user){
        db = databases.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("UserUsername", user.getUserUName());
        db.update(TABLE_NAME, contentValues,
                "UserID = ?", new String[]{user.getUserID() + ""});
        db.close();
    }

    //delete
    public void dbDelete(Users user){
        db = databases.getWritableDatabase();
        db.delete(TABLE_NAME,
                "UserID = ?", new String[]{user.getUserID() + ""});
        db.close();
    }

}
