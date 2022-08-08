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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 功能描述：
 *
 * @author (作者) someone
 * 创建时间： 2021/9/11
 */
public class SPNoteView extends LinearLayout {
    private TextView warning_sign;
    private TextView input_title;
    private EditText input_content;

    public SPNoteView(@NonNull Context context) {
        this(context, null);
    }

    public SPNoteView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SPNoteView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.spsdk_view_note, this, true);
        warning_sign = findViewById(R.id.warning_sign);
        input_title = findViewById(R.id.title);
        input_content = findViewById(R.id.content);
        setOrientation(LinearLayout.HORIZONTAL);
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.SPNoteView, defStyleAttr, 0);
        int warning_sign_type = attributes.getInt(R.styleable.SPNoteView_spsdk_note_warning_sign, 0);
        input_title.setText(attributes.getText(R.styleable.SPNoteView_spsdk_note_title)+":");
        input_content.setHint(attributes.getText(R.styleable.SPNoteView_spsdk_note_hint));
        input_content.setText(attributes.getText(R.styleable.SPNoteView_spsdk_note_content));


        if (warning_sign_type == 0) {
            warning_sign.setVisibility(View.GONE);
        } else {
            warning_sign.setVisibility(View.VISIBLE);
        }
        attributes.recycle();
    }

    public SPNoteView setTitle(String title) {
        input_title.setText(title+":");
        return this;
    }
    public SPNoteView setContent(String content) {
        input_content.setText(content);
        return this;
    }

    public String getContent() {
        return input_content.getText().toString();
//        if (TextUtils.isEmpty(input_content.getText().toString())) {
//            return "";
//        } else {
//            return input_content.getText().toString();
//        }
    }

}
