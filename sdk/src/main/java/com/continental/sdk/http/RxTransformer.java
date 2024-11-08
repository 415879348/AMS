package com.continental.sdk.http;

import com.continental.sdk.Constant;
import com.continental.sdk.bean.response.HttpResult;
import com.continental.sdk.bean.response.Optional;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleTransformer;
import io.reactivex.rxjava3.functions.Function;

public class RxTransformer {
 
    public static <T> SingleTransformer<HttpResult<T>, Optional<T>> handle_result() {

        return upstream -> upstream.flatMap(new Function<HttpResult<T>, Single<Optional<T>>>() {
            @Override
            public Single<Optional<T>> apply(HttpResult<T> result) throws Throwable {
                if (Constant.RESPONSE_CODE_SUCCESS == result.getResultCode()) {
                    // result.transform() 就是将返回结果进行包装
                    return createHttpData(result.transform());
                } else {
                    // 发送请求失败的信息
                    throw new HttpException(result.getResultCode(),result.getMessage());
                }
            }
        });
    }
 
    public static <T> Single<Optional<T>> createHttpData(Optional<T> t) {
        return Single.just(t);
    }
 
}

