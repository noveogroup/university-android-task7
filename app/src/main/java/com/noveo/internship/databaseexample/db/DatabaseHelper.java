package com.noveo.internship.databaseexample.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "database_task.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(final Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(final SQLiteDatabase database, final ConnectionSource connectionSource) {

    }

    @Override
    public void onUpgrade(final SQLiteDatabase database, final ConnectionSource connectionSource, final int oldVersion, final int newVersion) {

    }
}
