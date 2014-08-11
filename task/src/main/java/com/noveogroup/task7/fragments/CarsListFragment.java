package com.noveogroup.task7.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;

import com.noveogroup.task7.R;
import com.noveogroup.task7.database.CarsContract;

public class CarsListFragment extends ListFragment
                              implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int CURSOR_LOADER_ID = 0;

    private SimpleCursorAdapter cursorAdapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        cursorAdapter = new SimpleCursorAdapter(getActivity(), R.layout.list_item, null,
                new String[] {CarsContract.Cars.Columns.MODEL, CarsContract.Cars.Columns.PRICE},
                new int[] {R.id.model_text_view, R.id.price_text_view}, 0);
        setListAdapter(cursorAdapter);
        getLoaderManager().initLoader(CURSOR_LOADER_ID, null, this);
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(), CarsContract.Cars.TABLE_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader loader, Cursor cursor) {
        cursorAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader loader) {
        cursorAdapter.swapCursor(null);
    }
}
