package com.fsoon.android.fsfirsttemplate.view.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.fsoon.android.fsfirsttemplate.R;
import com.fsoon.android.fsfirsttemplate.view.tamplate.FixedHeaderActivity;

public class TestActivity extends FixedHeaderActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        init("테스트입니다다다다ㅏ다다다다다테스트입니다다다다ㅏ다다다다다다다");
    }
}