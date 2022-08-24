package com.esharp.ams.ui;

import android.util.Pair;

import com.blankj.utilcode.util.LogUtils;
import com.esharp.ams.R;
import com.esharp.ams.contract.LaunchActContract;
import com.esharp.ams.presenter.LaunchActPresenter;
import com.esharp.sdk.SPConfig;
import com.esharp.sdk.SPSdkUtil;
import com.esharp.sdk.base.BaseActivity;
import com.esharp.sdk.base.BaseMvpActivity;
import com.esharp.sdk.bean.request.FieldVo;
import com.esharp.sdk.bean.response.AssetAlertVo;
import com.esharp.sdk.bean.response.DeviceBean;
import com.esharp.sdk.bean.response.DeviceVo;
import com.esharp.sdk.bean.response.DictionaryBean;
import com.esharp.sdk.bean.response.DictionaryVo;
import com.esharp.sdk.bean.response.NodeVo;
import com.esharp.sdk.bean.response.TokenVo;
import com.esharp.sdk.bean.response.UserVo;
import com.esharp.sdk.bean.response.WorkOrderBean;
import com.esharp.sdk.bean.response.WorkOrderVo;

import java.util.List;

public class LaunchActivity extends BaseMvpActivity<LaunchActContract.Presenter> implements LaunchActContract.IHost {

    @Override
    protected Pair<Integer, LaunchActContract.Presenter> layoutAndPresenter() {
        return Pair.create(R.layout.activity_launch, new LaunchActPresenter(this));
    }

    @Override
    public BaseActivity getHost() {
        return null;
    }

    @Override
    protected boolean isShowTitle() {
        return false;
    }

    @Override
    protected void init() {
        mPresenter.method();

//        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.notify(1,createForegroundNotification());

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            String channelId = "chat";
//            String channelName = "聊天消息";
//            int importance = NotificationManager.IMPORTANCE_HIGH;
//            createNotificationChannel(channelId, channelName, importance);
//
//            channelId = "subscribe";
//            channelName = "订阅消息";
//            importance = NotificationManager.IMPORTANCE_DEFAULT;
//            createNotificationChannel(channelId, channelName, importance);
//        }
//
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {//Android 8.0及以上
//
//            NotificationChannel channel = manager.getNotificationChannel("chat");//CHANNEL_ID是自己定义的渠道ID
//
//            LogUtils.i(channel);
//
//            //未开启悬浮通知(横幅通知)情况下，channel.getImportance()默认值为3，即channel.getImportance()==NotificationManager.IMPORTANCE_DEFAULT
//
//            //开启悬浮通知(横幅通知)后，channel.getImportance()的值就变为4，即channel.getImportance()==NotificationManager.IMPORTANCE_HIGH
//            if (channel.getImportance() == NotificationManager.IMPORTANCE_DEFAULT) {//未开启
//                // 跳转到设置页面
//            }
//
//        }
//
//        sendChatMsg();
//        sendSubscribeMsg();
//        getHangUpPermission("chat");
    }

//    @TargetApi(Build.VERSION_CODES.O)
//    private void createNotificationChannel(String channelId, String channelName, int importance) {
//        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
//        NotificationManager notificationManager = (NotificationManager) getSystemService(
//                NOTIFICATION_SERVICE);
//        notificationManager.createNotificationChannel(channel);
//    }
//
//    public void sendChatMsg() {
//        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel channel = manager.getNotificationChannel("chat");
//            if (channel.getImportance() == NotificationManager.IMPORTANCE_NONE) {
//                Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
//                intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
//                intent.putExtra(Settings.EXTRA_CHANNEL_ID, channel.getId());
//                startActivity(intent);
//                Toast.makeText(this, "请手动将通知打开", Toast.LENGTH_SHORT).show();
//            }
//        }
//
//        Notification notification = new NotificationCompat.Builder(this, "chat")
//                .setContentTitle("收到一条聊天消息")
//                .setContentText("今天中午吃什么？")
//                .setWhen(System.currentTimeMillis())
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
//                .setAutoCancel(true)
//                .build();
//        manager.notify(1, notification);
//    }
//
//    public void sendSubscribeMsg() {
//        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        Notification notification = new NotificationCompat.Builder(this, "subscribe")
//                .setContentTitle("收到一条订阅消息")
//                .setContentText("地铁沿线30万商铺抢购中！")
//                .setWhen(System.currentTimeMillis())
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
//                .setAutoCancel(true)
//                .build();
//        manager.notify(2, notification);
//    }
//
//    /**跳转横幅通知权限,详细channelId授予权限*/
//    private void getHangUpPermission(String channelId) {
//        Intent intent = new Intent();
//        if (Build.VERSION.SDK_INT >= 26) {
//            // android8.0单个channelid设置
//            intent.setAction(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
//            intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
//            intent.putExtra(Settings.EXTRA_CHANNEL_ID, channelId);
//        } else {
//            // android 5.0以上一起设置
//            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//            intent.putExtra("app_package", getPackageName());
//            intent.putExtra("app_uid", getApplicationInfo().uid);
//        }
//        startActivity(intent);
//    }

    /**提示代码*/
//    private Notification createForegroundNotification() {
//        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//        // 唯一的通知通道的id.
//        String notificationChannelId = "notification_channel_id_01";
//
//        // Android8.0以上的系统，新建消息通道
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            //用户可见的通道名称
//            String channelName = "Foreground Service Notification";
//            //通道的重要程度
//            int importance = NotificationManager.IMPORTANCE_HIGH;
//            NotificationChannel notificationChannel = new NotificationChannel(notificationChannelId, channelName, importance);
//            notificationChannel.setDescription("Channel description");
//            //LED灯
//            notificationChannel.enableLights(true);
//            notificationChannel.setLightColor(Color.RED);
//            //震动
//            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
//            notificationChannel.enableVibration(true);
//            // 声音(没有声音)
//            notificationChannel.setSound(null, null);
//            if (notificationManager != null) {
//                notificationManager.createNotificationChannel(notificationChannel);
//            }
//        }
//
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), notificationChannelId);
//        builder.setCategory(Notification.CATEGORY_RECOMMENDATION);
//        //通知小图标
//        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
//        //通知标题
//        builder.setContentTitle("NotificationTitle");
//        //通知内容
//        builder.setContentText("ContentText");
//        builder.setTicker("Ticker");
//        //设定通知显示的时间
//        builder.setWhen(System.currentTimeMillis());
//        //设定启动的内容
//        Intent activityIntent = new Intent(this, MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 1, activityIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//        builder.setContentIntent(pendingIntent);
//        // sdk5.0以上使用
//        builder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
//        builder.setFullScreenIntent(pendingIntent, false);
//
//        //创建通知并返回
//        return builder.build();
//    }

    @Override
    public void onLoginSuccess(String it) {
        LogUtils.json(it);
        SPSdkUtil.getInstance(this)
                .setToken(new TokenVo(it))
                .setConfig(new SPConfig())
                .start();

        mPresenter.method2();
    }

    @Override
    public void getUserAll(List<UserVo>  it) {
        LogUtils.json(it);
    }

    @Override
    public void getAuth(UserVo it) {
        LogUtils.json(it);
    }

    @Override
    public void getAppDict(List<DictionaryBean> it) {
        LogUtils.json(it);
    }

    @Override
    public void dictParent(DictionaryVo it) {
        LogUtils.json(it);
    }

    @Override
    public void getAppDeviceAll(List<DeviceBean> it) {
        LogUtils.json(it);
    }

    @Override
    public void deleteAppDevice(Boolean it) {
        LogUtils.json(it);
    }

    @Override
    public void addAppDevice(Boolean it) {
        LogUtils.json(it);
    }

    @Override
    public void updateAppDevice(Boolean it) {
        LogUtils.json(it);
    }

    @Override
    public void queryAppDevice(DeviceBean it) {
        LogUtils.json(it);
    }

    @Override
    public void workOrderProcess(WorkOrderVo it) {
        LogUtils.json(it);
    }

    @Override
    public void workOrderProcessSuc(Boolean it) {
        LogUtils.json(it);
    }

    @Override
    public void workOrderNode(NodeVo it) {
        LogUtils.json(it);
    }

    @Override
    public void workOrderID(WorkOrderBean it) {
        LogUtils.json(it);
    }

    @Override
    public void workOrderCountProcess(Integer it) {
        LogUtils.json(it);
    }

    @Override
    public void workOrderCountOver(Integer it) {
        LogUtils.json(it);
    }

    @Override
    public void deviceField(List<FieldVo> it) {
        LogUtils.json(it);
    }

    @Override
    public void deviceFieldValue(List<FieldVo> it) {
        LogUtils.json(it);
    }

    @Override
    public void version(Object it) {
        LogUtils.json(it);
    }

    @Override
    public void logout(boolean it) {
        LogUtils.json(it);
    }

    @Override
    public void device(DeviceVo it) {
        LogUtils.json(it);
    }

    @Override
    public void document(Object it) {
        LogUtils.json(it);
    }

    @Override
    public void workOrder(Object it) {
        LogUtils.json(it);
    }

    @Override
    public void end(AssetAlertVo it) {
        LogUtils.json(it);
    }

}