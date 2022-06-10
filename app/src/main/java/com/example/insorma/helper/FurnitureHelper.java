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

    private final String TABLE_NAME = "furnitures";
    private Databases dbHelper;
    private SQLiteDatabase db;

    public FurnitureHelper(Context context){
        dbHelper = new Databases(context);
    }

    // insert
    public void dbInsert(Furnitures furnitures){
        db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("furnimage", furnitures.getFurnitureImage());
        cv.put("furnname", furnitures.getFurnitureName());
        cv.put("furnrating", furnitures.getFurnitureRating());
        cv.put("furnprice", furnitures.getFurniturePrice());
        cv.put("furndesc", furnitures.getFurnitureDesc());

        db.insert(TABLE_NAME, null, cv);
        db.close();
    }

    // read
    public Vector<Furnitures> dbRead(){
        Vector<Furnitures> listFurnitures = new Vector<>();
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM furnitures", null);
        cursor.moveToFirst();

        Furnitures temp;
        String tempImg, tempName, tempDesc;
        Double tempRating;
        Integer tempPrice;

        if (cursor != null && cursor.getCount() > 0){
            do {
                tempImg = cursor.getString(cursor.getColumnIndexOrThrow("furnimage"));
                tempName = cursor.getString(cursor.getColumnIndexOrThrow("furnname"));
                tempRating = cursor.getDouble(cursor.getColumnIndexOrThrow("furnrating"));
                tempPrice = cursor.getInt(cursor.getColumnIndexOrThrow("furnprice"));
                tempDesc = cursor.getString(cursor.getColumnIndexOrThrow("furndesc"));

                temp = new Furnitures(tempImg, tempName, tempRating, tempPrice, tempDesc);
                listFurnitures.add(temp);

                cursor.moveToNext();

            } while (!cursor.isAfterLast());
            cursor.close();
        }
        db.close();
        return listFurnitures;
    }

    public void dbClear(){

    }
}
