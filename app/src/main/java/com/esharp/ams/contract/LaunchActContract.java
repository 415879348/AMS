package com.esharp.ams.contract;

import com.esharp.sdk.base.IBasePresenter;
import com.esharp.sdk.base.IHostView;
import com.esharp.sdk.bean.request.FieldVo;
import com.esharp.sdk.bean.response.AssetAlertVo;
import com.esharp.sdk.bean.response.DeviceBean;
import com.esharp.sdk.bean.response.DeviceVo;
import com.esharp.sdk.bean.response.DictionaryBean;
import com.esharp.sdk.bean.response.DictionaryVo;
import com.esharp.sdk.bean.response.NodeVo;
import com.esharp.sdk.bean.response.UserVo;
import com.esharp.sdk.bean.response.WorkOrderBean;
import com.esharp.sdk.bean.response.WorkOrderVo;

import java.util.List;

public interface LaunchActContract {

    interface IHost extends IHostView {
        void onBackPressed();
        void onLoginSuccess(String it);
        void getUserAll(List<UserVo> it);
        void getAuth(UserVo it);
        void getAppDict(List<DictionaryBean> it);
        void dictParent(DictionaryVo it);
        void getAppDeviceAll(List<DeviceBean> it);
        void deleteAppDevice(Boolean it);
        void addAppDevice(Boolean it);
        void updateAppDevice(Boolean it);
        void queryAppDevice(DeviceBean it);
        void workOrderProcess(WorkOrderVo it);
        void workOrderProcessSuc(Boolean it);
        void workOrderNode(NodeVo it);
        void workOrderID(WorkOrderBean it);
        void workOrderCountProcess(Integer it);
        void workOrderCountOver(Integer it);
        void deviceField(List<FieldVo> it);
        void deviceFieldValue(List<FieldVo> it);
        void version(Object it);
        void logout(boolean it);
        void device(DeviceVo it);
        void document(Object it);
        void workOrder(Object it);
        void end(AssetAlertVo it);
    }

    interface View extends IHost {
        void method();
    }

    interface Presenter extends IBasePresenter {
        void method();
        void method2();
    }

}
