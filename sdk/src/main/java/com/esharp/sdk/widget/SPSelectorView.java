package com.esharp.sdk.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.esharp.sdk.R;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

/**
 * 功能描述：部门员工选择器
 *
 * @author (作者) someone
 * 创建时间： 2021/9/11
 */
public class SPSelectorView extends RelativeLayout {
    private RelativeLayout item;
    private TextView content;
    private AppCompatImageView right_image;

    public SPSelectorView(@NonNull Context context) {
        this(context, null);
    }

    public SPSelectorView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SPSelectorView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.spsdk_view_selector, this, true);
        item = findViewById(R.id.item);
        content = findViewById(R.id.content);
        right_image = findViewById(R.id.right_image);

//        int tintColor = SPGlobalManager.getTint();
//        input_title.setTextColor(tintColor);
//        input_content.setTextColor(tintColor);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.SPSelectorView, defStyleAttr, 0);
        int type = attributes.getInt(R.styleable.SPSelectorView_spsdk_selector_type, 0);
        content.setText(attributes.getText(R.styleable.SPSelectorView_spsdk_selector_content));

        if (type == 0) {
            right_image.setVisibility(View.VISIBLE);
            right_image.setImageResource(attributes.getResourceId(R.styleable.SPSelectorView_spsdk_selector_icon, 0));
        } else {
            right_image.setVisibility(View.GONE);
        }

        attributes.recycle();
    }

    public SPSelectorView setContent(String it) {
        content.setText(it);
        return this;
    }

    public String getContent() {
        return content.getText().toString();
    }

    public SPSelectorView setRightIcon(@DrawableRes int res) {
        right_image.setImageResource(res);
        return this;
    }

    public void setRightVisibility(int visibility) {
        right_image.setVisibility(visibility);
    }

    public void setRightClick(OnClickListener onClickListener) {
        right_image.setOnClickListener(onClickListener);
    }

    public void setOnItemClick(OnClickListener onClickListener) {
        item.setOnClickListener(onClickListener);
    }

}
