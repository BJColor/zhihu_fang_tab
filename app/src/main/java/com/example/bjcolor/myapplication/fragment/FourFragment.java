package com.example.bjcolor.myapplication.fragment;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.bjcolor.myapplication.R;
import com.example.bjcolor.myapplication.adapter.ListAdapter;
import com.example.bjcolor.myapplication.base.BaseFragment;
import com.example.bjcolor.myapplication.tab.SynchroScrollingTab;
import com.example.bjcolor.myapplication.utils.ColorUtil;

/**
 * Created by BJColor on 2018/7/6.
 */

public class FourFragment extends BaseFragment implements View.OnClickListener {
    private TabLayout mTabLayout;
    private RecyclerView recyclerView;
    private String[] strings = new String[]{"说说", "空间", "QQ", "微信", "头条", "热点", "娱乐", "体育", "新闻", "采访", "现场", "LIVE", "世界杯", "感动中国", "CCTV", "世界报道"};
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private ImageView icon;
    private RelativeLayout barRl;

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
        barRl = f(R.id.bar_rl);
        icon = f(R.id.iv_icon);
        icon.setOnClickListener(this);
    }


    @Override
    protected void initUI() {
        ListAdapter adapter = new ListAdapter(strings, getActivity());
        new SynchroScrollingTab(getActivity()).setTab(recyclerView, adapter, mTabLayout, strings);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                appBarLayout.getTotalScrollRange()  bar的总高度   1032
//                verticaloffset   距离    -1032

                Log.e("mzzzzzzzzzz;", verticalOffset + "");
                toolbar.setBackgroundColor(ColorUtil.changeAlpha(getResources().getColor(R.color.black), Math.abs(verticalOffset * 1.0f) / appBarLayout.getTotalScrollRange()));
                barRl.setAlpha(1.0f - Math.abs(verticalOffset * 1.0f) / appBarLayout.getTotalScrollRange());
            }
        });
        adapter.setItemClick(new ListAdapter.ItemClick() {
            @Override
            public void setItemOnClick(View view, int pos) {
                switch (view.getId()) {
                    case R.id.tv_content:
                        Toast.makeText(getActivity(), "内容---" + pos, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.list_item:
                        Toast.makeText(getActivity(), "条目---" + pos, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }


    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        Toast.makeText(getActivity(), "头像", Toast.LENGTH_SHORT).show();
    }
}
