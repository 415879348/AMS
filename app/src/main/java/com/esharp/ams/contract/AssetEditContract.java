package com.esharp.ams.contract;

import com.esharp.sdk.base.IBasePresenter;
import com.esharp.sdk.base.IBaseView;
import com.esharp.sdk.bean.response.DeviceBean;
import com.esharp.sdk.bean.response.DeviceInfoForm;
import com.esharp.sdk.bean.response.DictionaryBean;

import java.util.List;

public interface AssetEditContract {

    interface View extends IBaseView {
        void getDataSuc(DeviceBean it);
        void dictAllSuc(List<DictionaryBean> it);
        void updateAppDeviceSuc(boolean it);
    }

    interface Presenter extends IBasePresenter {
        void getData(String deviceId);
//        void dictAll(int dictType);
        void updateAppDevice(DeviceInfoForm it);
    }
}
