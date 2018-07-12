package com.example.bjcolor.myapplication.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by BJColor on 2018/7/6.
 */

public abstract class BaseFragment extends Fragment {

    private View rootView;
    private boolean isCreate = true;
    private boolean isUserVisible = false;

    protected abstract int contentView();

    protected abstract void findView();

    protected abstract void initUI();

    protected abstract void initData();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(contentView(), container, false);
        findView();
        initUI();
        initData();
        return rootView;
    }

    private void lazyLoad() {
        if (isUserVisible) {
            initData();
        }
    }

    protected final <T extends View> T f(int id) {
        return (T) rootView.findViewById(id);
    }

    @Override
    public void setUserVisibleHint(boolean is) {
//        super.setUserVisibleHint(is);
//        isUserVisible = is;
//        lazyLoad();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
