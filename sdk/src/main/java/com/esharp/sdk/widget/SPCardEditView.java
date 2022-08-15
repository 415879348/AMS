package com.esharp.sdk.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.esharp.sdk.R;
import com.esharp.sdk.utils.ResUtils;

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
    }

    public SPCardEditView setHint(String it) {
        et_edit.setHint(it);
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
