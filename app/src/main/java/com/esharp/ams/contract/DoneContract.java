package com.esharp.ams.contract;

import com.esharp.sdk.base.IBasePresenter;
import com.esharp.sdk.base.IBaseView;
import com.esharp.sdk.bean.response.WorkOrderVo;

public interface DoneContract {
    interface View extends IBaseView {
        void getDataSuc(WorkOrderVo it);
    }

    interface Presenter extends IBasePresenter {
        void getData(int current, int size);
    }
}
