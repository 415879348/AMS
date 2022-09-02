package com.esharp.ams.contract;

import com.esharp.sdk.base.IBasePresenter;
import com.esharp.sdk.base.IBaseView;
import com.esharp.sdk.bean.response.WorkOrderVo;

import java.util.Map;

public interface WorkOrderContract {

    interface View extends IBaseView {
        void refreshData(WorkOrderVo it);
        void appendData(WorkOrderVo it);
        void refreshFinish();
    }

    interface Presenter extends IBasePresenter {
        void getData(int current);

        void getData(int current, Map<String, String> map);
    }
}
