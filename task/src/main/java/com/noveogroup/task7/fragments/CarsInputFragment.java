package com.noveogroup.task7.fragments;


import android.content.AsyncQueryHandler;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.noveogroup.task7.R;
import com.noveogroup.task7.database.CarsContract;

public class CarsInputFragment extends Fragment {

    private EditText modelInput;
    private EditText priceInput;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_input, container, false);

        modelInput = (EditText) view.findViewById(R.id.model_input);
        priceInput = (EditText) view.findViewById(R.id.price_input);

        view.findViewById(R.id.insert_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertItem();
            }
        });

        view.findViewById(R.id.delete_last_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteLastItem();
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        modelInput = null;
        priceInput = null;
    }

    private void insertItem() {
        String model = modelInput.getText().toString();
        String price = priceInput.getText().toString();
        if(model.isEmpty() || price.isEmpty()) {
            Toast.makeText(getActivity(), getResources()
                    .getString(R.string.empty_fields_notification), Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            ContentValues values = new ContentValues();
            values.put(CarsContract.Cars.Columns.MODEL, model);
            values.put(CarsContract.Cars.Columns.PRICE, Integer.valueOf(price));

            new AsyncQueryHandler(getActivity().getContentResolver()) {
            }
                    .startInsert(0, null, CarsContract.Cars.TABLE_URI, values);
        } catch (NumberFormatException exception) {
            Toast.makeText(getActivity(), getResources()
                    .getString(R.string.too_big_price_notification), Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteLastItem() {
        Uri uri = CarsContract.Cars.TABLE_URI.buildUpon()
                .appendQueryParameter(CarsContract.QUERY_PARAMETER_LIMIT, "1").build();

        new AsyncQueryHandler(getActivity().getContentResolver()) {
            @Override
            protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
                super.onQueryComplete(token, cookie, cursor);
                if (cursor == null || cursor.getCount() == 0) {
                    return;
                }

                cursor.moveToFirst();
                int id = cursor.getInt(0);
                startDelete(0, null, CarsContract.Cars.TABLE_URI,
                        CarsContract.Cars.Columns.ID + " = ?", new String[]{String.valueOf(id)});

            }
        }.startQuery(0, null, uri, null, null, null, CarsContract.Cars.Columns.ID + " DESC");
    }
}
