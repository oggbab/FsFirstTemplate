package com.fsoon.android.fsfirsttemplate.view.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fsoon.android.fsfirsttemplate.R;
import com.fsoon.android.fsfirsttemplate.common.util.Errors;
import com.fsoon.android.fsfirsttemplate.net.APIConstants;
import com.fsoon.android.fsfirsttemplate.net.ApiManager;
import com.fsoon.android.fsfirsttemplate.net.model.goodpay.ResponseAppVersion;
import com.fsoon.android.fsfirsttemplate.net.model.search.ResponseShopSearch;
import com.fsoon.android.fsfirsttemplate.net.model.search.ResponseShopSearchItem;
import com.fsoon.android.fsfirsttemplate.view.base.BaseActivity;
import com.fsoon.android.fsfirsttemplate.view.tamplate.CollapsHeaderActivity;

import java.util.ArrayList;

public class TestActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initButton();
    }

    private void initButton() {
        Button networkTestButton = findViewById(R.id.networkTestButton);
        Button headerTestButton = findViewById(R.id.headerTestButton);

        networkTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), TestNetworkActivity.class);
                startActivity(intent);

            }
        });

        headerTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), TestHeaderActivity.class);
                startActivity(intent);

            }
        });


    }
}