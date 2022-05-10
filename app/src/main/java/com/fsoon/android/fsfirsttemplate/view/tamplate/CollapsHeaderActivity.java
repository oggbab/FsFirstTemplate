package com.fsoon.android.fsfirsttemplate.view.tamplate;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fsoon.android.fsfirsttemplate.R;
import com.fsoon.android.fsfirsttemplate.view.base.BaseActivity;
import com.google.android.material.appbar.CollapsingToolbarLayout;


public class CollapsHeaderActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mLeftButton, mRightButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collaps);
    }

    public void init(String title) {
        setTitle(title);
        initButton();
    }

    public void init(String title, boolean lVisible, boolean rVisible) {
        setTitle(title);
        initButton();
        setLeftButtonVisible(lVisible);
        setRightButtonVisible(rVisible);
    }

    public void init(String title, boolean lVisible, boolean rVisible, boolean lEnable, boolean rEnable) {
        setTitle(title);
        initButton();
        setLeftButtonVisible(lVisible);
        setRightButtonVisible(rVisible);
        setLeftButtonEnable(lEnable);
        setRightButtonEnable(rEnable);
    }

    public void setTitle(String title) {
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(title);

        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayShowHomeEnabled(false);

        ab.setDisplayHomeAsUpEnabled(false);
        ab.setDisplayShowCustomEnabled(true);
        ab.setDisplayShowTitleEnabled(false);
        ActionBar.LayoutParams ablp = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);*/
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