package com.continental.ams.factory;

import android.util.SparseArray;

import com.continental.ams.ui.fragment.AssetsFragment;
import com.continental.ams.ui.fragment.HomeFragment;
import com.continental.ams.ui.fragment.ProfileFragment;
import com.continental.ams.ui.fragment.WorkOrderFragment;
import com.continental.sdk.base.BaseFragment;

/**
 * @author      someone
 * @date        2014年3月27日 下午9:04:13
 */
public class FragmentFactory {

    public static SparseArray<BaseFragment> mFragments = new SparseArray<>();

    public static BaseFragment createFragment(int position){

        BaseFragment fragment = mFragments.get(position);

        if (fragment == null) {

            switch (position) {
                case 0:
                    fragment = new HomeFragment();
                    break;
                case 1:
                    fragment = new AssetsFragment();
                    break;
                case 2:
                    fragment = new WorkOrderFragment();
                    break;
                case 3:
                    fragment = new ProfileFragment();
                    break;
            }

            if (fragment != null) {
                mFragments.put(position, fragment);
            }
        }

        return fragment;
    }

}