package com.noveo.internship.databaseexample.ui.activity.orm;

import android.os.Bundle;

import com.noveo.internship.databaseexample.R;

public class UserActivity extends BaseOrmLiteActivity {
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        setTitle(R.string.users_title);
    }
}
