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

    Drawable drawable1 = null;
    Drawable drawable2 = null;
    boolean isChanged = false;
    OnClickCallback<View> mOnClickCallback = null;

    public SPToggleImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.SPToggleImageView, defStyleAttr, 0);

        drawable1 = attributes.getDrawable(R.styleable.SPToggleImageView_spsdk_toggle_drawable1);
        drawable2 = attributes.getDrawable(R.styleable.SPToggleImageView_spsdk_toggle_drawable2);
        attributes.recycle();

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView iv = (ImageView) v;
                if (isChanged) {
                    iv.setImageDrawable(drawable1);
                } else {
                    iv.setImageDrawable(drawable2);
                }
                isChanged = !isChanged;

                if (mOnClickCallback != null) {
                    mOnClickCallback.onClick(v);
                }
            }
        });
    }

    public void setOnClickCallback(OnClickCallback<View> onClickCallback) {
        mOnClickCallback = onClickCallback;
    }



}
