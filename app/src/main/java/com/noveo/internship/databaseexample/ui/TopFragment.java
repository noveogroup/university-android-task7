package com.noveo.internship.databaseexample.ui;

import android.content.AsyncQueryHandler;
import android.content.ContentValues;
import android.os.Bundle;
import android.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.noveo.internship.databaseexample.R;
import com.noveo.internship.databaseexample.db.ContentDescriptor;
import com.noveo.internship.databaseexample.db.OpenHelper;

public class TopFragment extends Fragment {

    private EditText titleEditText;
    private EditText costEditText;
    private static final int TOKEN = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_top, container, false);

        titleEditText = (EditText) view.findViewById(R.id.titleEdit);
        costEditText = (EditText) view.findViewById(R.id.costEdit);

        View insert = view.findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });

        View deleteLast = view.findViewById(R.id.delete_last);
        deleteLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteLast();
            }
        });

        return view;
    }

    private void insertData() {
        if (!TextUtils.isEmpty(titleEditText.getText()) && !TextUtils.isEmpty(costEditText.getText())) {
            ContentValues values = new ContentValues();
            values.put(ContentDescriptor.Toys.Cols.TITLE, String.valueOf(titleEditText.getText()));
            values.put(ContentDescriptor.Toys.Cols.COST, Integer.valueOf(String.valueOf(costEditText.getText())));

            new AsyncQueryHandler(getActivity().getContentResolver()) {}.startInsert(TOKEN, null, ContentDescriptor.Toys.TABLE_URI, values);
        } else {
            Toast.makeText(getActivity(), R.string.toast_message, Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteLast() {
        String selection = ContentDescriptor.Toys.Cols.ID + " = (SELECT MAX(" + ContentDescriptor.Toys.Cols.ID + ") FROM `" + ContentDescriptor.Toys.TABLE_NAME + "` LIMIT 1)";
        new AsyncQueryHandler(getActivity().getContentResolver()) {}.startDelete(TOKEN, null, ContentDescriptor.Toys.TABLE_URI, selection, null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        OpenHelper.getInstance(getActivity()).close();
    }
}

