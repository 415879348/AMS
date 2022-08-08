package com.esharp.sdk.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.esharp.sdk.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 功能描述：
 *
 * @author (作者) someone
 * 创建时间： 2021/9/11
 */
public class SPIconTextView extends LinearLayout {
    private ImageView icon;
    private TextView title;

    public SPIconTextView(@NonNull Context context) {
        this(context, null);
    }

    public SPIconTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SPIconTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.spsdk_view_icon_text, this, true);
        icon = findViewById(R.id.iv_icon);
        title = findViewById(R.id.tv_title);
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.SPIconTextView, defStyleAttr, 0);
        icon.setImageDrawable(attributes.getDrawable(R.styleable.SPIconTextView_spsdk_icon));
        title.setText(attributes.getText(R.styleable.SPIconTextView_spsdk_text));
        attributes.recycle();
    }

    public SPIconTextView setIcon(Drawable it) {
        icon.setImageDrawable(it);
        return this;
    }

    public SPIconTextView setTitle(String it) {
        title.setText(it);
        return this;
    }

}
