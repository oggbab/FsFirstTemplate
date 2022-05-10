package com.fsoon.android.fsfirsttemplate.view.tamplate;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fsoon.android.fsfirsttemplate.R;
import com.fsoon.android.fsfirsttemplate.view.base.BaseActivity;

public class ScrollingHeaderActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mLeftButton, mRightButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling_header);
    }

    public void init(String title) {
        initButton();
        setTitle(title);
    }

    public void init(String title, boolean lVisible, boolean rVisible) {
        initButton();
        setTitle(title);
        setLeftButtonVisible(lVisible);
        setRightButtonVisible(rVisible);
    }

    public void init(String title, boolean lVisible, boolean rVisible, boolean lEnable, boolean rEnable) {
        initButton();
        setTitle(title);
        setLeftButtonVisible(lVisible);
        setRightButtonVisible(rVisible);
        setLeftButtonEnable(lEnable);
        setRightButtonEnable(rEnable);
    }

    public void setTitle(String title) {
        TextView titleTextView = findViewById(R.id.titleTextView);
        titleTextView.setText(title);
    }

    private void initButton() {
        mLeftButton = findViewById(R.id.rightButton);
        mRightButton = findViewById(R.id.leftButton);
        mLeftButton.setOnClickListener(this);
        mRightButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.leftButton:
                break;
            case R.id.rightButton:
                break;
        }
    }

    public void setLeftButtonVisible(boolean isVisible) {
        mLeftButton.setVisibility(isVisible ? View.VISIBLE : View.INVISIBLE);
        mLeftButton.setClickable(isVisible);
    }

    public void setRightButtonVisible(boolean isVisible) {
        mRightButton.setVisibility(isVisible ? View.VISIBLE : View.INVISIBLE);
        mRightButton.setClickable(isVisible);
    }

    public void setLeftButtonEnable(boolean isEnable) {
        mLeftButton.setEnabled(isEnable);
    }

    public void setRightButtonEnable(boolean isEnable) {
        mRightButton.setEnabled(isEnable);
    }
}