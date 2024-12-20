package com.continental.sdk.http;

import com.blankj.utilcode.util.LogUtils;
import com.continental.sdk.BuildConfig;
import com.continental.sdk.Constant;
import com.continental.sdk.SPGlobalManager;
import com.continental.sdk.bean.response.Token;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 功能描述：网络服务工具类
 *
 * @author (作者) someone
 * 创建时间： 2021/7/11
 */
public final class HttpService {
    private static volatile HttpService httpService;
    private final IApiService apiService;

    public static IApiService get() {
        return getInstance().apiService;
    }

    private HttpService() {
        apiService = new Retrofit.Builder()
                .baseUrl(IHttpURL.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(getHttpClient())
                .build()
                .create(IApiService.class);
    }

    private static HttpService getInstance() {
        if (httpService == null) {
            synchronized (HttpService.class) {
                if (httpService == null) {
                    httpService = new HttpService();
                }
            }
        }
        return httpService;
    }

    private OkHttpClient getHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
//        LogUtils.i(BuildConfig.DEBUG);

        return new OkHttpClient().newBuilder()
                .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.STRICT_HOSTNAME_VERIFIER)
                .addInterceptor(chain -> {
                    Token token = SPGlobalManager.getToken();

                    //        zh_HK,zh_CN,en_US
                    String languageCode = SPGlobalManager.getLanguage().getLocale().toString();
                    LogUtils.json(languageCode);
                    return chain.proceed(chain.request()
                            .newBuilder()
                            .addHeader(Constant.Authorization, token == null ? Constant.TOKEN_HEAD_BEARER : Constant.TOKEN_HEAD_BEARER + token.getToken())
                            .addHeader(Constant.Language, languageCode)//en/tc/sc
                            .build());
                })
//                .addInterceptor(new HttpLogInterceptor())
                .addInterceptor(interceptor)
                .build();
    }

}
