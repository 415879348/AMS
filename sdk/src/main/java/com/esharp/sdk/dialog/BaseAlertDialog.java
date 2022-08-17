package com.esharp.sdk.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.esharp.sdk.R;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

/**
 * 功能描述：
 *
 * @author (作者) someone
 * 创建时间： 2021/7/20
 */
public abstract class BaseAlertDialog extends Dialog {

    public BaseAlertDialog(@NonNull Context context, @LayoutRes int layout) {
        super(context, R.style.spsdk_alert_dialog);
        this.setContentView(LayoutInflater.from(context).inflate(layout, null), new ViewGroup.LayoutParams(ScreenUtils.getScreenWidth() - SizeUtils.dp2px(30), ViewGroup.LayoutParams.WRAP_CONTENT));
        this.setCanceledOnTouchOutside(false);
    }

}
