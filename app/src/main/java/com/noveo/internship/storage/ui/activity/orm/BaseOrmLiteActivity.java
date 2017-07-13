package com.noveo.internship.storage.ui.activity.orm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.noveo.internship.storage.db.DatabaseHelper;

import butterknife.ButterKnife;

public class BaseOrmLiteActivity extends AppCompatActivity {
    protected DatabaseHelper databaseHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseHelper.close();
    }
}
