package com.esharp.ams.factory;

import android.util.SparseArray;

import com.esharp.ams.ui.fragment.BacklogFragment;
import com.esharp.ams.ui.fragment.DoneFragment;
import com.esharp.sdk.base.BaseFragment;

public class HomeFragmentFactory {

    private static SparseArray<BaseFragment> mFragments = new SparseArray<>();

    public static BaseFragment createFragment(int position){

        BaseFragment fragment = mFragments.get(position);

        if (fragment == null) {

            switch (position) {
                case 0:
                    fragment = new BacklogFragment();
                    break;
                case 1:
                    fragment = new DoneFragment();
                    break;
            }

            if (fragment != null) {
                mFragments.put(position, fragment);
            }
        }

        return fragment;
    }

}