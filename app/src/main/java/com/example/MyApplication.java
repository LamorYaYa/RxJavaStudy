package com.example;

import android.app.Application;

/**
 * @author Master
 */
public class MyApplication extends Application {

    private static MyApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }

    public static MyApplication getApplication() {
        return mApplication;
    }

}
