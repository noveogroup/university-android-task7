package com.noveogroup.internship.task7.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "internship.db";
    private static final int DATABASE_VERSION = 1;
    private static OpenHelper instance = null;

    private OpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized OpenHelper getInstance(Context context) {
        if (instance == null) {
            instance = new OpenHelper(context);
        }
        return instance;
    }

    public static synchronized void releaseInstance() {
        if (instance != null) {
            instance.close();
            instance = null;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + ContentContract.Toys.TABLE_NAME + " ( " +
                ContentContract.Toys.Cols._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                ContentContract.Toys.Cols.TITLE + " TEXT NOT NULL," +
                ContentContract.Toys.Cols.COST + " INTEGER" +
                " )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ContentContract.Toys.TABLE_NAME);
            onCreate(sqLiteDatabase);
        }
    }

}
