package com.esharp.ams.contract;

import com.esharp.sdk.base.IBasePresenter;
import com.esharp.sdk.base.IBaseView;
import com.esharp.sdk.bean.request.CreateWorkOrderVo;
import com.esharp.sdk.bean.response.DeviceBean;
import com.esharp.sdk.bean.response.UserVo;
import java.util.List;

public interface WorkOrderCreateContract {

    interface View extends IBaseView {
        void deviceAllSuc(List<DeviceBean> it);
        void userAllSuc(List<UserVo> it);
        void createWorkOrderSuc(boolean it);
    }

    interface Presenter extends IBasePresenter {
        void deviceAll();
        void userAll();
        void createWorkOrder(CreateWorkOrderVo it);
    }
}
