package com.fsoon.android.fsfirsttemplate;

import android.app.Activity;
import android.app.Application;

import androidx.core.app.ActivityCompat;

public class FsApp extends Application {
    private static FsApp mApp;

    @Override
    public void onCreate() {
        super.onCreate();

        mApp = this;
    }

    public static FsApp getApp() {
        return mApp;
    }
}


