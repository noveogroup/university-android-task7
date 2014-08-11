package com.noveogroup.task7.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class CarsContentProvider extends ContentProvider{

    private CarsOpenHelper openHelper;

    @Override
    public boolean onCreate() {
        openHelper = CarsOpenHelper.getInstance(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        String tableName = CarsContract.getTableName(uri);
        if (tableName == null) {
            return null;
        }

        String limit = uri.getQueryParameter(CarsContract.QUERY_PARAMETER_LIMIT);
        SQLiteDatabase database = openHelper.getReadableDatabase();
        Cursor cursor = database.query(tableName, projection, selection, selectionArgs, null, null,
                                       sortOrder, limit);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        String tableName = CarsContract.getTableName(uri);
        if (tableName == null) {
            return null;
        }

        SQLiteDatabase database = openHelper.getWritableDatabase();
        long id = database.insert(tableName, null, values);
        if (id == -1) {
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(CarsContract.Cars.TABLE_URI, id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        String tableName = CarsContract.getTableName(uri);
        if(tableName == null) {
            return 0;
        }

        SQLiteDatabase database = openHelper.getWritableDatabase();
        int deletedRows = database.delete(tableName, selection, selectionArgs);
        if(deletedRows != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return deletedRows;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        String tableName = CarsContract.getTableName(uri);
        if(tableName == null) {
            return 0;
        }

        SQLiteDatabase database = openHelper.getWritableDatabase();
        int updatedRows = database.update(tableName, values, selection, selectionArgs);
        if (updatedRows != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return updatedRows;
    }
}
