package com.demo.browsertoapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Window;

import java.lang.ref.WeakReference;
import java.util.Timer;

public class SplashActivity extends Activity {
    public static final String TAG = "BrowserToApp";

    private static final int WHT_TO_HANDLE_NORMAL = 0x110;
    private static final int WHT_TO_HANDLE_BROWSER = 0x111;
    private static final long DELAY_PERIOD = 3000;
    private static final long COUNT_DOWN_INTERVAL = 1000;
    private boolean isExist;

    private Timer timer;

    private CountDownTimer countDownTimer;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        Log.d(TAG, getClass().getSimpleName() + "->onCreate->taskId: " + getTaskId());
        Log.d(TAG, getClass().getSimpleName() + "->onCreate->action: " + getIntent().getAction());
        setContentView(R.layout.activity_splash);
        mContext = SplashActivity.this;
        final Intent intent = getIntent();

        //1.Handler实现页面优雅跳转
//        isExist = true;
//        final MyHandler myHandler = new MyHandler(this);

        //2.Timer实现页面优雅跳转
//        timer = new Timer();

        //3.CountDownTimer实现页面优雅跳转
        countDownTimer = new CountDownTimer(DELAY_PERIOD, COUNT_DOWN_INTERVAL) {
            @Override
            public void onTick(long millisUntilFinished) {
                //每隔 COUNT_DOWN_INTERVAL 会回调一次方法onTick
            }

            @Override
            public void onFinish() {
                if (intent != null) {
                    if (Intent.ACTION_VIEW.equals(intent.getAction())) {
                        handleAppOpenByOutside(intent);
                    } else if (Intent.ACTION_MAIN.equals(intent.getAction())) {
                        handleAppOpenByNormal();
                    }
                }
            }
        };
        countDownTimer.start();

        if (intent != null) {
            if (Intent.ACTION_VIEW.equals(intent.getAction())) {
//                myHandler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (isExist) {
//                            Message msg = myHandler.obtainMessage();
//                            msg.what = WHT_TO_HANDLE_BROWSER;
//                            msg.obj = intent;
//                            myHandler.sendMessage(msg);
//                        }
//                    }
//                }, DELAY_PERIOD);

//                timer.schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//                        handleAppOpenByOutside(intent);
//                    }
//                }, DELAY_PERIOD);

            } else if (Intent.ACTION_MAIN.equals(intent.getAction())) {
//                myHandler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (isExist) {
//                            myHandler.sendEmptyMessage(WHT_TO_HANDLE_NORMAL);
//                        }
//                    }
//                }, DELAY_PERIOD);

//                timer.schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//                        handleAppOpenByNormal();
//                    }
//                }, DELAY_PERIOD);
            }
        }

    }

    private void handleAppOpenByOutside(Intent outsideIntent) {
        int activitySize = MyApp.getInstance().getActivitySize();
        if (activitySize > 1) {
            Log.d(TAG, "浏览器->应用已打开");
            Activity lastActivity = MyApp.getInstance().getLastActivity();
            if (lastActivity != null) {
                Log.d(TAG, "lastActivity: " + lastActivity.getClass().getSimpleName());
                if (lastActivity instanceof ThirdActivity) {
                    Log.d(TAG, "lastActivity是ThirdActivity");
                    lastActivity.finish();
                }
            }
            jumpFromBrowser(outsideIntent.getData());
        } else {
            Log.d(TAG, "浏览器->应用未打开");
            outsideIntent.setClass(mContext, MainActivity.class);
            outsideIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(outsideIntent);
        }
        finish();
    }

    private void handleAppOpenByNormal() {
        int activitySize = MyApp.getInstance().getActivitySize();
        if (activitySize > 1) {
            Log.d(TAG, "应用已打开");
        } else {
            Log.d(TAG, "应用未打开");
            mContext.startActivity(new Intent(mContext, MainActivity.class));
        }
        finish();
    }

    private void jumpFromBrowser(Uri uri) {
        ThirdActivity.actionStart(this, uri, true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isExist = false;
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }

    static class MyHandler extends Handler {
        private WeakReference<SplashActivity> mActivityRef;

        public MyHandler(SplashActivity activity) {
            mActivityRef = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            SplashActivity activity = mActivityRef.get();
            if (activity != null) {
                if (msg.what == WHT_TO_HANDLE_NORMAL ) {
                    activity.handleAppOpenByNormal();
                } else if (msg.what == WHT_TO_HANDLE_BROWSER) {
                    Intent intent = (Intent) msg.obj;
                    activity.handleAppOpenByOutside(intent);
                }
            }
        }
    }
}
