package com.continental.sdk.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;

/**
 *@author Alex
 *2021-05-12
 */
public class MyNestedScrollView2 extends NestedScrollView {

    private Boolean shouldInterceptTouchEvent = true;

    public MyNestedScrollView2(@NonNull Context context) {
        super(context);
    }

    public MyNestedScrollView2(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyNestedScrollView2(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (shouldInterceptTouchEvent) {
            return super.onInterceptTouchEvent(ev);
        } else {
            return false;
        }
    }

    public void setInterceptTouchEvent(Boolean b){
        shouldInterceptTouchEvent = b;
    }

}