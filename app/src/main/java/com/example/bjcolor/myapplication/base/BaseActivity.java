package com.example.bjcolor.myapplication.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by BJColor on 2018/7/6.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected abstract int setContentView();
    protected abstract void setSavedInstanceState(Bundle savedInstanceState);

    protected abstract void findView();

    protected abstract void initUI();
    protected abstract void initData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(setContentView());
        setSavedInstanceState(savedInstanceState);
        findView();
        initUI();
        initData();
    }

//    public void setToolbar(String title, int gravity) {
//            TextView toolbarTitle = f(R.id.toolbar);
//            if (toolbarTitle == null)
//                return;
//            toolbarTitle.setText(title);
//            toolbarTitle.setGravity(gravity);
//
//    }


    protected final <T extends View> T f(int id) {
        return (T) findViewById(id);
    }

}
