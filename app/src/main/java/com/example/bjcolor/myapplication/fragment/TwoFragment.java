package com.example.bjcolor.myapplication.fragment;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.bjcolor.myapplication.R;
import com.example.bjcolor.myapplication.adapter.TwoFragmentAdapter;
import com.example.bjcolor.myapplication.base.BaseFragment;
import com.example.bjcolor.myapplication.tab.Bm_LinearLayoutManager;

/**
 * Created by BJColor on 2018/7/6.
 */

public class TwoFragment extends BaseFragment implements TabLayout.OnTabSelectedListener {

    private TabLayout mTabLayout;
    private RecyclerView recyclerView;
    private TwoFragmentAdapter adapter;
    private Bm_LinearLayoutManager advertiseLinearLayoutManager;
    //用来标记是否正在向最后一个滑动
    private int position;
    private int currentPostion;
    private boolean isTabSelect;

    @Override
    protected int contentView() {
        return R.layout.twofragment;
    }

    @Override
    protected void findView() {
        mTabLayout = (TabLayout) f(R.id.tabs);
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        recyclerView = f(R.id.recycleView);
        Toolbar toolbar = (Toolbar) f(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

    }

    private String[] strings = new String[]{"说说", "空间", "QQ", "微信", "头条", "热点", "娱乐", "体育", "新闻", "采访", "现场", "LIVE", "世界杯", "感动中国", "CCTV", "世界报道"};


    @Override
    protected void initUI() {
        // TabLayout 指示器 (记得自己手动创建4个Fragment,注意是 app包下的Fragment 还是 V4包下的 Fragment)
        for (int i = 0; i < strings.length; i++) {
            mTabLayout.addTab(mTabLayout.newTab());
            // TabLayout指示器添加文本
            mTabLayout.getTabAt(i).setText(strings[i]);
        }
        advertiseLinearLayoutManager = new Bm_LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(advertiseLinearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        adapter = new TwoFragmentAdapter(getActivity(), strings);
        recyclerView.setAdapter(adapter);
        mTabLayout.addOnTabSelectedListener(this);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                int totalItemCount = manager.getItemCount();

                if (lastVisibleItem == (totalItemCount - 1)) {
                    position = lastVisibleItem;
                } else {
                    position = manager.findFirstVisibleItemPosition();
                }

                if (isTabSelect) {//tab 选中了 就不在设置 select
                    if (position == currentPostion) {//知道选中的tab 等于 当前滚动到的tab在选择
                        isTabSelect = false;
                    }
                    return;
                } else {
                    if (position == currentPostion) {//防止重复设置
                        return;
                    }
                    mTabLayout.getTabAt(position).select();
                }

            }
        });
    }

    @Override
    protected void initData() {
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        currentPostion = tab.getPosition();
        if (position == currentPostion) {
            return;
        }
        recyclerView.smoothScrollToPosition(currentPostion);
        isTabSelect = true;
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }
}
