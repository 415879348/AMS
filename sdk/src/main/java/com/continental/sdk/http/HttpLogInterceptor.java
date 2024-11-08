package com.continental.sdk.http;

import com.blankj.utilcode.util.LogUtils;
import com.continental.sdk.utils.StringUtil;

import java.io.IOException;
import java.util.Objects;

import androidx.annotation.NonNull;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 *@author someone
 *2018/10/30
 */
public class HttpLogInterceptor implements Interceptor {

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        long t1 = System.nanoTime();
        String b = "";
        if (request.body() != null && request.body() instanceof FormBody) {
            FormBody body = (FormBody) request.body();
            StringBuilder stringBuffer = new StringBuilder();

            for (int i = 0; i < Objects.requireNonNull(body).size(); i++) {
                stringBuffer.append("\n").append(body.encodedName(i)).append(" = ").append(body.encodedValue(i));
            }
            b = stringBuffer.toString();
        } else {
            b = "";
        }
        Response response = null;

        try {
            response = chain.proceed(request);
        } catch (IOException e) {
            throw e;
        }

        if (response.code() == 200) {
            ResponseBody responseBody = null;
            try {
                responseBody = response.peekBody(1024 * 1024);
                LogUtils.i(String.format("请求成功：%s -> %.1f ms", StringUtil.decodeString(response.request().url().toString()), (System.nanoTime() - t1) / (1000 * 1000f)));
                LogUtils.json(responseBody.string());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            LogUtils.e(String.format("请求失败 %d%n code-> \n%s%n header-> \n%s%n body -> %s%n", response.code(), StringUtil.decodeString(request.url().toString()), request.headers(), b));
        }

        return response;
    }

}