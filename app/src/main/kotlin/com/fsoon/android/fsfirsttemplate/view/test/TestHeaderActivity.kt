package com.fsoon.android.fsfirsttemplate.view.test

import android.os.Bundle
import com.fsoon.android.fsfirsttemplate.R
import com.fsoon.android.fsfirsttemplate.view.tamplate.FixedHeaderActivity

class TestHeaderActivity : FixedHeaderActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_header)
        init("테스트입니다다다다ㅏ다다다다다테스트입니다다다다ㅏ다다다다다다다")
    }
}