package com.continental.sdk.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.continental.sdk.R;
import com.continental.sdk.utils.ResUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 功能描述：
 *
 * @author (作者) someone
 * 创建时间： 2021/9/11
 */
public class SPCardEditView extends LinearLayout {

    private EditText et_edit;

    public SPCardEditView(@NonNull Context context) {
        this(context, null);
    }

    public SPCardEditView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SPCardEditView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.spsdk_view_card, this, true);
        et_edit = findViewById(R.id.et_edit);
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.SPCardEditView, defStyleAttr, 0);
        if (attributes.getText(R.styleable.SPCardEditView_spsdk_card_edit_hint) != null) {
            et_edit.setHint(attributes.getText(R.styleable.SPCardEditView_spsdk_card_edit_hint));
        }
        if (attributes.getText(R.styleable.SPCardEditView_spsdk_card_edit_content) != null) {
            et_edit.setText(attributes.getText(R.styleable.SPCardEditView_spsdk_card_edit_content));
        }

        String maxLengthTip = "";
        if (attributes.getText(R.styleable.SPCardEditView_spsdk_card_edit_max_length_tip) != null) {
            maxLengthTip = (String) attributes.getText(R.styleable.SPCardEditView_spsdk_card_edit_max_length_tip);
        }

        int maxLength = attributes.getInt(R.styleable.SPCardEditView_spsdk_card_edit_max_length, -1);

        int inputType = attributes.getInt(R.styleable.SPCardEditView_spsdk_card_edit_content_type, 0);
        if (inputType != 0) {
            et_edit.setInputType(inputType);
            if (maxLength != -1) {
                et_edit.setFilters(new InputFilter[] {new MyLengthFilter(maxLength, maxLengthTip)});
            }
        } else {
            InputFilter filter = (source, start, end, dest, dstart, dend) -> {
//              String speChat = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
                String speChat = "[`~!@ $%^&*()+=|{}':;',\\[\\] <>/?~！@ ￥%……&*（）——+|{}【】‘；：”“’。，、？]";
                Pattern pattern = Pattern.compile(speChat);
                Matcher matcher = pattern.matcher(source.toString());
                if (matcher.find()) {
                    return "";
                } else {
                    return null;
                }
            };
            if (maxLength != -1) {
                et_edit.setFilters(new InputFilter[] {new MyLengthFilter(maxLength, maxLengthTip), filter});
            }
        }

    }

    private static class MyLengthFilter implements InputFilter {
        int MAX_EN;
        String tip;
        public MyLengthFilter(int mAX_EN, String tip) {
            super();
            MAX_EN = mAX_EN;
            this.tip = tip;
        }

        @Override
        public CharSequence filter(CharSequence source, int start, int end,
                                   Spanned dest, int dstart, int dend) {
            LogUtils.i(source, start, end, dest, dstart, dend);

            int destCount = dest.toString().length();
            int sourceCount = source.toString().length();
            if (destCount + sourceCount > MAX_EN) {
                ToastUtils.showShort(String.format(tip, MAX_EN));
                return "";
            } else {
                return source;
            }
        }

    }

    public SPCardEditView setHint(String it) {
        et_edit.setHint(it);
        return this;
    }

    public SPCardEditView setInputType(int type) {
        et_edit.setInputType(type);
        return this;
    }

    public SPCardEditView setContent(String it) {
        et_edit.setText(it);
        return this;
    }

    public String getContent() {
        return et_edit.getText().toString();
    }

    public SPCardEditView setEnable(boolean it) {
        setEnabled(it);
        et_edit.setEnabled(it);
        return this;
    }

}
