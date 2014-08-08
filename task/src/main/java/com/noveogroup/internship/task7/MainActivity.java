package com.noveogroup.internship.task7;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.noveogroup.internship.task7.provider.OpenHelper;

public class MainActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OpenHelper.releaseInstance();
    }
}
