package com.continental.ams.receiver;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.blankj.utilcode.util.LogUtils;
import com.continental.ams.App;
import com.continental.ams.notify.NotificationChannels;
import com.continental.ams.notify.NotificationSender;
import com.continental.ams.notify.NotificationUtil;
import com.continental.ams.ui.MainActivity;
import com.continental.sdk.SPGlobalManager;
import com.continental.sdk.bean.response.AssetAlertBean;
import com.continental.sdk.bean.response.NotificationVo;
import com.continental.sdk.bean.response.UserVo;
import com.google.gson.Gson;
import androidx.core.app.NotificationCompat;
import cn.jpush.android.api.CmdMessage;
import cn.jpush.android.api.CustomMessage;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.JPushMessage;
import cn.jpush.android.api.NotificationMessage;
import cn.jpush.android.service.JPushMessageReceiver;

/**
 *
 code     失败信息               注册失败
 1000     错误信息               自定义消息展示错误
 2003     not stop/stopped      isPushStopped 异步回调
 2004     connected/not connect getConnectionState 异步回调
 2005     对应 rid               getRegistrationID 异步回调
 2006     set success           onResume 设置回调
 2007     set success           onStop 设置回调
 2008     success               应用冷启动后，SDK 首次初始化成功的回调(只回调一次)
 10000    无                    厂商 token 注册回调，通过 extra 可获取对应 platform 和 token 信息
 */
public class PushMessageReceiver extends JPushMessageReceiver {
    private static final String TAG = "PushMessageReceiver";

    @Override
    public void onMessage(Context context, CustomMessage customMessage) {
        Log.e(TAG, "[onMessage] " + customMessage);
        AssetAlertBean assetAlertBean = new Gson().fromJson(customMessage.message, AssetAlertBean.class);
        LogUtils.json(assetAlertBean);

//        type=1时弹出
//        type=2和handlerId等于登录人id时弹
//        type=2和handlerId不等于登录人id时不做任何处理
        UserVo user = SPGlobalManager.getUserVo();
        if(assetAlertBean.getType() == 2
                && !assetAlertBean.getHandlerId().equals(user.getId())) {
            return;
        }
        NotificationSender.notify(context, assetAlertBean);
    }

    @Override
    public void onNotifyMessageOpened(Context context, NotificationMessage message) {
        Log.e(TAG, "[onNotifyMessageOpened] " + message);
        LogUtils.json(message);

        NotificationVo notificationVo = new Gson().fromJson(message.notificationExtras, NotificationVo.class);
        Bundle bundle = new Bundle();
//        type=1跳到現在警報 =2跳到待辦
        switch (notificationVo.getType()) {
            case 1:
                bundle.putString("TARGET", "AlertFragment");
            case 2:
                bundle.putString("TARGET", "BacklogFragment");
                break;
        }
        NotificationCompat.Builder builder = NotificationUtil.createBaseNotification(NotificationChannels.getInstance().NOTICE_ALERT);
        builder.setContentTitle(message.notificationTitle);
        builder.setSubText(notificationVo.getSendTime());
        builder.setContentText(message.notificationContent);
        Intent intent = new Intent(App.mApp, MainActivity.class);
        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);

//        PendingIntent pendingIntent = PendingIntent.getActivity(App.mApp, (int) ((Math.random() * 100000000)),
//                intent, Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ? PendingIntent.FLAG_IMMUTABLE : PendingIntent.FLAG_UPDATE_CURRENT);
//        int notificationID = (int) ((Math.random() * 100000000));
//        builder.setContentIntent(pendingIntent);
//        NotificationUtil.getNotificationManager().notify(notificationID, builder.build());

//        try {
//            //打开自定义的Activity
//            Intent i = new Intent(context, MainActivity.class);
//            Bundle bundle = new Bundle();
//            bundle.putString("TARGET", "AlertFragment");
//            bundle.putString(JPushInterface.EXTRA_NOTIFICATION_TITLE, message.notificationTitle);
//            bundle.putString(JPushInterface.EXTRA_ALERT, message.notificationContent);
//            i.putExtras(bundle);
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
//            context.startActivity(i);
//        } catch (Throwable throwable) {
//            LogUtils.json(throwable);
//        }
    }

    @Override
    public void onMultiActionClicked(Context context, Intent intent) {
        Log.e(TAG, "[onMultiActionClicked] 用户点击了通知栏按钮");
        String nActionExtra = intent.getExtras().getString(JPushInterface.EXTRA_NOTIFICATION_ACTION_EXTRA);

        //开发者根据不同 Action 携带的 extra 字段来分配不同的动作。
        if (nActionExtra == null) {
            Log.d(TAG, "ACTION_NOTIFICATION_CLICK_ACTION nActionExtra is null");
            return;
        }
        if (nActionExtra.equals("my_extra1")) {
            Log.e(TAG, "[onMultiActionClicked] 用户点击通知栏按钮一");
        } else if (nActionExtra.equals("my_extra2")) {
            Log.e(TAG, "[onMultiActionClicked] 用户点击通知栏按钮二");
        } else if (nActionExtra.equals("my_extra3")) {
            Log.e(TAG, "[onMultiActionClicked] 用户点击通知栏按钮三");
        } else {
            Log.e(TAG, "[onMultiActionClicked] 用户点击通知栏按钮未定义");
        }
    }

    @Override
    public void onNotifyMessageArrived(Context context, NotificationMessage message) {
        LogUtils.e(TAG, "[onNotifyMessageArrived] " + message);
        LogUtils.json(message);
    }

    @Override
    public void onNotifyMessageDismiss(Context context, NotificationMessage message) {
        Log.e(TAG, "[onNotifyMessageDismiss] " + message);
    }

    @Override
    public void onRegister(Context context, String registrationId) {
        Log.e(TAG, "[onRegister] " + registrationId);


//        if (TextUtils.isEmpty(SPGlobalManager.getToken().getToken())) {
//            JPRegisterVo vo = new JPRegisterVo();
//            vo.setRegisterId(registrationId);
//            HttpService.get().jpRegisterId(vo)
//                    .map(new HttpFunction<>())
//                    .compose(SchedulerUtils.io_main_single())
//                    .subscribe(it -> {
//                        ToastUtils.showShort("JP success");
//                    });
//            Intent intent = new Intent("com.jiguang.demo.register");
//            context.sendBroadcast(intent);
//        }

    }

    @Override
    public void onConnected(Context context, boolean isConnected) {
        Log.e(TAG, "[onConnected] " + isConnected);
    }

    @Override
    public void onCommandResult(Context context, CmdMessage cmdMessage) {
        Log.e(TAG, "[onCommandResult] " + cmdMessage);
        Log.e(TAG, "[onCommandResult] token" + SPGlobalManager.getToken().getToken());
//        if (TextUtils.isEmpty(SPGlobalManager.getToken().getToken())) {
//            Intent intent = new Intent("com.jiguang.demo.register");
//            context.sendBroadcast(intent);
//        }
    }

    @Override
    public void onTagOperatorResult(Context context, JPushMessage jPushMessage) {
        TagAliasOperatorHelper.getInstance().onTagOperatorResult(context,jPushMessage);
        super.onTagOperatorResult(context, jPushMessage);
    }

    @Override
    public void onCheckTagOperatorResult(Context context, JPushMessage jPushMessage) {
        Log.e(TAG, "[onCheckTagOperatorResult] " + jPushMessage);
        TagAliasOperatorHelper.getInstance().onCheckTagOperatorResult(context,jPushMessage);
        super.onCheckTagOperatorResult(context, jPushMessage);
    }

    @Override
    public void onAliasOperatorResult(Context context, JPushMessage jPushMessage) {
        Log.e(TAG, "[onAliasOperatorResult] " + jPushMessage);
        TagAliasOperatorHelper.getInstance().onAliasOperatorResult(context,jPushMessage);
        super.onAliasOperatorResult(context, jPushMessage);
    }

    @Override
    public void onMobileNumberOperatorResult(Context context, JPushMessage jPushMessage) {
        Log.e(TAG, "[onMobileNumberOperatorResult] " + jPushMessage);
        TagAliasOperatorHelper.getInstance().onMobileNumberOperatorResult(context,jPushMessage);
        super.onMobileNumberOperatorResult(context, jPushMessage);
    }

    @Override
    public void onNotificationSettingsCheck(Context context, boolean isOn, int source) {
        super.onNotificationSettingsCheck(context, isOn, source);
        Log.e(TAG, "[onNotificationSettingsCheck] isOn:" + isOn + ",source:" + source);
    }

}
