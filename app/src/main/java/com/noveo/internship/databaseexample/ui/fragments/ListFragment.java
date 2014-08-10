package com.noveo.internship.databaseexample.ui.fragments;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.noveo.internship.databaseexample.R;
import com.noveo.internship.databaseexample.db.ContentDescriptor;

public class ListFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private LoaderManager loaderManager;
    private SimpleCursorAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        loaderManager = getLoaderManager();
        ListView listView = (ListView)view.findViewById(R.id.list);

        String[] columns = new String[]{ContentDescriptor.Toys.Cols.TITLE, ContentDescriptor.Toys.Cols.COST};
        int[] toFields = new int[]{R.id.title, R.id.cost};

        adapter = new SimpleCursorAdapter(getActivity(), R.layout.list_item_layout, null, columns, toFields, 0);
        listView.setAdapter(adapter);

        loaderManager.initLoader(0, null, this);
        return view;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(getActivity(), ContentDescriptor.Toys.TABLE_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        if (adapter != null && cursor != null) {
            adapter.changeCursor(cursor);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        if (adapter != null) {
            adapter.changeCursor(null);
        }
    }

    private void showData() {
        loaderManager.restartLoader(0, null, this);
    }
}
