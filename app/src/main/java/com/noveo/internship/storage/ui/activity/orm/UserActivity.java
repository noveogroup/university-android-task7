package com.noveo.internship.storage.ui.activity.orm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.noveo.internship.storage.R;

public class UserActivity extends BaseOrmLiteActivity {

    public static Intent newIntent(final Context context) {
        return new Intent(context, UserActivity.class);
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        setTitle(R.string.users_title);
    }
}
