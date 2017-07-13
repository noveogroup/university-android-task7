package com.noveo.internship.databaseexample.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.noveo.internship.databaseexample.R;
import com.noveo.internship.databaseexample.ui.activity.orm.EmailActivity;
import com.noveo.internship.databaseexample.ui.activity.orm.UserActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_users).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, UserActivity.class));
            }
        });

        findViewById(R.id.button_emails).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, EmailActivity.class));
            }
        });
    }
}
