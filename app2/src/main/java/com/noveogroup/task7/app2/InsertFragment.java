package com.noveogroup.task7.app2;

import android.content.AsyncQueryHandler;
import android.content.ContentValues;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.noveogroup.task7.app2.R;
import com.noveogroup.task7.app2.db.ContentDescriptor;


public class InsertFragment extends Fragment {

	EditText titleEditText;
	EditText costEditText;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.input_fragment, container, false);
		view.findViewById(R.id.insert).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				insertData();
			}
		});
		view.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				deleteLastData();
			}
		});
		return  view;
	}

	private void deleteLastData() {

	}

	private void insertData(){
		if (!TextUtils.isEmpty(titleEditText.getText()) && !TextUtils.isEmpty(costEditText.getText())) {
			ContentValues values = new ContentValues();
			values.put(ContentDescriptor.Toys.Cols.TITLE, String.valueOf(titleEditText.getText()));
			values.put(ContentDescriptor.Toys.Cols.COST, Integer.valueOf(String.valueOf(costEditText.getText())));

			new AsyncQueryHandler(getActivity().getContentResolver()) {}.startInsert(1, null, ContentDescriptor.Toys.TABLE_URI, values);
		} else {
			Toast.makeText(getActivity(), R.string.toast_message, Toast.LENGTH_SHORT).show();
		}
	}
}
