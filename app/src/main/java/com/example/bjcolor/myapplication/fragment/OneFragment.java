package com.example.bjcolor.myapplication.fragment;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.bjcolor.myapplication.R;
import com.example.bjcolor.myapplication.adapter.MyPagerAdapter;
import com.example.bjcolor.myapplication.base.BaseFragment;
import com.example.bjcolor.myapplication.childfragment.ChildFragment;
import com.example.bjcolor.myapplication.childfragment.ChildFragment2;
import com.example.bjcolor.myapplication.childfragment.ChildFragment3;
import com.example.bjcolor.myapplication.childfragment.ChildFragment4;

import java.util.ArrayList;

/**
 * Created by BJColor on 2018/7/6.
 */

public class OneFragment extends BaseFragment {

    ViewPager mViewPager;

    private TabLayout mTabLayout;
    private ArrayList<Fragment> fragments;
    private MyPagerAdapter myPagerAdapter;
    private Toolbar toolbar;
    private View small;
    private View search;
    private View big;


    private void initViewPager() {
        if (fragments == null || fragments.size() <= 0) {
            fragments = new ArrayList<>();
            // 装填
            fragments.add(new ChildFragment());
            fragments.add(new ChildFragment2());
            fragments.add(new ChildFragment3());
            fragments.add(new ChildFragment4());

        }
        // 创建ViewPager适配器
        myPagerAdapter = new MyPagerAdapter(getChildFragmentManager());
        myPagerAdapter.setFragments(fragments);
        mViewPager.setAdapter(myPagerAdapter);

        // TabLayout 指示器 (记得自己手动创建4个Fragment,注意是 app包下的Fragment 还是 V4包下的 Fragment)
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());
        // 使用 TabLayout 和 ViewPager 相关联
        mTabLayout.setupWithViewPager(mViewPager);
        // TabLayout指示器添加文本
        mTabLayout.getTabAt(0).setText("头条");
        mTabLayout.getTabAt(1).setText("热点");
        mTabLayout.getTabAt(2).setText("娱乐");
        mTabLayout.getTabAt(3).setText("体育");

    }

    @Override
    protected int contentView() {
        return R.layout.onefragment;
    }

    @Override
    protected void findView() {
        mViewPager = (ViewPager) f(R.id.viewpager);
        mTabLayout = (TabLayout) f(R.id.tabs);
        toolbar = (Toolbar) f(R.id.toolbar);
        search = f(R.id.include_toolbar_search);
        small = f(R.id.include_toolbar_small);
        big = f(R.id.include_titl_big);
        AppBarLayout appBarLayout = (AppBarLayout) f(R.id.appbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                float move = 1.0f - Math.abs(verticalOffset * 1.0f) / appBarLayout.getTotalScrollRange();
                float move2 = Math.abs(verticalOffset * 1.0f) / appBarLayout.getTotalScrollRange();
                search.setAlpha(move);
                big.setAlpha(move);
                small.setAlpha(move2);

                if (move2 <= 0.4) {
                    search.setVisibility(View.VISIBLE);
                    small.setVisibility(View.GONE);
                } else {
                    small.setVisibility(View.VISIBLE);
                    search.setVisibility(View.GONE);
                }

            }
        });
    }

    @Override
    protected void initUI() {
        initViewPager();
    }

    @Override
    protected void initData() {

    }
}
