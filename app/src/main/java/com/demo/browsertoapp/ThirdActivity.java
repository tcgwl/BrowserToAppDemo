package com.demo.browsertoapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class ThirdActivity extends BaseActivity {
    private static final String TAG = "BrowserToApp";

    public static void actionStart(Context context, Uri uri, boolean needNewTask) {
        Intent intent = new Intent(context, ThirdActivity.class);
        if (uri != null) {
            intent.setData(uri);
        }
        if (needNewTask) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Intent intent = getIntent();
        if (intent != null && intent.getData() != null) {
            Uri uri = intent.getData();
            Log.e(TAG, "uri=" + uri.toString());
            String uid = uri.getQueryParameter("uid");
            Log.e(TAG, "uid=" + uid);
        }
    }
}
