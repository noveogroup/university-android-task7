package com.noveogroup.laba;

import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class InputFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input, container, false);
        final EditText mTitleEdit = (EditText) view.findViewById(R.id.titleEdit);
        final EditText mTextEdit = (EditText) view.findViewById(R.id.textEdit);
        Button mInsert = (Button) view.findViewById(R.id.insert);
        Button mDeleteLast = (Button) view.findViewById(R.id.delete_last);

        mInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(mTitleEdit.getText()) && !TextUtils.isEmpty(mTextEdit.getText())) {
                    ((MainActivity)getActivity()).addToDB(mTitleEdit.getText().toString(), mTextEdit.getText().toString());
                }
                mTitleEdit.setText("");
                mTextEdit.setText("");
            }
        });

        mDeleteLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).deleteLast();
            }
        });

        return view;
    }
}
