package com.esharp.sdk.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
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
public class SPShowTextView extends LinearLayout {

    private TextView title, detail;

    public SPShowTextView(@NonNull Context context) {
        this(context, null);
    }

    public SPShowTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SPShowTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.spsdk_view_show_text, this, true);
        title = findViewById(R.id.tv_title);
        detail = findViewById(R.id.tv_detail);

//        int tintColor = SPGlobalManager.getTint();
//        input_title.setTextColor(tintColor);
//        input_content.setTextColor(tintColor);

        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.SPShowTextView, defStyleAttr, 0);
        title.setText(attributes.getText(R.styleable.SPShowTextView_spsdk_show_text_title));
        detail.setText(attributes.getText(R.styleable.SPShowTextView_spsdk_show_text_detail));

        attributes.recycle();
    }

    public SPShowTextView setTitle(String it) {
        title.setText(it);
        return this;
    }

    public SPShowTextView setTitleColour(int it) {
        title.setTextColor(it);
        return this;
    }

    public SPShowTextView setDetail(String it) {
        detail.setText(it);
        return this;
    }

    public SPShowTextView setDetail(float it) {
        detail.setText(it+"");
        return this;
    }

    public SPShowTextView setDetailColour(int it) {
        detail.setTextColor(it);
        return this;
    }

}
