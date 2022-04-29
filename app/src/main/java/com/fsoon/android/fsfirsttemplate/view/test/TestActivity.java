package com.fsoon.android.fsfirsttemplate.view.test;

import android.os.Bundle;
import android.widget.Toast;

import com.fsoon.android.fsfirsttemplate.R;
import com.fsoon.android.fsfirsttemplate.common.util.Errors;
import com.fsoon.android.fsfirsttemplate.net.APIConstants;
import com.fsoon.android.fsfirsttemplate.net.ApiManager;
import com.fsoon.android.fsfirsttemplate.net.model.goodpay.ResponseAppVersion;
import com.fsoon.android.fsfirsttemplate.net.model.search.ResponseShopSearch;
import com.fsoon.android.fsfirsttemplate.net.model.search.ResponseShopSearchItem;
import com.fsoon.android.fsfirsttemplate.view.tamplate.FixedHeaderActivity;

import java.util.ArrayList;

public class TestActivity extends FixedHeaderActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        init("테스트입니다다다다ㅏ다다다다다테스트입니다다다다ㅏ다다다다다다다");

//        request();
        requestGoodpayVersion();
    }

    private void request() {

        ApiManager.getInstance(this).requestSearchShop("모자", 1, 15, "sim", new ApiManager.OnNetworkListener<ResponseShopSearch>() {
            @Override
            public void OnNetworkResult(APIConstants.URL requestId, ResponseShopSearch res) {
                if (res != null && res.isSuccess()) {
                    ArrayList<ResponseShopSearchItem> itemList = res.items;
                    if (itemList.size() > 0) {

                    }
                } else {
                    Errors.show(TestActivity.this, res, false);
                }
            }
        });
    }

    private void requestGoodpayVersion() {
        ApiManager.getInstance(this).requestAppVersion("AND", "01.00.01", "com.kt.android.goodpay", new ApiManager.OnNetworkListener<ResponseAppVersion>() {
            @Override
            public void OnNetworkResult(APIConstants.URL requestId, ResponseAppVersion res) {
                if(res != null && res.isSuccess()) {
                    Toast.makeText(TestActivity.this, "version : " + res.data.appVersion, Toast.LENGTH_LONG).show();
                } else {
                    Errors.show(TestActivity.this, res, true);
                }
            }
        });
    }
}