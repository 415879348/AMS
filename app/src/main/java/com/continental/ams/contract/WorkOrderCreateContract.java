package com.continental.ams.contract;

import com.continental.sdk.base.IBasePresenter;
import com.continental.sdk.base.IBaseView;
import com.continental.sdk.bean.request.CreateWorkOrderVo;
import com.continental.sdk.bean.response.DeviceBean;
import com.continental.sdk.bean.response.UserVo;
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
