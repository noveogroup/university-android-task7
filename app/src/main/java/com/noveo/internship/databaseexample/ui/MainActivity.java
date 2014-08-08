package com.noveo.internship.databaseexample.ui;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.AsyncQueryHandler;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;

import com.noveo.internship.databaseexample.R;
import com.noveo.internship.databaseexample.db.ContentDescriptor;
import com.noveo.internship.databaseexample.db.OpenHelper;

public class MainActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {

    private EditText titleEditText;
    private EditText costEditText;
    private LoaderManager loaderManager;
    private SimpleCursorAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.list);
        titleEditText = (EditText) findViewById(R.id.titleEdit);
        costEditText = (EditText) findViewById(R.id.costEdit);
        loaderManager = getLoaderManager();

        View insert = findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });

        String[] columns = new String[]{ContentDescriptor.Toys.Cols.TITLE, ContentDescriptor.Toys.Cols.COST};
        int[] toFields = new int[]{R.id.title, R.id.cost};

        adapter = new SimpleCursorAdapter(this, R.layout.list_item_layout, null, columns, toFields, 0);
        listView.setAdapter(adapter);

        loaderManager.initLoader(0, null, this);
    }

    private void insertData() {
        if (!TextUtils.isEmpty(titleEditText.getText()) && !TextUtils.isEmpty(costEditText.getText())) {
            ContentValues values = new ContentValues();
            values.put(ContentDescriptor.Toys.Cols.TITLE, String.valueOf(titleEditText.getText()));
            values.put(ContentDescriptor.Toys.Cols.COST, Integer.valueOf(String.valueOf(costEditText.getText())));
            new AsyncQueryHandler(getContentResolver()) {}.startInsert(1, null, ContentDescriptor.Toys.TABLE_URI, values);
        } else {
            Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(MainActivity.this, ContentDescriptor.Toys.TABLE_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (adapter != null && cursor != null) {
            adapter.changeCursor(cursor);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        if (adapter != null) {
            adapter.changeCursor(null);
        }
    }

    private void showData() {
        loaderManager.restartLoader(0, null, this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OpenHelper.getInstance(this).close();
    }
}
