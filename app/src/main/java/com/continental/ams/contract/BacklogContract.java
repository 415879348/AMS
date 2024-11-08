package com.continental.ams.contract;

import com.continental.sdk.base.IBasePresenter;
import com.continental.sdk.base.IBaseView;
import com.continental.sdk.base.IHostView;
import com.continental.sdk.bean.response.WorkOrderVo;

public interface BacklogContract {

    interface IHost extends IHostView {
        void onItemClick();
    }

    interface View extends IBaseView {
        void refreshData(WorkOrderVo it);

        void appendData(WorkOrderVo it);

        void refreshFinish();
    }

    interface Presenter extends IBasePresenter {
        void getData(int current, int size);

        void getData(int current);
    }
}
