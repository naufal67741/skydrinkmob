package com.example.skydrinkmob.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static DBHelper mInstance = null;
    public static String SQL_CREATE_DRINK_TABLE =
            "CREATE TABLE IF NOT EXISTS "+MyDB.DATABASE_NAME_DRINK+" (DrinkID INTEGER PRIMARY KEY AUTOINCREMENT, DrinkName TEXT, DrinkDescription TEXT, DrinkPrice INTEGER" +
                    ", DrinkSweetness INTEGER, DrinkSpicy INTEGER, DrinkMalty INTEGER, ClusterID INTEGER)";

    public static String SQL_CREATE_USER_TABLE =
            "CREATE TABLE IF NOT EXISTS "+MyDB.DATABASE_NAME_USER+" (UserID INTEGER PRIMARY KEY AUTOINCREMENT, UserEmail TEXT, UserPassword TEXT, UserGender TEXT," +
                    " UserPhoneNumber TEXT, ClusterID INTEGER)";

    public static String SQL_CREATE_TRANSACTION_TABLE =
            "CREATE TABLE IF NOT EXISTS "+MyDB.DATABASE_NAME_TRANSACTION+" (TransactionID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " TransactionDate TEXT, TotalPrice INTEGER, UserID INTEGER REFERENCES "+MyDB.DATABASE_NAME_USER+")";
    public static String SQL_CREATE_CART_TABLE =
            "CREATE TABLE IF NOT EXISTS "+MyDB.DATABASE_NAME_CART+" (CartID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "  SubTotal INTEGER, UserID INTEGER REFERENCES "+MyDB.DATABASE_NAME_USER+", DrinkID INTEGER REFERENCES "+MyDB.DATABASE_NAME_DRINK+")";
    public static DBHelper getInstance(Context activityContext) {
        // Get the application context from the activityContext to prevent leak
        if (mInstance == null) {
            mInstance = new DBHelper (activityContext.getApplicationContext());
        }
        return mInstance;
    }
    public DBHelper(@Nullable Context context) {
        super(context, MyDB.DATABASE_NAME_DRINK, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_DRINK_TABLE);
        db.execSQL(SQL_CREATE_USER_TABLE);
        db.execSQL(SQL_CREATE_TRANSACTION_TABLE);
        db.execSQL(SQL_CREATE_CART_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+MyDB.DATABASE_NAME_DRINK);
        db.execSQL("DROP TABLE IF EXISTS "+MyDB.DATABASE_NAME_USER);
        db.execSQL("DROP TABLE IF EXISTS "+MyDB.DATABASE_NAME_TRANSACTION);
        db.execSQL("DROP TABLE IF EXISTS "+MyDB.DATABASE_NAME_CART);

        // Create tables again
        onCreate(db);
    }
}
