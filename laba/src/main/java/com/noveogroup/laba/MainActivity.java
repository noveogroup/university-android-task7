package com.noveogroup.laba;

import android.app.Activity;
import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {
    private ListFragment mListFragment;
    private DB mDB;
    private SimpleCursorAdapter adapter;
    private LoaderManager loaderManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListFragment = (ListFragment) getFragmentManager().findFragmentById(R.id.list_fragment);
        loaderManager = getLoaderManager();

        mDB = DB.getInstance(this);
        mDB.open();

        String[] columns = new String[]{DB.COLUMN_TITLE, DB.COLUMN_TXT};
        int[] toFields = new int[]{R.id.title, R.id.text};

        adapter = new SimpleCursorAdapter(this, R.layout.list_item_layout, null, columns, toFields, 0);
        mListFragment.setListAdapter(adapter);

        loaderManager.initLoader(0, null, this);
    }

    public void addToDB(String title, String text) {
        mDB.addRec(text, title);
        loaderManager.getLoader(0).forceLoad();
    }

    public void deleteLast() {
        Cursor cursor = adapter.getCursor();
        if(!cursor.isBeforeFirst()) {
            cursor.moveToLast();
            int lastID = cursor.getInt(cursor.getColumnIndex(DB.COLUMN_ID));
            mDB.delRec(lastID);
            loaderManager.getLoader(0).forceLoad();
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mDB.open();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDB.close();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new DBCursorLoader(this, mDB);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.changeCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    public static class DBCursorLoader extends CursorLoader {
        private DB db;

        public DBCursorLoader(Context context, DB db) {
            super(context);
            this.db = db;
        }

        @Override
        public Cursor loadInBackground() {
            return db.getAllData();
        }
    }
}
