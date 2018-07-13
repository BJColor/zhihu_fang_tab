package com.example.bjcolor.myapplication.fragment;

import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.bjcolor.myapplication.R;
import com.example.bjcolor.myapplication.adapter.ListAdapter;
import com.example.bjcolor.myapplication.base.BaseFragment;
import com.example.bjcolor.myapplication.tab.SynchroScrollingTab;

/**
 * Created by BJColor on 2018/7/6.
 */

public class FourFragment extends BaseFragment {
    private TabLayout mTabLayout;
    private RecyclerView recyclerView;
    private String[] strings = new String[]{"说说", "空间", "QQ", "微信", "头条", "热点", "娱乐", "体育", "新闻", "采访", "现场", "LIVE", "世界杯", "感动中国", "CCTV", "世界报道"};
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;

    @Override
    protected int contentView() {
        return R.layout.fourfragment;
    }

    @Override
    protected void findView() {
        mTabLayout = (TabLayout) f(R.id.tabs);
        toolbar = (Toolbar) f(R.id.toolbar);
        appBarLayout = f(R.id.appbar);
        recyclerView = f(R.id.recycleView);
    }


    @Override
    protected void initUI() {
        ListAdapter adapter = new ListAdapter(strings);
        new SynchroScrollingTab(getActivity()).setTab(recyclerView, adapter, mTabLayout, strings);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                toolbar.setBackgroundColor(changeAlpha(getResources().getColor(R.color.black), Math.abs(verticalOffset * 1.0f) / appBarLayout.getTotalScrollRange()));
            }
        });
    }

    /**
     * 根据百分比改变颜色透明度
     */
    public int changeAlpha(int color, float fraction) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        int alpha = (int) (Color.alpha(color) * fraction);
        return Color.argb(alpha, red, green, blue);
    }

    @Override
    protected void initData() {

    }
}
