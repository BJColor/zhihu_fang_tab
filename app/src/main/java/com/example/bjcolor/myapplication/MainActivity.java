package com.example.bjcolor.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.bjcolor.myapplication.base.BaseActivity;
import com.example.bjcolor.myapplication.fragment.FourFragment;
import com.example.bjcolor.myapplication.fragment.OneFragment;
import com.example.bjcolor.myapplication.fragment.ThreeFragment;
import com.example.bjcolor.myapplication.fragment.TwoFragment;
import com.example.bjcolor.myapplication.utils.BottomNavigationViewHelper;

public class MainActivity extends BaseActivity {

    private MenuItem menuItem;
    private FourFragment fourFragment;
    private ThreeFragment threeFragment;
    private TwoFragment twoFragment;
    private OneFragment oneFragment;
    private BottomNavigationView navigation;
    private FragmentManager supportFragmentManager;
    private String[] fragmentTags = new String[]{"mzFragment1", "mzFragment2", "mzFragment3", "mzFragment4"};


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            menuItem = item;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    showFragment(0);
                    return true;
                case R.id.navigation_dashboard:
                    showFragment(1);
                    return true;
                case R.id.navigation_notifications:
                    showFragment(2);
                    return true;
                case R.id.navigation_five:
                    showFragment(3);
                    return true;
            }

            return false;
        }
    };
    private int currentIndex;


    @Override
    protected int setContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void setSavedInstanceState(Bundle savedInstanceState) {
        supportFragmentManager = getSupportFragmentManager();

        if (savedInstanceState != null) {
            oneFragment = (OneFragment) supportFragmentManager.findFragmentByTag(fragmentTags[0]);
            twoFragment = (TwoFragment) supportFragmentManager.findFragmentByTag(fragmentTags[1]);
            threeFragment = (ThreeFragment) supportFragmentManager.findFragmentByTag(fragmentTags[2]);
            fourFragment = (FourFragment) supportFragmentManager.findFragmentByTag(fragmentTags[3]);
        }
    }

    @Override
    protected void findView() {
        navigation = f(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//        取消偏移动画
        BottomNavigationViewHelper.disableShiftMode(navigation);
    }

    @Override
    protected void initUI() {
        showFragment(1);
    }

    @Override
    protected void initData() {

    }

    private void showFragment(int index) {
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        hideFragment(fragmentTransaction);

        currentIndex = index;
        if (menuItem != null) {
            menuItem.setChecked(false);
        } else {
            navigation.getMenu().getItem(0).setChecked(false);
        }
        menuItem = navigation.getMenu().getItem(index);
        menuItem.setChecked(true);

        switch (index) {
            case 0:
                if (oneFragment != null) {
                    fragmentTransaction.show(oneFragment);
                } else {
                    oneFragment = new OneFragment();
                    fragmentTransaction.add(R.id.fl_content, oneFragment, fragmentTags[index]);
                }
//                oneFragment.setUserVisibleHint(true);
                break;
            case 1:
                if (twoFragment != null) {
                    fragmentTransaction.show(twoFragment);
                } else {
                    twoFragment = new TwoFragment();
                    fragmentTransaction.add(R.id.fl_content, twoFragment, fragmentTags[index]);
                }
//                twoFragment.setUserVisibleHint(true);
                break;
            case 2:
                if (threeFragment != null) {
                    fragmentTransaction.show(threeFragment);
                } else {
                    threeFragment = new ThreeFragment();
                    fragmentTransaction.add(R.id.fl_content, threeFragment, fragmentTags[index]);
                }
//                threeFragment.setUserVisibleHint(true);
                break;
            case 3:
                if (fourFragment != null) {
                    fragmentTransaction.show(fourFragment);
                } else {
                    fourFragment = new FourFragment();
                    fragmentTransaction.add(R.id.fl_content, fourFragment, fragmentTags[index]);
                }
//                fourFragment.setUserVisibleHint(true);
                break;
        }


        fragmentTransaction.commitAllowingStateLoss();
    }


    private void hideFragment(FragmentTransaction fragmentTransaction) {
        if (oneFragment != null) {
            fragmentTransaction.hide(oneFragment);
        }
        if (twoFragment != null) {
            fragmentTransaction.hide(twoFragment);
        }
        if (threeFragment != null) {
            fragmentTransaction.hide(threeFragment);
        }
        if (fourFragment != null) {
            fragmentTransaction.hide(fourFragment);
        }
    }


    private long exitTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (currentIndex == 0) {
                if ((System.currentTimeMillis() - exitTime) > 1500) {
                    //弹出提示，可以有多种方式
                    Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    exitTime = System.currentTimeMillis();
                } else {
                    finish();
                }
            } else {
                showFragment(0);
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
