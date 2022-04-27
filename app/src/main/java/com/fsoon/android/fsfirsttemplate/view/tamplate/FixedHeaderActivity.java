package com.fsoon.android.fsfirsttemplate.view.tamplate;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.fsoon.android.fsfirsttemplate.R;
import com.fsoon.android.fsfirsttemplate.view.base.BaseActivity;

public class FixedHeaderActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mLeftButton, mRightButton;
    private RelativeLayout mHeader;
    private ViewGroup mHeaderViewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixed_header);
        mHeader = findViewById(R.id.header);
//                mHeaderViewGroup = (ViewGroup) getLayoutInflater().inflate(R.layout.view_base_fixed_header, null);
    }

    public void init(String title) {
//        initHeader();
        initButton();
        setTitle(title);
    }

    public void init(String title, boolean lVisible, boolean rVisible) {
        initHeader();
        initButton();
        setTitle(title);
        setLeftButtonVisible(lVisible);
        setRightButtonVisible(rVisible);
    }

    public void init(String title, boolean lVisible, boolean rVisible, boolean lEnable, boolean rEnable) {
        initHeader();
        initButton();
        setTitle(title);
        setLeftButtonVisible(lVisible);
        setRightButtonVisible(rVisible);
        setLeftButtonEnable(lEnable);
        setRightButtonEnable(rEnable);
    }

    public void setTitle(String title) {
        TextView titleTextView = mHeader.findViewById(R.id.titleTextView);
        titleTextView.setText(title);
    }

    private void initButton() {
        mLeftButton = mHeader.findViewById(R.id.rightButton);
        mRightButton = mHeader.findViewById(R.id.leftButton);
        mLeftButton.setOnClickListener(this);
        mRightButton.setOnClickListener(this);
    }

    public void initHeader() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);//기본 제목을 없애줍니다.
        actionBar.setDisplayHomeAsUpEnabled(true);
//        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
//        actionBar.setCustomView(mHeaderViewGroup, params);
        actionBar.setCustomView(mHeaderViewGroup);

/*        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mHeaderViewGroup = (ViewGroup) getLayoutInflater().inflate(R.layout.view_base_fixed_header, null);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        actionBar.setCustomView(mHeaderViewGroup, params);*/
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