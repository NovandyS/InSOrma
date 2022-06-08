package com.example.insorma.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.insorma.databases.Databases;
import com.example.insorma.models.Users;

public class UserHelper {

    private final String TABLE_NAME = "users";
    private Databases dbHelper;
    private SQLiteDatabase db;

    public UserHelper(Context context){
        dbHelper = new Databases(context);
    }

    // insert
    public void dbInsert(Users user){
        db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("useremail", user.getUserEmail());
        contentValues.put("useruname", user.getUserUName());
        contentValues.put("userphone", user.getUserPhone());
        contentValues.put("userpass", user.getUserPass());

        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }

    //read
    public Users dbRead(String email, String password){
        Log.wtf("Check Email", "Success");
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE useremail = ? and userpass = ?",
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
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE useremail = ?",
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
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE useruname = ?",
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
        db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("useruname", user.getUserUName());
        db.update(TABLE_NAME, contentValues,
                "userid = ?", new String[]{user.getUserID() + ""});
        db.close();
    }

    //delete
    public void dbDelete(Users user){
        db = dbHelper.getWritableDatabase();
        db.delete(TABLE_NAME,
                "userid = ?", new String[]{user.getUserID() + ""});
        db.close();
    }

}
