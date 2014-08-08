package com.noveogroup.internship.task7;

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

import com.noveogroup.internship.task7.provider.ContentContract;

public class EditFragment extends Fragment {

    private EditText mTitleView;
    private EditText mCostView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit, container, false);
        mTitleView = (EditText)view.findViewById(R.id.title);
        mCostView = (EditText)view.findViewById(R.id.cost);
        Button insert = (Button)view.findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });
        Button delLast = (Button)view.findViewById(R.id.del_last);
        delLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String idCol = ContentContract.Toys.Cols._ID;
                final String selection =
                        idCol + " = (select max(" + idCol + ") from "
                        + ContentContract.Toys.TABLE_NAME + ")";
                new AsyncQueryHandler(getActivity().getContentResolver()) { }
                        .startDelete(2, null, ContentContract.Toys.TABLE_URI, selection, null);
            }
        });
        return view;
    }

    private void insertData() {
        if (TextUtils.isEmpty(mTitleView.getText()) || TextUtils.isEmpty(mCostView.getText())) {
            Toast.makeText(getActivity(), R.string.toast_message, Toast.LENGTH_SHORT).show();
            return;
        }
        int cost;
        try {
            cost = Integer.parseInt(String.valueOf(mCostView.getText()));
        } catch (NumberFormatException e) {
            Toast.makeText(getActivity(), R.string.illegal_cost, Toast.LENGTH_SHORT).show();
            return;
        }
        ContentValues values = new ContentValues();
        values.put(ContentContract.Toys.Cols.TITLE, String.valueOf(mTitleView.getText()));
        values.put(ContentContract.Toys.Cols.COST, cost);
        new AsyncQueryHandler(getActivity().getContentResolver()) { }
                .startInsert(1, null, ContentContract.Toys.TABLE_URI, values);
    }

    @Override
    public void onDestroyView() {
        mTitleView = null;
        mCostView = null;
        super.onDestroyView();
    }
}
