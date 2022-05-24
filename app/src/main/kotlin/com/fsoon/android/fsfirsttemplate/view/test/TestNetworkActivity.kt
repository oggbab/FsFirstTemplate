package com.fsoon.android.fsfirsttemplate.view.test

import com.fsoon.android.fsfirsttemplate.view.base.BaseActivity

class TestNetworkActivity : BaseActivity() {
/*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_network)
        //     requestNaverApi();
        requestGoodpayVersion()
    }

    private fun requestNaverApi() {
        ApiManager.getInstance(this)
            .requestSearchShop(
                "모자",
                1,
                15,
                "sim",
                object : ApiManager.OnNetworkListener<ResponseShopSearch?> {
                    override fun OnNetworkResult(
                        requestId: APIConstants.URL,
                        res: ResponseShopSearch
                    ) {
                        if (res != null && res.isSuccess) {
                            val itemList = res.items
                            if (itemList!!.size > 0) {
                            }
                        } else {
                            Errors.show(this@TestNetworkActivity, res, false)
                        }
                    }

                    override fun OnNetworkResult(
                        requestId: APIConstants.URL?,
                        res: ResponseShopSearch?
                    ) {
                        TODO("Not yet implemented")
                    }
                })
    }

    private fun requestGoodpayVersion() {
        ApiManager.getInstance(this)
            .requestAppVersion(
                "AND", "01.00.01", "com.kt.android.goodpay",
                object : ApiManager.OnNetworkListener<ResponseAppVersion> {
                    override fun OnNetworkResult(
                        requestId: APIConstants.URL,
                        res: Any
                    ) {
                        if (res != null && res.isSuccess()) {
                            Toast.makeText(
                                this@TestNetworkActivity,
                                "version : " + res.data?.appVersion,
                                Toast.LENGTH_LONG
                            ).show()
                        } else {
                            Errors.show(this@TestNetworkActivity, res, true)
                        }
                    }
                })
    }*/
}