package com.example.insorma.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.insorma.databases.Databases;
import com.example.insorma.models.Furnitures;

import java.util.Vector;

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
        cv.put("furnimage", furnitures.getFurnitureName());
        cv.put("furnname", furnitures.getFurnitureName());
        cv.put("furnrating", furnitures.getFurnitureRating());
        cv.put("furnprice", furnitures.getFurniturePrice());
        cv.put("furndesc", furnitures.getFurnitureDesc());

        db.insert(TABLE_NAME, null, cv);
        db.close();
    }

    // read
    public Vector<Furnitures> dbRead(){
        Vector<Furnitures> listFurnitures = null;
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM furnitures", null);
        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            listFurnitures = new Vector<>();
            listFurnitures.add(new Furnitures(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getDouble(2),
                    cursor.getInt(3),
                    cursor.getString(4)
            ));
            cursor.close();
        }
        db.close();
        return listFurnitures;
    }
}
