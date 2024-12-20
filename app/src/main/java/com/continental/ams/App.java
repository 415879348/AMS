package com.continental.ams;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Build;
import android.util.DisplayMetrics;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.continental.ams.notify.NotificationChannels;
import com.continental.sdk.SPGlobalManager;
import com.continental.sdk.SPSdkUtil;
import com.continental.sdk.bean.request.JPRegisterVo;
import com.continental.sdk.http.HttpFunction;
import com.continental.sdk.http.HttpService;
import com.continental.sdk.rxjava.SchedulerUtils;
import com.continental.sdk.utils.FontUtil;
import com.continental.sdk.utils.LocalUtils;
import com.continental.sdk.utils.ResUtils;
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
//        registerReceiver(receiver, new IntentFilter("com.jiguang.demo.register"));

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

//    private final BroadcastReceiver receiver =  new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            LogUtils.i("receiver");
//            JPRegisterVo vo = new JPRegisterVo();
//            vo.setRegisterId(JPushInterface.getRegistrationID(context));
//            HttpService.get().jpRegisterId(vo)
//                    .map(new HttpFunction<>())
//                    .compose(SchedulerUtils.io_main_single())
//                    .subscribe(it -> {
//                        LogUtils.i("JP success");
//                        ToastUtils.showShort("JP success");
//                    });
//        }
//    };

}
