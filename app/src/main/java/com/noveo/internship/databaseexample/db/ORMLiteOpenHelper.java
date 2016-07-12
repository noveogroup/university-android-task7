package com.noveo.internship.databaseexample.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.noveo.internship.databaseexample.model.Toy;

import java.sql.SQLException;

public class ORMLiteOpenHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = ExampleOpenHelper.DATABASE_NAME;
    private static final int DATABASE_VERSION = ExampleOpenHelper.DATABASE_VERSION;

    private RuntimeExceptionDao<Toy, Integer> toyDao;

    public ORMLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Toy.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Toy.class, false);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public RuntimeExceptionDao<Toy, Integer> getRuntimeExceptionDao() {
        if (toyDao == null) {
            toyDao = getRuntimeExceptionDao(Toy.class);
        }
        return toyDao;
    }
}