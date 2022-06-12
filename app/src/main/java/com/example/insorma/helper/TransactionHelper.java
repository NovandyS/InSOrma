package com.example.insorma.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.insorma.databases.Databases;
import com.example.insorma.models.Transactions;
import com.example.insorma.models.Users;

import java.util.Vector;

public class TransactionHelper {

    private final String TABLE_NAME = "Transactions";
    private Databases databases;
    private SQLiteDatabase db;

    public TransactionHelper(Context context){
        databases = new Databases(context);
    }

    // insert
    public void dbInsert(Transactions transcations){
        db = databases.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("UserID", transcations.getUserID());
        cv.put("ProductID", transcations.getProdID());
        cv.put("TransactionDate", transcations.getTransDate());
        cv.put("Quantity", transcations.getTransQuant());

        db.insert(TABLE_NAME, null, cv);
        db.close();
    }

    // read
    public Vector<Transactions> dbRead(Users user){
        Vector<Transactions> transHistory = new Vector<>();
        db = databases.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Transactions WHERE UserID = ?", new String[]{user.getUserID().toString()});
        cursor.moveToFirst();

        Transactions temp;
        String tempProdID, tempTransDate;
        Integer tempTransID, tempUserID, tempQuantity;

        if (cursor != null && cursor.getCount() > 0){
            do {
                tempProdID = cursor.getString(cursor.getColumnIndexOrThrow("ProductID"));
                tempTransDate = cursor.getString(cursor.getColumnIndexOrThrow("TransactionDate"));
                tempUserID = cursor.getInt(cursor.getColumnIndexOrThrow("UserID"));
                tempTransID = cursor.getInt(cursor.getColumnIndexOrThrow("TransactionID"));
                tempQuantity = cursor.getInt(cursor.getColumnIndexOrThrow("Quantity"));

                temp = new Transactions(tempTransID, tempUserID, tempProdID, tempTransDate, tempQuantity);
                transHistory.add(temp);

                cursor.moveToNext();

            } while (!cursor.isAfterLast());
            cursor.close();
        }
        db.close();
        return transHistory;
    }



}
