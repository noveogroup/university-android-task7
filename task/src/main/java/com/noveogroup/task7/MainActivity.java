package com.noveogroup.task7;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.noveogroup.task7.database.CarsOpenHelper;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CarsOpenHelper.getInstance(this).close();
    }
}
