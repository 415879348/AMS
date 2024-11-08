package com.continental.ams.contract;

import com.continental.sdk.base.IBasePresenter;
import com.continental.sdk.base.IBaseView;
import com.continental.sdk.bean.response.HandlerVo;
import com.continental.sdk.bean.response.NodeVo;
import com.continental.sdk.bean.response.UserVo;
import com.continental.sdk.bean.response.WorkOrderBean;

import java.util.List;

public interface WorkOrderDetailContract {

    interface View extends IBaseView {
        void workOrderIDSuc(WorkOrderBean it);
        void workOrderNodeSuc(List<NodeVo> it);
        void workOrderProcessSuc(Boolean it);
        void userAllSuc(List<UserVo> it);
    }

    interface Presenter extends IBasePresenter {
        void workOrderID(String workOrderID);
        void workOrderNode(String workOrderID);
        void workOrderProcess(HandlerVo it);
        void userAll();
    }
}
