package com.noveo.internship.databaseexample.ui.fragments;

import android.content.AsyncQueryHandler;
import android.content.ContentValues;
import android.os.Bundle;
import android.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.noveo.internship.databaseexample.R;
import com.noveo.internship.databaseexample.db.ContentDescriptor;

public class EditorFragment extends Fragment {
    private EditText titleEditText;
    private EditText costEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_editor, container, false);

        titleEditText = (EditText)view.findViewById(R.id.title_edit);
        costEditText = (EditText)view.findViewById(R.id.cost_edit);

        Button insertButton = (Button)view.findViewById(R.id.insert);
        final Button deleteLastButton = (Button)view.findViewById(R.id.delete_last);

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });

        deleteLastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteLastItem();
            }
        });
        return view;
    }

    private void insertData() {
        if (!TextUtils.isEmpty(titleEditText.getText()) && !TextUtils.isEmpty(costEditText.getText())) {
            ContentValues values = new ContentValues();
            values.put(ContentDescriptor.Toys.Cols.TITLE, String.valueOf(titleEditText.getText()));
            values.put(ContentDescriptor.Toys.Cols.COST, Integer.valueOf(String.valueOf(costEditText.getText())));

            new AsyncQueryHandler(getActivity().getContentResolver()) {}.startInsert(1, null, ContentDescriptor.Toys.TABLE_URI, values);
        } else {
            Toast.makeText(getActivity(), R.string.toast_message, Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteLastItem() {
        String selection = ContentDescriptor.Toys.Cols.ID + " = (SELECT MAX(" + ContentDescriptor.Toys.Cols.ID + ") FROM `" + ContentDescriptor.Toys.TABLE_NAME + "` LIMIT 1)";
        new AsyncQueryHandler(getActivity().getContentResolver()) {}.startDelete(1, null, ContentDescriptor.Toys.TABLE_URI, selection, null);
    }
}
