package com.noveogroup.task7.app2;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.noveogroup.task7.app2.db.ContentDescriptor;

public class ShowListFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor>{
	private CursorAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		adapter = new SimpleCursorAdapter(getActivity(), R.layout.list_item_layout, null,
				new String[]{ContentDescriptor.Toys.Cols.TITLE, ContentDescriptor.Toys.Cols.COST},
				new int[]{R.id.title, R.id.cost}, 0);
		setListAdapter(adapter);
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		return new CursorLoader(getActivity(), ContentDescriptor.Toys.TABLE_URI, null, null, null, null);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		if (adapter != null && data != null) {
			adapter.changeCursor(data);
		}
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		if (adapter != null) {
			adapter.changeCursor(null);
		}
	}
}
