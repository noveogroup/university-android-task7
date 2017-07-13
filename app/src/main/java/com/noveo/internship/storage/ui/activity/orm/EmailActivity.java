package com.noveo.internship.storage.ui.activity.orm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.noveo.internship.storage.R;

public class EmailActivity extends BaseOrmLiteActivity {

    public static Intent newIntent(final Context context) {
        return new Intent(context, EmailActivity.class);
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emails);
        setTitle(R.string.emails_title);
    }
}
