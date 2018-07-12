package com.example.bjcolor.myapplication.fragment;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.bjcolor.myapplication.R;
import com.example.bjcolor.myapplication.adapter.ListAdapter;
import com.example.bjcolor.myapplication.base.BaseFragment;
import com.example.bjcolor.myapplication.tab.SynchroScrollingTab;

/**
 * Created by BJColor on 2018/7/6.
 */

public class ThreeFragment extends BaseFragment {

    private TabLayout mTabLayout;
    private RecyclerView recyclerView;
    private String[] strings = new String[]{"说说", "空间", "QQ", "微信", "头条", "热点", "娱乐", "体育", "新闻", "采访", "现场", "LIVE", "世界杯", "感动中国", "CCTV", "世界报道"};

    @Override
    protected int contentView() {
        return R.layout.threefragment;
    }

    @Override
    protected void findView() {
        mTabLayout = (TabLayout) f(R.id.tabs);
        recyclerView = f(R.id.recycleView);
        setupToolbar();
    }


    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) f(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    }

    @Override
    protected void initUI() {
        ListAdapter adapter = new ListAdapter(strings);
        new SynchroScrollingTab(getActivity()).setTab(recyclerView, adapter, mTabLayout, strings);
    }

    @Override
    protected void initData() {

    }
}
