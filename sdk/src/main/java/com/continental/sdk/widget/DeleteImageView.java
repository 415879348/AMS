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
public class DeleteImageView extends RelativeLayout {
    private ImageView iv_delete;
    private RadiusImageView iv_picture;

    public DeleteImageView(@NonNull Context context) {
        this(context, null);
    }

    public DeleteImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DeleteImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.spsdk_view_delete_image, this, true);
        iv_delete = findViewById(R.id.iv_delete);
        iv_picture = findViewById(R.id.iv_picture);
        iv_delete.setOnClickListener(v -> ((Activity) context).finish());
    }

    public RadiusImageView getImageView() {
        return iv_picture;
    }

    public DeleteImageView setDeleteIcon(@DrawableRes int res) {
        iv_delete.setImageResource(res);
        return this;
    }

    public void setDeleteTag(String tag) {
        iv_delete.setTag(tag);
    }

    public String getDeleteTag() {
        return (String) iv_delete.getTag();
    }

    public void setDeleteClick(OnClickListener onClickListener) {
        iv_delete.setOnClickListener(onClickListener);
    }

}
