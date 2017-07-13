package com.noveo.internship.databaseexample.ui.activity.orm;

import android.os.Bundle;

import com.noveo.internship.databaseexample.R;

public class EmailActivity extends BaseOrmLiteActivity {

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emails);
        setTitle(R.string.emails_title);
    }
}
