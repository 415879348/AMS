package com.esharp.ams.contract;

import com.esharp.sdk.base.IBasePresenter;
import com.esharp.sdk.base.IBaseView;
import com.esharp.sdk.bean.response.DeviceBean;

public interface AssetDetailContract {

    interface View extends IBaseView {
        void getDataSuc(DeviceBean it);
    }

    interface Presenter extends IBasePresenter {
        void getData(long deviceId);
    }
}
