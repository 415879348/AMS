package com.esharp.ams.contract;

import com.esharp.sdk.base.IBasePresenter;
import com.esharp.sdk.base.IBaseView;
import com.esharp.sdk.base.IHostView;
import com.esharp.sdk.bean.response.WorkOrderVo;

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
