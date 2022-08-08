package com.esharp.sdk.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.ScreenUtils;
import com.esharp.sdk.R;

import androidx.annotation.NonNull;


/**
 * 功能描述：全局统一样式进度条
 *
 * @author (作者) someone
 * 创建时间： 2021/7/9
 */
public class ProgressDialog extends Dialog implements IBaseProgress {
    private final TextView loadingText;

    public ProgressDialog(@NonNull Context context) {
        super(context, R.style.Dialog);
        this.setContentView(LayoutInflater.from(context).inflate(R.layout.spsdk_dialog_progress, null), new ViewGroup.LayoutParams( ScreenUtils.getScreenWidth() * 3 / 5, -2));
        loadingText = findViewById(R.id.loading_text);
        this.setCanceledOnTouchOutside(false);
    }

    @Override
    public void setText(@NonNull CharSequence text) {
        loadingText.setText(text);
    }
}
