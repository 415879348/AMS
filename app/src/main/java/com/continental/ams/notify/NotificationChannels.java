package com.continental.ams.notify;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.RequiresApi;

public class NotificationChannels {

    private static NotificationChannels mInstance;

    public static NotificationChannels getInstance(){
        if(mInstance == null) {
            synchronized (NotificationChannels.class){
                if(mInstance == null){
                    mInstance = new NotificationChannels();
                }
            }
        }

        return mInstance;
    }

    public String NOTICE_ALERT = "notice_alert";

    public String NOTICE_SILENT = "notice_silent";

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createAllNotificationChannels(Context context) {
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationChannel silentChannel = new NotificationChannel(
                NOTICE_SILENT,
                NOTICE_SILENT,
                NotificationManager.IMPORTANCE_DEFAULT);
        silentChannel.canBypassDnd();
        silentChannel.enableLights(true);
        silentChannel.setSound(null,null);

        NotificationChannel alertChannel = new NotificationChannel(
                NOTICE_ALERT,
                NOTICE_ALERT,
                NotificationManager.IMPORTANCE_HIGH);
        initChannel(alertChannel);

//        val newMessageChannel = NotificationChannel(
//                NEW_MESSAGE,
//                NEW_MESSAGE,
//                NotificationManager.IMPORTANCE_HIGH)
//        initChannel(newMessageChannel)
//
//        val callInChannel = NotificationChannel(
//                CALL_IN,
//                CALL_IN,
//                NotificationManager.IMPORTANCE_HIGH)
//        initChannel(callInChannel)
//
//        val noticeMissChannel = NotificationChannel(
//                NOTICE_MISS_CALL,
//                NOTICE_MISS_CALL,
//                NotificationManager.IMPORTANCE_HIGH)
//        initChannel(noticeMissChannel)
//
        List<NotificationChannel> channels = new ArrayList<>();
        channels.add(silentChannel);
        channels.add(alertChannel);
        nm.createNotificationChannels(channels);
    }

    @TargetApi(Build.VERSION_CODES.O)
    public void initChannel(NotificationChannel channel) {
        channel.canBypassDnd();
        //闪光灯
        channel.enableLights(true);
        //锁屏显示通知
        channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        //闪关灯的灯光颜色
        channel.setLightColor(Color.RED);
        //桌面launcher的消息角标
        channel.canShowBadge();
        //是否允许震动
        channel.enableVibration(true);
        //设置可绕过  请勿打扰模式
        channel.setBypassDnd(true);
        //设置震动模式
        channel.setVibrationPattern(new long[]{100L, 100L, 200L});
        //是否会有灯光
        channel.shouldShowLights();
    }

}