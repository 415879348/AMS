package com.esharp.ams.contract;

import com.esharp.sdk.base.IBasePresenter;
import com.esharp.sdk.base.IBaseView;
import com.esharp.sdk.bean.request.IDListVo;
import com.esharp.sdk.bean.response.DeviceVo;

import retrofit2.http.Body;

public interface AssetsContract {
    interface View extends IBaseView {
        void refreshData(DeviceVo it);
        void appendData(DeviceVo it);
        void deleteDeviceSuc(boolean it);
    }

    interface Presenter extends IBasePresenter {
        void getData(int current);

        void deleteDevice(IDListVo ids);

    }
}
