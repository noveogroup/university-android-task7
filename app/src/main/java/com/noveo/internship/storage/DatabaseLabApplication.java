package com.noveo.internship.storage;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class DatabaseLabApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
