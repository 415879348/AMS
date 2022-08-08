package com.esharp.sdk.base;

import com.esharp.sdk.http.HttpException;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * 功能描述：
 *
 * @author (作者) someone
 * 创建时间： 2021/7/10
 */
public class BaseObserver<T> implements Observer<T>, MaybeObserver<T>, SingleObserver<T>, CompletableObserver {

    private final IBaseView mView;
    private final ICallback<T> callback;
    private final ICallback<Throwable> error;
    private Disposable disposable;

    public BaseObserver(IBaseView mView) {
        this(mView, null);
    }

    public BaseObserver(IBaseView mView, ICallback<T> callback) {
        this(mView, callback, null);
    }

    public BaseObserver(IBaseView mView, ICallback<T> callback, ICallback<Throwable> error) {
        this.mView = mView;
        this.callback = callback;
        this.error = error;
    }

    @Override
    public void onSuccess(@NonNull T t) {
        if (mView != null) {
            mView.removeStream(disposable);
        }
        onNext(t);
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        if (mView != null) {
            mView.addStream(disposable = d);
        }
    }

    @Override
    public void onNext(@NonNull T t) {
        if (callback != null) callback.exec(t);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        if (error != null) error.exec(e);
        if (e instanceof HttpException) {
            if (mView != null) {
                mView.showToast(e.getMessage());
            }
        }
        if (mView != null) {
            mView.removeStream(disposable);
        }
    }

    @Override
    public void onComplete() {
        if (mView != null) {
            mView.removeStream(disposable);
        }
    }

}
