package com.esharp.sdk.rxjava;

import com.esharp.sdk.base.IBaseView;

import androidx.annotation.StringRes;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleOperator;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * 功能描述：自定义网络访问进度条显示操作符，可显示，隐藏进度条并处理异常
 *
 * @author (作者) someone
 * 创建时间： 2021/7/12
 */
public class ProgressOperator<T> implements SingleOperator<T, T> {

    private final IBaseView mView;
    @StringRes
    private final int resId;

    public ProgressOperator(IBaseView mView, int resId) {
        this.mView = mView;
        this.resId = resId;
    }

    @Override
    public @NonNull SingleObserver<? super T> apply(@NonNull SingleObserver<? super T> observer) throws Throwable {
        return new SingleObserver<T>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                if (mView != null) {
                    mView.showProgress(resId);
                }
                observer.onSubscribe(d);
            }

            @Override
            public void onSuccess(@NonNull T t) {
                if (mView != null) {
                    mView.dismissProgress();
                }
                observer.onSuccess(t);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (mView != null) {
                    mView.dismissProgress();
                }
                observer.onError(e);
            }
        };
    }
}
