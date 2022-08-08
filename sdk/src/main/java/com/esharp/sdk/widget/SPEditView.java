package com.esharp.sdk.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.esharp.sdk.R;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

/**
 * 功能描述：
 *
 * @author (作者) someone
 * 创建时间： 2021/9/11
 */
public class SPEditView extends LinearLayout {
    private TextView warning_sign;
    private TextView input_title;
    private EditText input_content;
    private AppCompatImageView input_right_image;

    public SPEditView(@NonNull Context context) {
        this(context, null);
    }

    public SPEditView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SPEditView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.spsdk_view_input, this, true);
        warning_sign = findViewById(R.id.warning_sign);
        input_title = findViewById(R.id.title);
        input_content = findViewById(R.id.content);
        input_right_image = findViewById(R.id.right_image);

//        int tintColor = SPGlobalManager.getTint();
//        input_title.setTextColor(tintColor);
//        input_content.setTextColor(tintColor);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.SPInputView, defStyleAttr, 0);
        int type = attributes.getInt(R.styleable.SPInputView_spsdk_input_show_right_img, 0);
        int warning_sign_type = attributes.getInt(R.styleable.SPInputView_spsdk_input_warning_sign, 0);
        input_title.setText(attributes.getText(R.styleable.SPInputView_spsdk_input_title)+":");
        input_content.setHint(attributes.getText(R.styleable.SPInputView_spsdk_input_hint));
        input_content.setText(attributes.getText(R.styleable.SPInputView_spsdk_input_content));
        int inputType = attributes.getInt(R.styleable.SPInputView_spsdk_input_content_type, 0);

        if (inputType != 0) {
            input_content.setInputType(inputType);
        }

        if (warning_sign_type == 0) {
            warning_sign.setVisibility(View.INVISIBLE);
        } else {
            warning_sign.setVisibility(View.VISIBLE);
        }

        int ifShowTitle = attributes.getInt(R.styleable.SPInputView_spsdk_input_show_title, 1);
        if (ifShowTitle == 0) {
            warning_sign.setVisibility(View.GONE);
            input_title.setVisibility(View.GONE);
        } else {
            warning_sign.setVisibility(View.VISIBLE);
            input_title.setVisibility(View.VISIBLE);
        }


        if (type == 0) {
            input_right_image.setVisibility(View.GONE);
        } else {
            input_right_image.setVisibility(View.VISIBLE);
            input_right_image.setImageResource(attributes.getResourceId(R.styleable.SPInputView_spsdk_input_icon, 0));
        }

        int content_enabled = attributes.getInt(R.styleable.SPInputView_spsdk_input_content_enabled, 1);
        if (content_enabled == 1) {
            input_content.setEnabled(true);
        } else {
            input_content.setEnabled(false);
        }


        attributes.recycle();
    }

    public SPEditView setTitle(String title) {
        input_title.setText(title+":");
        return this;
    }

    public SPEditView setHint(String hint) {
        input_content.setHint(hint);
        return this;
    }

    public SPEditView setContent(String hint) {
        input_content.setText(hint);
        return this;
    }

    public String getContent() {
        return input_content.getText().toString();
    }

    public View getContentView() {
        return input_content;
    }

    public SPEditView setRightIcon(@DrawableRes int res) {
        input_right_image.setImageResource(res);
        return this;
    }

    public void setRightVisibility(int visibility) {
        input_right_image.setVisibility(visibility);
    }

    public void setRightClick(OnClickListener onClickListener) {
        input_right_image.setOnClickListener(onClickListener);
    }

}
