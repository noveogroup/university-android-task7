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

import com.noveogroup.task7.app2.db.ContentDescriptor;


public class InsertFragment extends Fragment {

	public static final int DELETE_LAST_TOKEN = 2;
	public static final int INSERT_TOKEN = 1;
	EditText titleEditText;
	EditText costEditText;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.input_fragment, container, false);
		titleEditText = (EditText) view.findViewById(R.id.titleEdit);
		costEditText = (EditText) view.findViewById(R.id.costEdit);
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
		String id = ContentDescriptor.Toys.Cols.ID;
		String selection = id + " = (select max(" + id + ") from "+
				ContentDescriptor.Toys.TABLE_NAME + ")";
		new AsyncQueryHandler(getActivity().getContentResolver()) {}
				.startDelete(DELETE_LAST_TOKEN, null, ContentDescriptor.Toys.TABLE_URI, selection, null);
	}

	private void insertData(){
		if (!TextUtils.isEmpty(titleEditText.getText()) && !TextUtils.isEmpty(costEditText.getText())) {

			ContentValues values = new ContentValues();
			values.put(ContentDescriptor.Toys.Cols.TITLE, String.valueOf(titleEditText.getText()));
			String costText = String.valueOf(costEditText.getText());
			try {
				values.put(ContentDescriptor.Toys.Cols.COST, Integer.valueOf(costText));
			} catch (NumberFormatException ignored) {
				Toast.makeText(getActivity(), getString(R.string.cant_accept_cost_text, costText), Toast.LENGTH_SHORT).show();
				return;
			}


			new AsyncQueryHandler(getActivity().getContentResolver()) {
			}.startInsert(INSERT_TOKEN, null, ContentDescriptor.Toys.TABLE_URI, values);
		} else {
			Toast.makeText(getActivity(), R.string.toast_message, Toast.LENGTH_SHORT).show();
		}
	}
}
