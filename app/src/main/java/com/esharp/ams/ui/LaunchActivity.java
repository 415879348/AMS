package com.esharp.ams.ui;

import android.os.Handler;
import android.util.Pair;

import com.blankj.utilcode.util.LogUtils;
import com.esharp.ams.App;
import com.esharp.ams.R;
import com.esharp.ams.contract.LaunchActContract;
import com.esharp.ams.presenter.LaunchActPresenter;
import com.esharp.sdk.Constant;
import com.esharp.sdk.SPConfig;
import com.esharp.sdk.SPGlobalManager;
import com.esharp.sdk.SPLocal;
import com.esharp.sdk.SPSdkUtil;
import com.esharp.sdk.base.BaseActivity;
import com.esharp.sdk.base.BaseMvpActivity;
import com.esharp.sdk.bean.request.FieldVo;
import com.esharp.sdk.bean.response.AssetAlertVo;
import com.esharp.sdk.bean.response.DeviceBean;
import com.esharp.sdk.bean.response.DeviceVo;
import com.esharp.sdk.bean.response.DictionaryBean;
import com.esharp.sdk.bean.response.DictionaryVo;
import com.esharp.sdk.bean.response.LanguageVo;
import com.esharp.sdk.bean.response.NodeVo;
import com.esharp.sdk.bean.response.Token;
import com.esharp.sdk.bean.response.UserVo;
import com.esharp.sdk.bean.response.WorkOrderBean;
import com.esharp.sdk.bean.response.WorkOrderVo;
import com.esharp.sdk.utils.LocalUtils;

import java.util.List;

import androidx.appcompat.app.AppCompatDelegate;

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
//        mPresenter.method();

        mPresenter.language();

//        Token token = SPGlobalManager.getToken();
//        LogUtils.json(token);
//        if (token != null) {
//            MainActivity.startActivity(this);
//        } else {
//            LoginTMSActivity.startActivity(this);
//        }
//        finish();
    }

    @Override
    public void onLoginSuccess(String it) {
        LogUtils.json(it);
        SPGlobalManager.refreshToken(new Token(it));
        LogUtils.json(SPGlobalManager.getToken());
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

    @Override
    public void common(Object it) {
        LogUtils.json(it);

    }

    @Override
    public void language(List<LanguageVo> it) {
        LogUtils.json(it);


        SPLocal systemLocal = SPGlobalManager.getLanguage();
        LogUtils.json(systemLocal);
//        zh_HK,zh_CN,en_US
        LogUtils.json(systemLocal.getLocale().toString());

        for (int i = 0; i < it.size(); i++) {
            if (it.get(i).equals(systemLocal.getLocale().toString())) {
                SPGlobalManager.setLanguage(systemLocal);
//              更新application里的context就会全部页面起效果
                LocalUtils.initLocal(App.mApp);
                break;
            } else {
                SPGlobalManager.setLanguage(Constant.TC);

                LocalUtils.initLocal(App.mApp);
            }
        }

        Token token = SPGlobalManager.getToken();
        LogUtils.json(token);
        if (token != null) {
            MainActivity.startActivity(LaunchActivity.this);
        } else {
            LoginTMSActivity.startActivity(LaunchActivity.this);
        }
        finish();

    }

}