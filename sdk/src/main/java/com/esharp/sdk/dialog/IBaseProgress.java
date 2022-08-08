package com.esharp.sdk.dialog;


import androidx.annotation.NonNull;

/**
 * 功能描述：基类进度条接口
 *
 * @author (作者) someone
 * 创建时间： 2021/7/9
 */
public interface IBaseProgress {

    void show();

    boolean isShowing();

    void setText(@NonNull CharSequence var1);

    void dismiss();
}
