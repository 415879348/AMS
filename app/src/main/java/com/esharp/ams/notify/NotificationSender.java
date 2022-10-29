package com.esharp.ams.notify;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RemoteViews;

import com.esharp.ams.App;
import com.esharp.ams.R;
import com.esharp.ams.ui.MainActivity;
import com.esharp.sdk.bean.response.AssetAlertBean;
import com.esharp.sdk.utils.DateTimeUtils;
import com.esharp.sdk.utils.ResUtils;
import androidx.core.app.NotificationCompat;
import cn.jpush.android.api.NotificationMessage;

public class NotificationSender {

    private NotificationSender mInstance = null;

    public NotificationSender getInstance() {
        if (mInstance == null) {
            synchronized(NotificationSender.class) {
                if (mInstance == null) {
                    mInstance = new NotificationSender();
                }
            }
        }
        return mInstance;
    }

    public static void notify(Context context, AssetAlertBean assetAlertBean) {
        NotificationCompat.Builder builder = NotificationUtil.createBaseNotification(NotificationChannels.getInstance().NOTICE_ALERT);

//        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.item_notification);
//        remoteViews.setTextViewText(R.id.tv_title, "tv_title");
//        remoteViews.setTextViewText(R.id.tv_detail, "tv_detail");
//        remoteViews.setTextViewText(R.id.tv_date, "tv_date");
//        remoteViews.setImageViewResource(R.id.icon, R.drawable.icon1);
//        builder.setCustomContentView(remoteViews);

//        1是异常记录 2是定时保养记录
        if (assetAlertBean.getType() == 1) {
            builder.setContentTitle(ResUtils.getString(R.string.record_exception));
        } else if (assetAlertBean.getType() == 2) {
            builder.setContentTitle(ResUtils.getString(R.string.record_maintain));
        }
        builder.setSubText(DateTimeUtils.millis2Date(assetAlertBean.getDate(), DateTimeUtils.yyyy_MM_dd_HH_mm_ss));
//        1：超温警报 2：电量过低 3：工单超时
//        if (assetAlertBean.getErrorType() == 1) {
//            builder.setContentText(ResUtils.getString(R.string.over_temperature_alarm));
//            sb.append(ResUtils.getString(R.string.over_temperature_alarm)).append("\n").append(DateTimeUtils.millis2Date(assetAlertBean.getCreateTime(), DateTimeUtils.yyyy_MM_dd_HH_mm_ss));
//        } else if (assetAlertBean.getErrorType() == 2) {
//            builder.setContentText(ResUtils.getString(R.string.low_power));
//            sb.append(ResUtils.getString(R.string.low_power));
//        } else if (assetAlertBean.getErrorType() == 3) {
//            builder.setContentText(ResUtils.getString(R.string.work_order_timeout));
//            sb.append(ResUtils.getString(R.string.work_order_timeout));
//        }
        builder.setContentText(assetAlertBean.getMessage());

        Intent intent = new Intent(App.mApp, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("AssetAlertBean", assetAlertBean);
        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );

        PendingIntent pendingIntent = PendingIntent.getActivity(App.mApp, (int) ((Math.random() * 100000000)),
                    intent, Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ? PendingIntent.FLAG_IMMUTABLE : PendingIntent.FLAG_UPDATE_CURRENT);
        int notificationID = (int) ((Math.random() * 100000000));
        builder.setContentIntent(pendingIntent);
        NotificationUtil.getNotificationManager().notify(notificationID, builder.build());
    }

    public static void notify(Context context, NotificationMessage message) {
        NotificationCompat.Builder builder = NotificationUtil.createBaseNotification(NotificationChannels.getInstance().NOTICE_ALERT);

        builder.setContentTitle(message.notificationTitle);
        builder.setContentText(message.notificationContent);

        Intent intent = new Intent(context, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("TARGET", "AlertFragment");
        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );

        PendingIntent pendingIntent = PendingIntent.getActivity(context, (int) ((Math.random() * 100000000)),
                intent, Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ? PendingIntent.FLAG_IMMUTABLE : PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        NotificationUtil.getNotificationManager().notify((int) ((Math.random() * 100000000)), builder.build());
    }
}