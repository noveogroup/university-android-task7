package com.noveo.internship.storage.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.noveo.internship.storage.R;
import com.noveo.internship.storage.ui.activity.orm.EmailActivity;
import com.noveo.internship.storage.ui.activity.orm.UserActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_users)
    public void onUsersButtonClick() {
        startActivity(UserActivity.newIntent(this));
    }

    @OnClick(R.id.button_emails)
    public void onEmailsButtonClick() {
        startActivity(EmailActivity.newIntent(this));
    }
}
