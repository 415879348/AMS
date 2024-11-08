package com.continental.sdk.rxjava;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.CompletableTransformer;
import io.reactivex.rxjava3.core.MaybeTransformer;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.core.SingleTransformer;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * 功能描述：Rxjava线程切换工具类
 *
 * @author (作者) someone
 * 创建时间： 2021/7/10
 */
public final class SchedulerUtils {

    public static <T> ObservableTransformer<T, T> io_main_detach() {
        return upstream -> upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onTerminateDetach();//取消订阅时清除上游对下游的引用
    }

    public static <T> SingleTransformer<T, T> io_main_single() {
        return upstream -> upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> MaybeTransformer<T, T> io_main_maybe() {
        return upstream -> upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onTerminateDetach();
    }

    public static CompletableTransformer io_main_complete() {
        return upstream -> upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> ObservableTransformer<T, T> cmp_main() {
        return upstream -> upstream
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .onTerminateDetach();//取消订阅时清除上游对下游的引用
    }

}
