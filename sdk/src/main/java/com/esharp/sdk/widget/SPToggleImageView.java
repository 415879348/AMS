package com.esharp.sdk.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.esharp.sdk.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SPToggleImageView extends androidx.appcompat.widget.AppCompatImageView {

    public static final int STATE1 = 1;
    public static final int STATE2 = 2;

    Drawable drawable1 = null;
    Drawable drawable2 = null;
    boolean isChanged = false;
    OnClickCallback<Boolean> mOnClickCallback = null;

    public SPToggleImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.SPToggleImageView, defStyleAttr, 0);
        drawable1 = attributes.getDrawable(R.styleable.SPToggleImageView_spsdk_toggle_drawable1);
        drawable2 = attributes.getDrawable(R.styleable.SPToggleImageView_spsdk_toggle_drawable2);
        setImageDrawable(drawable1);
        attributes.recycle();

        setOnClickListener(v -> {
            ImageView iv = (ImageView) v;
            if (isChanged) {
                iv.setImageDrawable(drawable1);
            } else {
                iv.setImageDrawable(drawable2);
            }
            isChanged = !isChanged;

            if (mOnClickCallback != null) {
                mOnClickCallback.onClick(isChanged);
            }
        });
    }

    public void setOnClickCallback(OnClickCallback<Boolean> onClickCallback) {
        mOnClickCallback = onClickCallback;
    }

    public void setState1() {
        isChanged = false;
        setImageDrawable(drawable1);
    }

    public void setState2() {
        isChanged = true;
        setImageDrawable(drawable2);
    }

    public int getCurrentState() {
        if (getDrawable() == drawable1) {
            return 1;
        } else {
            return 2;
        }
    }

}
