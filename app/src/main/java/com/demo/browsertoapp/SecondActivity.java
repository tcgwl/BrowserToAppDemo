package com.demo.browsertoapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class SecondActivity extends BaseActivity {
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, SecondActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }
}
