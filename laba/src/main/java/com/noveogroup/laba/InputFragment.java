package com.noveogroup.laba;

import android.content.AsyncQueryHandler;
import android.content.ContentValues;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.noveogroup.laba.content.ContentDescriptor;

public class InputFragment extends Fragment {
    private EditText inputTitle;
    private EditText inputCost;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input, container, false);
        inputTitle = (EditText) view.findViewById(R.id.input_title);
        inputCost = (EditText) view.findViewById(R.id.input_cost);
        Button insert = (Button) view.findViewById(R.id.input_insert);
        final Button deleteLast = (Button) view.findViewById(R.id.input_delete_last);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert();
            }
        });

        deleteLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteLast();
            }
        });
        return view;
    }

    private void insert() {
        if(!TextUtils.isEmpty(inputTitle.getText()) && !TextUtils.isEmpty(inputCost.getText())) {
            int cost;
            try {
                cost = Integer.valueOf(inputCost.getText().toString());
            }catch (NumberFormatException e) {
                Toast.makeText(getActivity(), getString(R.string.invalid_cost_message), Toast.LENGTH_LONG).show();
                return;
            }
            ContentValues values = new ContentValues();
            values.put(ContentDescriptor.Toys.Cols.TITLE, inputTitle.getText().toString());
            values.put(ContentDescriptor.Toys.Cols.COST, cost);
            new AsyncQueryHandler(getActivity().getContentResolver()){}.startInsert(1, null, ContentDescriptor.Toys.TABLE_URI, values);
        }
        else {
            Toast.makeText(getActivity(), getString(R.string.empty_field_message), Toast.LENGTH_LONG).show();
        }
    }

    private void deleteLast() {
        String selection = ContentDescriptor.Toys.Cols.ID + " = (select max(" + ContentDescriptor.Toys.Cols.ID + ") from "
                        + ContentDescriptor.Toys.TABLE_NAME + ")";
        new AsyncQueryHandler(getActivity().getContentResolver()){}.startDelete(2, null, ContentDescriptor.Toys.TABLE_URI, selection, null);
    }
}
