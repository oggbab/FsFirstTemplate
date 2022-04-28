package com.fsoon.android.fsfirsttemplate;

import android.app.Application;

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


