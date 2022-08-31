package com.esharp.sdk;

import android.content.Context;

/**
 * 功能描述：
 *
 * @author (作者) someone
 * 创建时间： 2021/8/14
 */
public class SPSdkUtil {
    private Context context;
    private SPConfig mSPConfig;
    private SPParams mSPParams;

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

    public SPSdkUtil setParams(SPParams spParams) {
        this.mSPParams = spParams;
        return this;
    }

    public SPSdkUtil setConfig(SPConfig spConfig) {
        this.mSPConfig = spConfig;
        return this;
    }

    public void start() {
        if (mSPConfig == null) {
            mSPConfig = new SPConfig();
        }
        SPGlobalManager.start(context, mSPParams, mSPConfig);
    }

}
