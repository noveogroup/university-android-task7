package com.noveogroup.laba;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB {
    private static final String DB_NAME = "database";
    private static final int DB_VERSION = 1;
    private static final String DB_TABLE = "info";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_TXT = "text";

    private static final String DB_CREATE =
            "create table " + DB_TABLE + "(" +
                    COLUMN_ID + " integer primary key autoincrement, " +
                    COLUMN_TITLE + " text, " +
                    COLUMN_TXT + " text" +
                    ");";

    private OpenHelper mOpenHelper;
    private SQLiteDatabase mDB;
    private final Context mContext;

    private static DB instance;

    public static synchronized DB getInstance(Context ctx) {
        if(instance == null) {
            instance = new DB(ctx);
        }
        return instance;
    }

    public DB(Context context) {
        mContext = context;
    }

    public void open() {
        mOpenHelper = OpenHelper.getInstance(mContext);
        mDB = mOpenHelper.getWritableDatabase();
    }

    public void close() {
        if(mOpenHelper != null) {
            mOpenHelper.close();
        }
    }

    public Cursor getAllData() {
        return mDB.query(DB_TABLE, null, null, null, null, null, null);
    }

    public void addRec(String text, String title) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TXT, text);
        cv.put(COLUMN_TITLE, title);
        mDB.insert(DB_TABLE, null, cv);
    }

    public void delRec(long id) {
        mDB.delete(DB_TABLE, COLUMN_ID + " = " + id, null);
    }

    public static class OpenHelper extends SQLiteOpenHelper {
        private static OpenHelper instance;

        public static synchronized OpenHelper getInstance(Context context) {
            if(instance == null) {
                instance = new OpenHelper(context);
            }
            return instance;
        }

        public OpenHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(DB_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
            if (oldVersion < newVersion) {
                sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
                onCreate(sqLiteDatabase);
            }
        }
    }
}
