package com.continental.ams.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.continental.ams.R;
import com.continental.ams.adapter.MainFragmentPagerAdapter;
import com.continental.ams.contract.MainActContract;
import com.continental.ams.eventbus.EventAlert;
import com.continental.ams.eventbus.EventBacklog;
import com.continental.ams.factory.FragmentFactory;
import com.continental.ams.presenter.MainActPresenter;
import com.continental.ams.ui.fragment.HomeFragment;
import com.continental.sdk.SPGlobalManager;
import com.continental.sdk.base.BaseActivity;
import com.continental.sdk.base.BaseMvpActivity;
import com.continental.sdk.http.HttpException;
import com.continental.sdk.utils.ResUtils;
import com.continental.sdk.widget.NoScrollViewPager;
import com.google.android.material.tabs.TabLayout;
import org.greenrobot.eventbus.EventBus;
import java.util.List;
import java.util.Objects;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import cn.jpush.android.api.JPushInterface;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toAlertFragment(savedInstanceState);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        toAlertFragment(intent.getExtras());
    }

    private void toAlertFragment(Bundle bundle){
        if(bundle != null) {
            String target = bundle.getString("TARGET");
            LogUtils.json(target);
            if ("AlertFragment".equals(target)) {
                Objects.requireNonNull(mTabLayout.getTabAt(0)).select();
                ((HomeFragment)mPagerAdapter.getItem(0)).selectPage(2);
                EventBus.getDefault().post(new EventAlert());
            } else if ("BacklogFragment".equals(target)) {
                Objects.requireNonNull(mTabLayout.getTabAt(0)).select();
                ((HomeFragment)mPagerAdapter.getItem(0)).selectPage(0);
                EventBus.getDefault().post(new EventBacklog());
            }
        }
    }

    @Override
    public void onAuthenticationFailed(HttpException e) {
        SPGlobalManager.logout(LoginTMSActivity.class);
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


//        AndPermission.with(this).runtime()
//                .permission(Manifest.permission.POST_NOTIFICATIONS).onGranted {
//        }.onDenied {
//        }.start()

        PermissionUtils.permission(Manifest.permission.POST_NOTIFICATIONS)
                .callback(new PermissionUtils.FullCallback() {

                    @Override
                    public void onGranted(List<String> permissionsGranted) {
                        LogUtils.json(permissionsGranted);
                    }

                    @Override
                    public void onDenied(List<String> permissionsDeniedForever, List<String> permissionsDenied) {
                        LogUtils.json(permissionsDeniedForever);
                        LogUtils.json(permissionsDenied);
                    }
                }).request();
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.i(JPushInterface.getRegistrationID(this));
        mPresenter.jpRegisterId(this);
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
        new Handler().postDelayed(() -> Objects.requireNonNull(mTabLayout.getTabAt(2)).select(),1000);
    }

    @Override
    public void jpRegisterIdSuc(Object it) {
        LogUtils.json(it);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FragmentFactory.mFragments.clear();
    }
}