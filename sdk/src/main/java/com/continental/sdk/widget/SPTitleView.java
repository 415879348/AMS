package com.continental.sdk.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.continental.sdk.R;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.widget.AppCompatImageView;

/**
 * 功能描述：
 *
 * @author (作者) someone
 * 创建时间： 2021/9/11
 */
public class SPTitleView extends RelativeLayout {
    private ImageView iv_back;
    private TextView tv_back;
    private AppCompatImageView title_right;
    private TextView title_text, title_action;
    private int type;

    public SPTitleView(@NonNull Context context) {
        this(context, null);
    }

    public SPTitleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SPTitleView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.spsdk_view_title, this, true);
        iv_back = findViewById(R.id.iv_back);
        tv_back = findViewById(R.id.tv_back);
        title_right = findViewById(R.id.title_right);
        title_text = findViewById(R.id.title_text);
        title_action = findViewById(R.id.title_action);
        iv_back.setOnClickListener(v -> ((Activity) context).finish());
        tv_back.setOnClickListener(v -> ((Activity) context).finish());

//        int tintColor = SPGlobalManager.getTint();
//        tv_back.setTextColor(tintColor);
//        title_right.setColorFilter(tintColor);
//        title_text.setTextColor(tintColor);
//        title_action.setTextColor(tintColor);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.SPTitleView, defStyleAttr, 0);
        type = attributes.getInt(R.styleable.SPTitleView_spsdk_title_type, 0);
        title_text.setText(attributes.getText(R.styleable.SPTitleView_spsdk_title));
        if (type == 0) {
            title_action.setVisibility(View.VISIBLE);
            title_action.setText(attributes.getText(R.styleable.SPTitleView_spsdk_title_action));
        } else {
            title_right.setVisibility(View.VISIBLE);
            title_right.setImageResource(attributes.getResourceId(R.styleable.SPTitleView_spsdk_title_icon, 0));
        }
        attributes.recycle();
    }

    public SPTitleView setTitle(String title) {
        title_text.setText(title);
        return this;
    }

    public SPTitleView setBackClick(OnClickListener onClickListener) {
        iv_back.setOnClickListener(onClickListener);
        tv_back.setOnClickListener(onClickListener);
        return this;
    }

    public SPTitleView setRightIcon(@DrawableRes int res) {
        title_right.setImageResource(res);
        return this;
    }

    public SPTitleView setRightText(@StringRes int res) {
        title_action.setText(res);
        return this;
    }

    public void setRightVisibility(int visibility) {
        title_action.setVisibility(visibility);
    }

    public void setRightClick(OnClickListener onClickListener) {
        if (type == 1) {
            title_right.setOnClickListener(onClickListener);
        } else {
            title_action.setOnClickListener(onClickListener);
        }
    }

}
