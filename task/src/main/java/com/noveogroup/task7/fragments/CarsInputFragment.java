package com.noveogroup.task7.fragments;


import android.content.AsyncQueryHandler;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.noveogroup.task7.R;
import com.noveogroup.task7.database.CarsContract;
import com.noveogroup.task7.database.CarsOpenHelper;

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

        final CarsOpenHelper openHelper = CarsOpenHelper.getInstance(getActivity());
        final SQLiteDatabase database = openHelper.getWritableDatabase();
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                database.execSQL("DELETE FROM " + CarsContract.Cars.TABLE_NAME
                        + " WHERE " + CarsContract.Cars.Columns.ID
                        + " = (SELECT " + CarsContract.Cars.Columns.ID + " FROM "
                        + CarsContract.Cars.TABLE_NAME + " ORDER BY "
                        + CarsContract.Cars.Columns.ID + " DESC LIMIT 1)");
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);

                getActivity().getContentResolver().notifyChange(CarsContract.Cars.TABLE_URI, null);
            }
        }.execute();
    }
}
