package com.example.insorma.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Databases extends SQLiteOpenHelper {

    private final String CREATE_TABLE_USERS = "CREATE TABLE Users(UserID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "UserEmailAddress TEXT not null," +
            "UserUsername TEXT not null," +
            "UserPhoneNumber TEXT not null," +
            "UserPassword TEXT not null)";

    private final String CREATE_TABLE_PRODUCT = "CREATE TABLE Product(ProductName TEXT PRIMARY KEY," +
            "ProductRating REAL not null," +
            "ProductPrice INTEGER not null," +
            "ProductImage TEXT not null," +
            "ProductDescription TEXT not null)";

    private final String CREATE_TABLE_TRANSACTION = "CREATE TABLE Transactions(TransactionID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "UserID INTEGER not null," +
            "ProductID TEXT not null," +
            "TransactionDate DATE not null," +
            "Quantity INTEGER not null," +
            "FOREIGN KEY (UserID) REFERENCES Users (UserID)," +
            "FOREIGN KEY (ProductID) REFERENCES Product (ProductName))";

    public Databases(@Nullable Context context) {
        super(context, "sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_USERS);
        sqLiteDatabase.execSQL(CREATE_TABLE_PRODUCT);
        sqLiteDatabase.execSQL(CREATE_TABLE_TRANSACTION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Users");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Product");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Transactions");
    }
}
