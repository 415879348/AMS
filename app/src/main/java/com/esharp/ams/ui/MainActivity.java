package com.esharp.ams.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.esharp.ams.R;
import com.esharp.ams.adapter.MainFragmentPagerAdapter;
import com.esharp.ams.contract.MainActContract;
import com.esharp.ams.presenter.MainActPresenter;
import com.esharp.sdk.base.BaseActivity;
import com.esharp.sdk.base.BaseMvpActivity;
import com.esharp.sdk.utils.ResUtils;
import com.esharp.sdk.widget.NoScrollViewPager;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

import androidx.viewpager.widget.ViewPager;

public class MainActivity extends BaseMvpActivity<MainActContract.Presenter> implements MainActContract.IHost {

    @Override
    protected Pair<Integer, MainActContract.Presenter> layoutAndPresenter() {
        return Pair.create(R.layout.activity_main, new MainActPresenter(this));
    }

    @Override
    public BaseActivity getHost() {
        return MainActivity.this;
    }

    @Override
    protected boolean isShowTitle() {
        return false;
    }

    public static void startActivity(BaseActivity activity) {
        activity.startActivity(new Intent(activity, MainActivity.class));
    }

    NoScrollViewPager mViewPager;

    TabLayout mTabLayout;

    MainFragmentPagerAdapter mPagerAdapter;

    @Override
    protected void init() {
        mViewPager = findViewById(R.id.viewPager);
        mViewPager.setNoScroll(true);
        mTabLayout = findViewById(R.id.tabLayout);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                LogUtils.i(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });

        mPagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager());
        int count = mPagerAdapter.getCount();
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOffscreenPageLimit(count - 1);

        for (int i = 0; i < count; i++) {
            mTabLayout.addTab(returnTab(mTabLayout, i));
        }

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        Objects.requireNonNull(mTabLayout.getTabAt(0)).select();
    }

    @SuppressLint("ClickableViewAccessibility")
    private TabLayout.Tab returnTab(TabLayout tabLayout, int position) {
        TabLayout.Tab tab = tabLayout.newTab();
        View tabView = LayoutInflater.from(MainActivity.this).inflate(R.layout.spsdk_item_tab, tabLayout, false);
        ImageView icon = tabView.findViewById(R.id.iv_icon);
        TextView tile = tabView.findViewById(R.id.tv_title);
        switch (position) {
            case 0: {
                icon.setImageDrawable(ResUtils.getDrawable(R.drawable.selector_tab_home));
                tile.setText(ResUtils.getString(R.string.tab_title_home));
            }
            break;
            case 1: {
                icon.setImageDrawable(ResUtils.getDrawable(R.drawable.selector_tab_asset));
                tile.setText(ResUtils.getString(R.string.tab_title_assets));
            }
            break;
            case 2: {
                icon.setImageDrawable(ResUtils.getDrawable(R.drawable.selector_tab_trouble));
                tile.setText(ResUtils.getString(R.string.tab_title_work_order));
            }
            break;
            case 3: {
                icon.setImageDrawable(ResUtils.getDrawable(R.drawable.selector_tab_profile));
                tile.setText(ResUtils.getString(R.string.tab_title_profile));
            }
            break;
        }

        tab.setCustomView(tabView);

        return tab;
    }


    @Override
    public void onItemClick() {
//        new Handler().postDelayed(() -> Objects.requireNonNull(mTabLayout.getTabAt(2)).select(),1000);
    }
}