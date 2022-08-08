package com.esharp.sdk.widget;

import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.esharp.sdk.R;
import com.esharp.sdk.utils.ActivityUtil;

/**
 *@author Alex
 *2020-12-07
 */
public class MyPopupWindow extends PopupWindow {

    public enum Type {
        Vertical,
        Horizontal
    }

    public MyPopupWindow(View contentView, int width, int height, MyPopupWindowBuilder builder, Type type) {
        super(contentView, width, height, true);

        if (builder.isCancelWhenClickOutSide()) {
            View bg = contentView.findViewById(R.id.backView);
            bg.setOnClickListener(v -> dismiss());
        }

        if (type == Type.Horizontal) {
            View cancel = contentView.findViewById(R.id.cancel);
            cancel.setOnClickListener(v -> dismiss());
        }
        builder.setPopupWindow(this);
        builder.initView();
    }

    public static void showWin(MyPopupWindowBuilder builder) {
        View customView = builder.getView();
        MyPopupWindow p = new MyPopupWindow(customView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, builder, builder.getType());
        p.setTouchable(true);
        p.setBackgroundDrawable(new BitmapDrawable());
        p.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (v != null && !(v instanceof EditText) && event != null && event.getAction() == MotionEvent.ACTION_UP) {
                    ActivityUtil.hideSoftInput(v);
                }
                return false;
            }
        });

        if (builder.getType() == Type.Vertical) {
            p.setAnimationStyle(R.style.popupWindowAnimStyle);
            p.showAtLocation(builder.getDecorView(), Gravity.BOTTOM, 0, 0);
        } else {
            p.setAnimationStyle(R.style.popupWindowAnimStyle2);
            p.showAtLocation(builder.getDecorView(), Gravity.LEFT, 0, 0);
        }

    }

}