package com.esharp.ams.ui.fragment;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.esharp.ams.R;
import com.esharp.ams.adapter.HomeFragmentPagerAdapter;
import com.esharp.ams.contract.HomeContract;
import com.esharp.ams.contract.MainActContract;
import com.esharp.ams.presenter.HomePresenter;
import com.esharp.sdk.base.BaseMvpFragment;
import com.esharp.sdk.utils.ResUtils;
import com.esharp.sdk.widget.NoScrollViewPager;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

import androidx.viewpager.widget.ViewPager;

public class HomeFragment extends BaseMvpFragment<HomeContract.Presenter, MainActContract.IHost> implements HomeContract.View {

    NoScrollViewPager mViewPager;

    TabLayout mTabLayout;

    private HomeFragmentPagerAdapter mPagerAdapter;

    @Override
    protected Pair<Integer, HomeContract.Presenter> layoutAndPresenter() {
        return Pair.create(R.layout.fragment_home, new HomePresenter(this));
    }

    @Override
    protected void init(View view) {
        mViewPager = view.findViewById(R.id.viewPager);
        mViewPager.setNoScroll(true);
        mTabLayout = view.findViewById(R.id.tabLayout);

        mPagerAdapter = new HomeFragmentPagerAdapter(getChildFragmentManager());
        int count = mPagerAdapter.getCount();
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOffscreenPageLimit(count);

        for (int i = 0; i < count; i++) {
            mTabLayout.addTab(returnTab(mTabLayout, i));
        }

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        Objects.requireNonNull(mTabLayout.getTabAt(0)).select();

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                LogUtils.i(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        mPresenter.workOrderCountProcess();
        mPresenter.workOrderCountOver();
    }

    private TabLayout.Tab returnTab(TabLayout tabLayout, int position) {
        TabLayout.Tab tab = tabLayout.newTab();
        View tabView = LayoutInflater.from(mContext).inflate(R.layout.item_tab, tabLayout, false);
        ImageView iv_icon = tabView.findViewById(R.id.iv_icon);
        TextView title = tabView.findViewById(R.id.tv_title);
        TextView indicator = tabView.findViewById(R.id.count);

        switch (position) {
            case 0: {
                title.setText(ResUtils.getString(R.string.backlog));
                iv_icon.setImageDrawable(ResUtils.getDrawable(R.drawable.selector_tab_backlog));
                indicator.setTextColor(ResUtils.getColor(R.color.white));
            }
            break;
            case 1: {
                title.setText(ResUtils.getString(R.string.done));
                iv_icon.setImageDrawable(ResUtils.getDrawable(R.drawable.selector_tab_done));
                indicator.setTextColor(ResUtils.getColor(R.color.spsdk_main_color));
                indicator.setBackground(null);
            }
            break;
        }
        tab.setCustomView(tabView);
        return tab;
    }

    @Override
    public void workOrderCountProcess(Integer it) {
        LogUtils.i(it);
        TextView count = mTabLayout.getTabAt(0).view.findViewById(R.id.count);
        count.setText(it+"");
    }

    @Override
    public void workOrderCountOver(Integer it) {
        LogUtils.i(it);
        TextView count = mTabLayout.getTabAt(1).view.findViewById(R.id.count);
        count.setText(it+"");
    }
}
