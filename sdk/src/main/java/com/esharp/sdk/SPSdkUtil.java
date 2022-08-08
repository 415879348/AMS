package com.esharp.sdk;

import android.content.Context;

import com.esharp.sdk.bean.response.TokenVo;

/**
 * 功能描述：
 *
 * @author (作者) someone
 * 创建时间： 2021/8/14
 */
public class SPSdkUtil {
    private Context context;
    private TokenVo token;
    private SPConfig mSPConfig;

    public static void init(Context context) {
        SPGlobalManager.init(context);
    }

    private SPSdkUtil() {

    }

    public static SPSdkUtil getInstance(Context context) {
        SPSdkUtil spSdkUtil = new SPSdkUtil();
        spSdkUtil.context = context;
        return spSdkUtil;
    }

    public SPSdkUtil setToken(TokenVo token) {
        this.token = token;
        return this;
    }

    public SPSdkUtil setConfig(SPConfig SPConfig) {
        this.mSPConfig = SPConfig;
        return this;
    }

    public void start() {
        if (token == null) {
            throw new RuntimeException("token must not null");
        }
        if (mSPConfig == null) {
            mSPConfig = new SPConfig();
        }
        SPGlobalManager.start(context, token, mSPConfig);
    }

}
