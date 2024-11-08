package com.continental.ams.contract;

import com.continental.sdk.base.IBasePresenter;
import com.continental.sdk.base.IBaseView;
import com.continental.sdk.bean.response.WorkOrderVo;

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
