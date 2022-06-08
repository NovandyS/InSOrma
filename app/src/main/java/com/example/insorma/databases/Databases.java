package com.example.insorma.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Databases extends SQLiteOpenHelper {

    private final String CREATE_TABLE_USERS = "CREATE TABLE users(userid INTEGER PRIMARY KEY AUTOINCREMENT," +
            "useremail TEXT not null," +
            "useruname TEXT not null," +
            "userphone TEXT not null," +
            "userpass TEXT not null)";

    private final String CREATE_TABLE_FURNITURES = "CREATE TABLE furnitures(furnid INTEGER PRIMARY KEY AUTOINCREMENT," +
            "furnimage INTEGER not null," +
            "furnname TEXT not null," +
            "furnrating REAL not null," +
            "furnprice INTEGER not null," +
            "furndesc TEXT not null)";

    private final String CREATE_TABLE_TRANSACTIONS = "CREATE TABLE transactions(transid INTEGER PRIMARY KEY AUTOINCREMENT," +
            "userid INTEGER not null," +
            "prodid INTEGER not null," +
            "transdate TEXT not null," +
            "transquant INTEGER not null," +
            "transtotal INTEGER not null," +
            "FOREIGN KEY (userid) REFERENCES users (userid)," +
            "FOREIGN KEY (prodid) REFERENCES furnitures (furnid))";

    public Databases(@Nullable Context context) {
        super(context, "sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_USERS);
        sqLiteDatabase.execSQL(CREATE_TABLE_FURNITURES);
        sqLiteDatabase.execSQL(CREATE_TABLE_TRANSACTIONS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS users");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS furnitures");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS transactions");
    }
}
