package com.demo.browsertoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by Archer on 2017/6/12.
 */

public class BaseActivity extends AppCompatActivity {
    public static final String TAG = "BrowserToApp";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApp.getInstance().addActivity(this);
        Log.d(TAG, getClass().getSimpleName() + "->onCreate->taskId: " + getTaskId());
        Log.d(TAG, getClass().getSimpleName() + "->onCreate->action: " + getIntent().getAction());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApp.getInstance().removeActivity(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, getClass().getSimpleName() + "->onNewIntent->taskId: " + getTaskId());
        Log.d(TAG, getClass().getSimpleName() + "->onNewIntent->action: " + getIntent().getAction());
    }
}
