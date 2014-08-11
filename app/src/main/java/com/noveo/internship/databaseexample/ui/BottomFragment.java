package com.noveo.internship.databaseexample.ui;

import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;

import com.noveo.internship.databaseexample.R;
import com.noveo.internship.databaseexample.db.ContentDescriptor;

public class BottomFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    SimpleCursorAdapter adapter;
    private LoaderManager loaderManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String[] columns = new String[]{ContentDescriptor.Toys.Cols.TITLE, ContentDescriptor.Toys.Cols.COST};
        int[] toFields = new int[]{R.id.title, R.id.cost};
        adapter = new SimpleCursorAdapter(inflater.getContext(), R.layout.list_item_layout, null, columns, toFields, 0);
        setListAdapter(adapter);

        loaderManager = getLoaderManager();
        loaderManager.initLoader(0, null, this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(), ContentDescriptor.Toys.TABLE_URI, null, null, null, null);
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
}
