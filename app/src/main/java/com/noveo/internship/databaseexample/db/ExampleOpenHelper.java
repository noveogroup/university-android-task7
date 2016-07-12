package com.noveo.internship.databaseexample.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ExampleOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "internship.db";
    public static final int DATABASE_VERSION = 1;
    private static ExampleOpenHelper instance = null;

    private ExampleOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public synchronized static ExampleOpenHelper getInstance(Context context) {
        if (instance == null) {
            instance = new ExampleOpenHelper(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + ContentDescriptor.Toys.TABLE_NAME + " ( " +
                ContentDescriptor.Toys.Cols.ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                ContentDescriptor.Toys.Cols.TITLE + " TEXT NOT NULL," +
                ContentDescriptor.Toys.Cols.COST + " INTEGER" +
                " )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ContentDescriptor.Toys.TABLE_NAME);
            onCreate(sqLiteDatabase);
        }
    }

}
