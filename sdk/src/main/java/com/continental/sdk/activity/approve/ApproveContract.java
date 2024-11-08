package com.continental.sdk.activity.approve;

import com.continental.sdk.base.IBasePresenter;
import com.continental.sdk.base.IBaseView;
import com.continental.sdk.bean.response.RepairDetailVo;

public class ApproveContract {
    interface View extends IBaseView {
        void getRepairDetailsSuccess(RepairDetailVo it);
    }

    interface Presenter extends IBasePresenter {
        void repairDetails(String applyId);
    }

    interface Model {
        void method();
    }
}
