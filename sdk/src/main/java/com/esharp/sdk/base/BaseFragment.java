package com.esharp.sdk.base;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.esharp.sdk.http.HttpException;
import com.esharp.sdk.utils.FontUtil;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * 功能描述：Fragment基类
 *
 * @author (作者) someone
 * 创建时间： 2021/7/9
 */
public abstract class BaseFragment<H extends IHostView> extends AppCompatDialogFragment implements IBaseView {

    protected Context mContext;
    protected H mHostView;
    private CompositeDisposable disposable;
    private View rootView;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
        this.mHostView = (H) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        FontUtil.initFontScale(getActivity());
        if (rootView != null) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) parent.removeView(rootView);
        } else {
            rootView = inflater.inflate(layout(), container, false);
        }
        init(rootView);
        return rootView;
    }


    protected abstract @LayoutRes
    int layout();

    protected abstract void init(View view);

    protected CompositeDisposable getDisposable() {
        if (disposable == null) disposable = new CompositeDisposable();
        return disposable;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getDisposable().clear();
    }

    @Override
    public void showProgress(int resId) {
        mHostView.showProgress(resId);
    }

    @Override
    public void showProgress(@Nullable String msg) {
        mHostView.showProgress(msg);
    }

    @Override
    public void dismissProgress() {
        mHostView.dismissProgress();
    }

    @Override
    public void onAuthenticationFailed(HttpException e) {
        mHostView.onAuthenticationFailed(e);
    }

    @Override
    public void showToast(@Nullable String msg) {
        mHostView.showToast(msg);
    }

    @Override
    public void showToast(int msg) {
        mHostView.showToast(msg);
    }

    @Override
    public void addStream(@NonNull Disposable disposable) {
        getDisposable().add(disposable);
    }

    @Override
    public void removeStream(@Nullable Disposable disposable) {
        if (disposable == null) return;
        getDisposable().remove(disposable);
    }
}
