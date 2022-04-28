package com.fsoon.android.fsfirsttemplate.view.tamplate;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fsoon.android.fsfirsttemplate.R;
import com.fsoon.android.fsfirsttemplate.common.util.LogUtil;
import com.fsoon.android.fsfirsttemplate.view.base.BaseActivity;

public class FixedHeaderActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mLeftButton, mRightButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void init(String title) {
        initViews();
        setTitle(title);
    }

    public void init(String title, boolean lVisible, boolean rVisible) {
        initViews();
        setTitle(title);
        setLeftButtonVisible(lVisible);
        setRightButtonVisible(rVisible);
    }

    private void initViews() {
        mLeftButton = findViewById(R.id.leftButton);
        mRightButton = findViewById(R.id.rightButton);
        mLeftButton.setOnClickListener(this);
        mRightButton.setOnClickListener(this);
    }

    public void setTitle(String title) {
        TextView titleTextView = findViewById(R.id.titleTextView);
        titleTextView.setText(title);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.leftButton:
                onClickToolbarLeftButton();
                break;
            case R.id.rightButton:
                onClickToolbarRightButton();
                break;
        }
    }

    public void setLeftButtonVisible(boolean isVisible) {
        mLeftButton.setVisibility(isVisible ? View.VISIBLE : View.INVISIBLE);
        mLeftButton.setEnabled(isVisible);
    }

    public void setRightButtonVisible(boolean isVisible) {
        mRightButton.setVisibility(isVisible ? View.VISIBLE : View.INVISIBLE);
        mRightButton.setEnabled(isVisible);
    }

    protected void onClickToolbarLeftButton() {

    }

    protected void onClickToolbarRightButton() {

    }
}