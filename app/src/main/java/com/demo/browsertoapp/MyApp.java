package com.demo.browsertoapp;

import android.app.Activity;
import android.app.Application;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Archer on 2017/6/12.
 */

public class MyApp extends Application {
    private List<Activity> activityList = new LinkedList<>();
    private static MyApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public MyApp() {
        instance = this;
    }

    public static MyApp getInstance() {
        return instance;
    }

    public synchronized void addActivity(Activity activity) {
        if (!activityList.contains(activity)) {
            activityList.add(activity);
        }
    }

    public synchronized void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    public int getActivitySize() {
        return activityList.size();
    }

    public Activity getLastActivity() {
        if (activityList != null && activityList.size() > 0) {
            return activityList.get(activityList.size() - 1);
        }
        return null;
    }
}
