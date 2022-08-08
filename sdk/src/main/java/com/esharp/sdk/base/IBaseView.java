package com.esharp.sdk.base;

import org.jetbrains.annotations.Nullable;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * 功能描述：MVP View层基础接口
 *
 * @author (作者) someone
 * 创建时间： 2021/7/9
 */
public interface IBaseView {

    void showProgress(@StringRes int resId);

    void showProgress(@Nullable String msg);

    void dismissProgress();

    void showToast(@Nullable String msg);

    void showToast(@StringRes int msg);

    void addStream(@NonNull Disposable disposable);

    void removeStream(@Nullable Disposable disposable);
}
