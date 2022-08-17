package com.esharp.ams.contract;

import com.esharp.sdk.base.IBasePresenter;
import com.esharp.sdk.base.IBaseView;
import com.esharp.sdk.bean.request.IDListVo;
import com.esharp.sdk.bean.response.DeviceVo;

import java.util.Map;

import retrofit2.http.Body;

public interface AssetsContract {
    interface View extends IBaseView {
        void refreshData(DeviceVo it);
        void appendData(DeviceVo it);
        void deleteDeviceSuc(boolean it);
        void refreshFinish();
    }

    interface Presenter extends IBasePresenter {
        void getData(int current);

        void getData(int current, Map<String, String> map);

        void deleteDevice(IDListVo ids);

    }
}
