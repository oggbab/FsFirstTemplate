package com.fsoon.android.fsfirsttemplate

import android.app.Application

class FsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        app = this
    }

    companion object {
        lateinit var app: FsApp
            private set
    }
}