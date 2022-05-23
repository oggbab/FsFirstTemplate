package com.fsoon.android.fsfirsttemplate.view.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fsoon.android.fsfirsttemplate.R;
import com.fsoon.android.fsfirsttemplate.view.base.BaseActivity;

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