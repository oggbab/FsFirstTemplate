package com.fsoon.android.fsfirsttemplate.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fsoon.android.fsfirsttemplate.R

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }
}