package com.am.am_util;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.am.am_util.activity.BaseActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        SplashActivity.this.finish();
    }
}
