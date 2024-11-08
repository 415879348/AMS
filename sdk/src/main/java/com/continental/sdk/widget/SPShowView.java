package com.continental.sdk.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.continental.sdk.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 功能描述：
 *
 * @author (作者) someone
 * 创建时间： 2021/9/11
 */
public class SPShowView extends RelativeLayout {

    private ImageView icon, icon2;
    private TextView title, detail;

    public SPShowView(@NonNull Context context) {
        this(context, null);
    }

    public SPShowView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SPShowView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.spsdk_view_show, this, true);
        icon = findViewById(R.id.iv_icon);
        icon2 = findViewById(R.id.icon2);
        title = findViewById(R.id.title);
        detail = findViewById(R.id.tv_detail);

//        int tintColor = SPGlobalManager.getTint();
//        input_title.setTextColor(tintColor);
//        input_content.setTextColor(tintColor);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.SPShowView, defStyleAttr, 0);
        icon.setImageDrawable(attributes.getDrawable(R.styleable.SPShowView_spsdk_show_icon));
        Drawable drawable2 = attributes.getDrawable(R.styleable.SPShowView_spsdk_show_icon2);
        if (drawable2 != null) {
            icon2.setImageDrawable(drawable2);
        }
        title.setText(attributes.getText(R.styleable.SPShowView_spsdk_show_title));

        CharSequence detail_text = attributes.getText(R.styleable.SPShowView_spsdk_show_detail);

        if (! TextUtils.isEmpty(detail_text)) {
            detail.setText(attributes.getText(R.styleable.SPShowView_spsdk_show_detail));
        }

        attributes.recycle();
    }

    public SPShowView setTitle(String t) {
        title.setText(t);
        return this;
    }

    public SPShowView setDetail(String hint) {
        detail.setText(hint);
        return this;
    }

}
