package com.noveogroup.internship.task7;

import android.annotation.TargetApi;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.noveogroup.internship.task7.provider.ContentContract;

public class ViewFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private SimpleCursorAdapter mAdapter;

    @SuppressWarnings("deprecation")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] columns = new String[]{
                ContentContract.Toys.Cols.TITLE, ContentContract.Toys.Cols.COST };
        int[] toFields = new int[]{ android.R.id.text1, android.R.id.text2 };
        mAdapter = new SimpleCursorAdapter(
                getActivity(), android.R.layout.simple_list_item_2, null, columns, toFields);

        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ListView list = new ListView(getActivity());
        list.setAdapter(mAdapter);
        return list;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mAdapter = null;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(), ContentContract.Toys.TABLE_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (mAdapter != null && data != null) {
            mAdapter.changeCursor(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        if (mAdapter != null) {
            mAdapter.changeCursor(null);
        }
    }
}
