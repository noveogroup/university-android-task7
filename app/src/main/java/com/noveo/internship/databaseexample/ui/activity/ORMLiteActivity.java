package com.noveo.internship.databaseexample.ui.activity;

import android.app.Activity;
import android.os.Bundle;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.noveo.internship.databaseexample.db.ORMLiteOpenHelper;

public class ORMLiteActivity extends Activity {

    private ORMLiteOpenHelper openHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openHelper = OpenHelperManager.getHelper(this, ORMLiteOpenHelper.class);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        openHelper.close();
    }
}
