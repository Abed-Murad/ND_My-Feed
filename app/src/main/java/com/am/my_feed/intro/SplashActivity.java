package com.am.my_feed.intro;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.am.my_feed.main.MainActivity;
import com.am.my_feed.util.BaseActivity;


public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startMainActivity();
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        SplashActivity.this.finish();
    }
}
