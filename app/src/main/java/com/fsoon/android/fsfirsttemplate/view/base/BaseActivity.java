package com.fsoon.android.fsfirsttemplate.view.base;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.fsoon.android.fsfirsttemplate.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }
}