package com.fsoon.android.fsfirsttemplate.view.test;

import android.os.Bundle;

import com.fsoon.android.fsfirsttemplate.R;
import com.fsoon.android.fsfirsttemplate.common.util.Errors;
import com.fsoon.android.fsfirsttemplate.net.APIConstants;
import com.fsoon.android.fsfirsttemplate.net.NetworkManager;
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

        request();
    }

    private void request() {

        NetworkManager.getInstance(this).requestSearchShop("모자", 1, 15, "sim", new NetworkManager.OnNetworkListener<ResponseShopSearch>() {
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
}