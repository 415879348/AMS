package com.esharp.sdk.widget;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

import com.esharp.sdk.R;
import com.esharp.sdk.base.BaseActivity;

/**
 *@author Alex
 *2020-12-07
 */
public abstract class MyPopupWindowBuilder {

    protected BaseActivity mActivity = null;

    protected MyPopupWindow mPopupWindow = null;

    public MyPopupWindowBuilder(BaseActivity activity) {
        mActivity = activity;
        init();
    }

    protected View mView = null;

    private void init() {

        if (getType() == MyPopupWindow.Type.Vertical) {
            mView = LayoutInflater.from(mActivity).inflate(R.layout.spsdk_popup_window_vertical_frame, null);
        } else {
            mView = LayoutInflater.from(mActivity).inflate(R.layout.spsdk_popup_window_horizontal_frame, null);
        }

        View list = provideLayout();
        MyNestedScrollView2 scrollView = mView.findViewById(R.id.scrollView);
        scrollView.addView(list, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));
        scrollView.setInterceptTouchEvent(interceptScrollView());

//        if (getType() == MyPopupWindow.Type.Vertical) {
//            mView = LayoutInflater.from(mActivity).inflate(R.layout.spsdk_popup_window_vertical_frame, null);
//            View cornerBar = mView.findViewById(R.id.cornerBar);
//
//            if (shouldShowCornerBar()) {
//                cornerBar.setVisibility(View.VISIBLE);
//            } else {
//                cornerBar.setVisibility(View.GONE);
//            }
//
//            View pigHeadView = getPinHeadView();
//
//            LinearLayout contentList = mView.findViewById(R.id.contentList);
//            FrameLayout contentHolder = mView.findViewById(R.id.contentHolder);
//            FrameLayout scrollView = mView.findViewById(R.id.scrollView);
//
//            if (pigHeadView != null) {
//                contentList.addView(pigHeadView, 0, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
//            }
//            if (shouldContentViewInScrollView()) { // default true
//                if (shouldHeightMatchParent()) {
//                    contentHolder.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));// 在父控件中的属性
//                    contentList.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)); // 在父控件中的属性
//                    contentList.addView(provideLayout(), new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
//                } else {
//                    scrollView.addView(provideLayout(), new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));
//                }
//            } else {
//                scrollView.setVisibility(View.GONE);
//                contentList.addView(provideLayout(), new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
//            }
//        } else {
//            mView = LayoutInflater.from(mActivity).inflate(R.layout.spsdk_popup_window_horizontal_frame, null);
//            View cornerBar = mView.findViewById(R.id.cornerBar);
//
//            if (shouldShowCornerBar()) {
//                cornerBar.setVisibility(View.VISIBLE);
//            } else {
//                cornerBar.setVisibility(View.GONE);
//            }
//
//            LinearLayout contentList = mView.findViewById(R.id.contentList);
//            FrameLayout contentHolder = mView.findViewById(R.id.contentHolder);
//            FrameLayout scrollView = mView.findViewById(R.id.scrollView);
//
//            scrollView.addView(provideLayout(), new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));
//        }
//        mView.scrollView.setInterceptTouchEvent(interceptScrollView())
//        if (provideOnDismissListener() != null) {
//            mPopupWindow?.setOnDismissListener(provideOnDismissListener())
//        }
    }

    private View provideLayout() {
        return LayoutInflater.from(mActivity).inflate(provideLayoutID(), null);
    }

    protected abstract int provideLayoutID();

    protected PopupWindow.OnDismissListener provideOnDismissListener() {
        return null;
    }

    public abstract void initView();

    public void setPopupWindow(MyPopupWindow p) {
        mPopupWindow = p;
    }

    public View getView() {
        return mView;
    }

    public View getDecorView() {
        return mActivity.getWindow().getDecorView();
    }

    public View getPinHeadView() {
        return null;
    }

    public MyPopupWindow.Type getType() {
        return MyPopupWindow.Type.Vertical;
    }

    protected Boolean interceptScrollView() {
        return true;
    }

    protected Boolean shouldShowCornerBar() {
        return true;
    }

    protected Boolean shouldContentViewInScrollView() {
        return true;
    }

    protected Boolean shouldHeightMatchParent() {
        return false;
    }

    protected Boolean isCancelWhenClickOutSide() {
        return true;
    }
}