package com.am.my_feed.intro;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.am.my_feed.test.TestActivity;
import com.am.my_feed.util.BaseActivity;


public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startMainActivity();
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);
        SplashActivity.this.finish();
    }
}
