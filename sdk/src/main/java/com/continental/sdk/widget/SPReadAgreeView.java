package com.continental.sdk.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.continental.sdk.R;

/**
 * 功能描述：
 *
 * @author (作者) someone
 * 创建时间： 2021/8/6
 */
public class SPReadAgreeView extends FrameLayout {
    private final ImageView read_agree_check;
    private boolean isCheck;

    public SPReadAgreeView(Context context) {
        this(context, null);
    }

    public SPReadAgreeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SPReadAgreeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.spsdk_view_read_agree, this, true);
         read_agree_check = this.findViewById(R.id.read_agree_check);
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.SPReadAgreeView, defStyleAttr, 0);
        isCheck = attributes.getBoolean(R.styleable.SPReadAgreeView_spsdk_agreeCheck, false);
        ((TextView) findViewById(R.id.read_agree_hint)).setText(String.format("             %s", attributes.getText(R.styleable.SPReadAgreeView_spsdk_agreeHint)));
        attributes.recycle();
        read_agree_check.setSelected(isCheck);
        setOnClickListener(v -> {
            isCheck = !isCheck;
            read_agree_check.setSelected(isCheck);
        });
    }

    public boolean isCheck() {
        return isCheck;
    }
}
