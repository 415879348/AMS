package com.continental.ams.contract;

import com.continental.sdk.base.IBasePresenter;
import com.continental.sdk.base.IBaseView;
import com.continental.sdk.bean.request.IDListVo;
import com.continental.sdk.bean.response.DeviceVo;

import java.util.Map;

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
