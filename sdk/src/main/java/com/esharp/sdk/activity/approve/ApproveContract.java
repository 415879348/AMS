package com.esharp.sdk.activity.approve;

import com.esharp.sdk.base.IBasePresenter;
import com.esharp.sdk.base.IBaseView;
import com.esharp.sdk.bean.response.RepairDetailVo;

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
