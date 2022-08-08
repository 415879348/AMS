package com.esharp.ams.contract;

import com.esharp.sdk.base.IBasePresenter;
import com.esharp.sdk.base.IBaseView;
import com.esharp.sdk.base.IHostView;
import com.esharp.sdk.bean.response.DeviceInfoForm;
import com.esharp.sdk.bean.response.WorkOrderVo;

public interface CreateAssetContract {
    interface IHost extends IHostView {
        void getDataSuc();
        void addAppDevice(boolean it);
    }

//    interface View extends IBaseView {
//        void getDataSuc(WorkOrderVo it);
//
//    }

    interface Presenter extends IBasePresenter {
        void addAppDevice(DeviceInfoForm it);
    }
}
