package com.continental.ams.notify;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import com.continental.ams.App;
import com.continental.ams.R;
import androidx.core.app.NotificationCompat;

/**
 *@author Albener
 *2019-10-09
 */
public class NotificationUtil {

    private static NotificationUtil mInstance;

    public static NotificationUtil getInstance(){
        if(mInstance == null) {
            synchronized (NotificationUtil.class){
                if(mInstance == null){
                    mInstance = new NotificationUtil();
                }
            }
        }
        return mInstance;
    }

    private static NotificationManager mNotificationManager = null;

    public static NotificationManager getNotificationManager() {
        if (mNotificationManager == null) {
            mNotificationManager = (NotificationManager) App.mApp.getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mNotificationManager;
    }

    public static NotificationCompat.Builder createBaseNotification(String channelId) {
        NotificationCompat.Builder builder = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = new NotificationCompat.Builder(App.mApp, channelId);
        } else {
            builder = new NotificationCompat.Builder(App.mApp)
                    .setPriority(Notification.PRIORITY_MAX)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setLights(Color.RED, 1000, 0);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder.setCategory(Notification.CATEGORY_RECOMMENDATION);
        }
        builder.setAutoCancel(true);
        builder.setWhen(System.currentTimeMillis());
        builder.setSmallIcon(R.mipmap.app_icon);
        return builder;
    }

}