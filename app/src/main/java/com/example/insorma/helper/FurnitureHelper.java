package com.example.insorma.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.insorma.databases.Databases;
import com.example.insorma.models.Furnitures;

import java.util.Vector;
import com.example.insorma.models.Furnitures;
import com.example.insorma.models.Users;

public class FurnitureHelper {

    private final String TABLE_NAME = "Product";
    private Databases databases;
    private SQLiteDatabase db;

    public FurnitureHelper(Context context){
        databases = new Databases(context);
    }

    // insert
    public void dbInsert(Furnitures furnitures){
        db = databases.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("ProductImage", furnitures.getFurnitureImage());
        cv.put("ProductName", furnitures.getFurnitureName());
        cv.put("ProductRating", furnitures.getFurnitureRating());
        cv.put("ProductPrice", furnitures.getFurniturePrice());
        cv.put("ProductDescription", furnitures.getFurnitureDesc());

        db.insert(TABLE_NAME, null, cv);
        db.close();
    }

    // read
    public Vector<Furnitures> dbRead(){
        Vector<Furnitures> listFurnitures = new Vector<>();
        db = databases.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Product", null);
        cursor.moveToFirst();

        Furnitures temp;
        String tempImg, tempName, tempDesc;
        Double tempRating;
        Integer tempPrice;

        if (cursor != null && cursor.getCount() > 0){
            do {
                tempImg = cursor.getString(cursor.getColumnIndexOrThrow("ProductImage"));
                tempName = cursor.getString(cursor.getColumnIndexOrThrow("ProductName"));
                tempRating = cursor.getDouble(cursor.getColumnIndexOrThrow("ProductRating"));
                tempPrice = cursor.getInt(cursor.getColumnIndexOrThrow("ProductPrice"));
                tempDesc = cursor.getString(cursor.getColumnIndexOrThrow("ProductDescription"));

                temp = new Furnitures(tempImg, tempName, tempRating, tempPrice, tempDesc);
                listFurnitures.add(temp);

                cursor.moveToNext();

            } while (!cursor.isAfterLast());
            cursor.close();
        }
        db.close();
        return listFurnitures;
    }

    public Furnitures dbReadFurn(String prodID){
        db = databases.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Product WHERE ProductName = ?",
                new String[]{prodID});
        Furnitures furnitures = null;
        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            furnitures = new Furnitures();
            furnitures.setFurnitureName(cursor.getString(0));
            furnitures.setFurnitureRating(cursor.getDouble(1));
            furnitures.setFurniturePrice(cursor.getInt(2));
            furnitures.setFurnitureImage(cursor.getString(3));
            furnitures.setFurnitureDesc(cursor.getString(4));
            cursor.close();
        }
        db.close();
        return furnitures;
    }

    public void dbClear(){
        db = databases.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }
}
