package com.noveogroup.task7.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;

public class CarsOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "com.noveogroup.task7.CARS_DATABASE";
    public static final int DATABASE_VERSION = 1;
    private static CarsOpenHelper instance;
    private static HashMap<String, Integer> exampleItems;


    private CarsOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public synchronized static CarsOpenHelper getInstance(Context context) {
        if (instance == null) {
            instance = new CarsOpenHelper(context);
            exampleItems = CarsContentMaker.getExampleItems(context);
        }
        return instance;
    }
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL("CREATE TABLE " + CarsContract.Cars.TABLE_NAME + " ( "
                + CarsContract.Cars.Columns.ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + CarsContract.Cars.Columns.MODEL+ " TEXT NOT NULL,"
                + CarsContract.Cars.Columns.PRICE + " INTEGER NOT NULL" + " )");

        for(String model : exampleItems.keySet()) {
            ContentValues values = new ContentValues();
            values.put(CarsContract.Cars.Columns.MODEL, model);
            values.put(CarsContract.Cars.Columns.PRICE, exampleItems.get(model));
            database.insert(CarsContract.Cars.TABLE_NAME, null, values);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            database.execSQL("DROP TABLE IF EXISTS " + CarsContract.Cars.TABLE_NAME);
            onCreate(database);
        }
    }
}
