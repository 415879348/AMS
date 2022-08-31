package com.esharp.ams.notify;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import com.esharp.ams.App;
import com.esharp.ams.ui.MainActivity;
import com.esharp.sdk.bean.response.AssetAlertBean;
import androidx.core.app.NotificationCompat;

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

    public static void notify(AssetAlertBean assetAlertBean) {
        NotificationCompat.Builder builder = NotificationUtil.createBaseNotification(NotificationChannels.getInstance().NOTICE_ALERT);

        builder.setContentTitle("警报");
        builder.setContentText("警报");

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
}