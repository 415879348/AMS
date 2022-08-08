package com.esharp.ams;

import android.app.Application;

import com.esharp.sdk.SPSdkUtil;
import com.esharp.sdk.utils.LocalUtils;
import com.esharp.sdk.utils.ResUtils;

/**
 * 功能描述：
 *
 * @author (作者) someone
 * 创建时间： 2021/8/14
 */
public class App extends Application {

    App mApp = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
//        UMConfigure.init(this, "611735131fee2e303c226b5f", null, UMConfigure.DEVICE_TYPE_PHONE, "");
//        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);
//        LogUtils.getConfig().setLogSwitch(!BuildConfig.DEBUG);
        SPSdkUtil.init(this);
        ResUtils.init(this);
        LocalUtils.initLocal(this);
//        SPProfileMenus.setIProfileMenus(new ProfileMenus());
    }
}
