package com.esharp.sdk.widget;

import android.content.Context;
import android.content.res.TypedArray;
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
public class SPTabView extends LinearLayout {
    private ImageView icon;
    private TextView title;

    public SPTabView(@NonNull Context context) {
        this(context, null);
    }

    public SPTabView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SPTabView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.spsdk_view_tab, this, true);
        icon = findViewById(R.id.iv_icon);
        title = findViewById(R.id.title);
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.SPTabView, defStyleAttr, 0);
        int type = attributes.getInt(R.styleable.SPTabView_spsdk_tab_orientation, 0);
        if (type == 0) {
           setOrientation(LinearLayout.HORIZONTAL);
        } else {
           setOrientation(LinearLayout.VERTICAL);
        }
        setGravity(Gravity.CENTER);
        icon.setImageDrawable(attributes.getDrawable(R.styleable.SPTabView_spsdk_tab_icon));
        title.setText(attributes.getText(R.styleable.SPTabView_spsdk_tab_title));
        attributes.recycle();
    }

    public SPTabView setTitle(String it) {
        title.setText(it);
        return this;
    }

    public SPTabView setDetail(String it) {
        title.setText(it);
        return this;
    }

}
