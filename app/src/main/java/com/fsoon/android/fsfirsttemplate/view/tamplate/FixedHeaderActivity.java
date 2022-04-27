package com.fsoon.android.fsfirsttemplate.view.tamplate;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.fsoon.android.fsfirsttemplate.R;
import com.fsoon.android.fsfirsttemplate.view.base.BaseActivity;

public class FixedHeaderActivity extends BaseActivity implements View.OnClickListener {

    private ImageView menuButton, backButton;
    private ViewGroup mHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixed_header);
//        initView();
    }

    public void init(String title) {
        setTitle(title);
//        initHeader();
//        initButton();
    }

    public void init(String title, boolean lVisible, boolean rVisible) {
        setTitle(title);
        setBackButtonEnable(lVisible);
        setMenuButtonEnable(rVisible);
    }

    public void setTitle(String title) {

    }

    private void initButton() {
        menuButton = mHeader.findViewById(R.id.menuButton);
        backButton = mHeader.findViewById(R.id.backButton);
        menuButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
    }

    public void initHeader() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mHeader = (ViewGroup) getLayoutInflater().inflate(R.layout.view_base_fixed_header, null);
        mHeader.setBackgroundColor(Color.WHITE);
        mHeader.setOutlineProvider(null);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        actionBar.setCustomView(mHeader, params);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.menuButton:
                break;
            case R.id.backButton:
                break;
        }
    }

    public void setMenuButtonVisible(boolean isVisible) {
        menuButton.setVisibility(isVisible ? View.VISIBLE : View.INVISIBLE);
    }

    public void setMenuButtonEnable(boolean isEnable) {
        menuButton.setEnabled(isEnable);
    }

    public void setBackButtonVisible(boolean isVisible) {
        backButton.setVisibility(isVisible ? View.VISIBLE : View.INVISIBLE);
    }

    public void setBackButtonEnable(boolean isEnable) {
        menuButton.setEnabled(isEnable);
    }

    public void setHeaderVisible(boolean isVisible) {

    }
}