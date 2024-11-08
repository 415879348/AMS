package com.continental.sdk.http;


import com.continental.sdk.Constant;
import com.continental.sdk.bean.response.HttpResult;

import io.reactivex.rxjava3.functions.Function;

/**
 * 功能描述：接口返回数据统一处理方法类
 *
 * @author (作者) someone
 * 创建时间： 2021/7/12
 */
public class HttpFunction<T> implements Function<HttpResult<T>, T> {

    @Override
    public T apply(HttpResult<T> result) throws Throwable {
        if (result.getResultCode().equals(Constant.RESPONSE_CODE_SUCCESS)) {
            return result.getData();
        }
        throw new HttpException(result.getResultCode(),result.getMessage());
    }
}
