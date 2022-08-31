package com.esharp.sdk.rxjava;

import com.esharp.sdk.Constant;
import com.esharp.sdk.SPGlobalManager;
import com.esharp.sdk.bean.response.HttpResult;
import com.esharp.sdk.http.HttpError;
import com.esharp.sdk.http.HttpException;
import com.google.gson.Gson;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleOperator;
import io.reactivex.rxjava3.disposables.Disposable;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * 功能描述：
 *
 * @author (作者) someone
 * 创建时间： 2021/11/23
 */
public class HttpResultOperator<T> implements SingleOperator<T, HttpResult<T>> {

    @Override
    public @NonNull SingleObserver<? super HttpResult<T>> apply(@NonNull SingleObserver<? super T> observer) throws Throwable {
        return new SingleObserver<HttpResult<T>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                observer.onSubscribe(d);
            }

            @Override
            public void onSuccess(@NonNull HttpResult<T> httpResult) {
//                LogUtils.json(httpResult);
                if (httpResult.getResultCode().equals(Constant.RESPONSE_CODE_SUCCESS)) {
//                    if (!TextUtils.isEmpty(httpResult.getResultMsg())) {
//                        ToastUtils.showShort(httpResult.getResultMsg());
//                    }
                    observer.onSuccess(httpResult.getData());
                } else {
                    observer.onError(new HttpException(httpResult.getResultCode(), httpResult.getResultMsg()));
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (e instanceof retrofit2.HttpException) {
                    retrofit2.HttpException exception = (retrofit2.HttpException) e;
                    Response<?> response = exception.response();
                    ResponseBody responseBody;
                    try {
                        if (response != null && (responseBody = response.errorBody()) != null) {
                            HttpError httpError = new Gson().fromJson(responseBody.string(), HttpError.class);
                            observer.onError(new HttpException("1", httpError.getError() + "\n\n" + httpError.getMessage()));
                            return;
                        }
                    } catch (Exception e1) {
                        observer.onError(e1);
                        return;
                    }
                }
                observer.onError(e);
            }
        };
    }

}
