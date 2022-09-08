package com.esharp.ams;

import android.app.Application;
import android.os.Build;

import com.blankj.utilcode.util.LogUtils;
import com.esharp.ams.notify.NotificationChannels;
import com.esharp.sdk.SPGlobalManager;
import com.esharp.sdk.SPSdkUtil;
import com.esharp.sdk.utils.LocalUtils;
import com.esharp.sdk.utils.ResUtils;
import cn.jpush.android.api.JPushInterface;

/**
 * 功能描述：
 *
 * @author (作者) someone
 * 创建时间： 2021/8/14
 */
public class App extends Application {

    public static App mApp = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
//        UMConfigure.init(this, "611735131fee2e303c226b5f", null, UMConfigure.DEVICE_TYPE_PHONE, "");
//        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);
//        SPProfileMenus.setIProfileMenus(new ProfileMenus());

//        LogUtils.getConfig().setLogSwitch(!BuildConfig.DEBUG);
        SPSdkUtil.init(this); // 初始化参数设置

        if (SPGlobalManager.getLanguage() == null) {
            SPGlobalManager.setLanguage(LocalUtils.getSystemLocal(this));
        }

        LocalUtils.initLocal(this);
        ResUtils.init(this);

        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannels.getInstance().createAllNotificationChannels(this);
        }
    }

}
