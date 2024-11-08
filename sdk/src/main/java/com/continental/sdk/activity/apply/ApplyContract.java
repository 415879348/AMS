package com.continental.sdk.activity.apply;

import com.continental.sdk.base.IBasePresenter;
import com.continental.sdk.base.IHostView;
import com.continental.sdk.bean.request.RequestApplyVo;
import com.continental.sdk.bean.response.RecordDetailVo;

public interface ApplyContract {

    interface IHost extends IHostView {
        void onBackPressed();
        void onOrdinaryFlowCreateSuccess(boolean it);
        void onOrdinaryFlowUpdateSuccess(boolean it);
        void getAmendRecordDetailSuccess(RecordDetailVo it);
        void getLeaveRecordDetailAllSuccess(RecordDetailVo it);
        void getOtRecordDetailsAllSuccess(RecordDetailVo it);
    }

    interface IView extends IHost {
        void requestFinish();
    }

    interface Presenter extends IBasePresenter {
        void repairOrdinaryFlowCreate(RequestApplyVo it);
        void repairOrdinaryFlowUpdate(RequestApplyVo it);
        void leaveOrdinaryFlowCreate(RequestApplyVo it);
        void otOrdinaryFlowCreate(RequestApplyVo it);
    }

}
