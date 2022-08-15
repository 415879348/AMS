package com.esharp.ams.contract;

import com.esharp.sdk.base.IBasePresenter;
import com.esharp.sdk.base.IBaseView;
import com.esharp.sdk.bean.response.DeviceVo;
import com.esharp.sdk.bean.response.WorkOrderVo;

public interface WorkOrderContract {

    interface View extends IBaseView {
        void refreshData(WorkOrderVo it);
        void appendData(WorkOrderVo it);
    }

    interface Presenter extends IBasePresenter {
        void getData(int current);
    }
}
