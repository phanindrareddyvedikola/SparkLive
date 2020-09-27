package com.litmusworld.sparklivetest;

import android.app.Application;
import android.content.Context;

import androidx.annotation.Nullable;

public class SparkApplication extends Application {


    private static SparkApplication sInstance;

    @Nullable
    public static Context getAppContext() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

}
