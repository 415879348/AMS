package com.continental.ams.contract;

import com.continental.sdk.base.IBasePresenter;
import com.continental.sdk.base.IBaseView;
import com.continental.sdk.bean.response.DeviceBean;

public interface AssetDetailContract {

    interface View extends IBaseView {
        void getDataSuc(DeviceBean it);
    }

    interface Presenter extends IBasePresenter {
        void getData(String deviceId);
    }
}
