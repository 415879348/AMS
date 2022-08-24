package com.esharp.ams.adapter;

import com.esharp.ams.factory.HomeFragmentFactory;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {

    public HomeFragmentPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return HomeFragmentFactory.createFragment(position);
    }

    @Override
    public int getCount() {
        return 3;
    }
}
