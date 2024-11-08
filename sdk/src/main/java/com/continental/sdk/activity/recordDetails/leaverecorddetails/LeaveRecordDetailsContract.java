package com.continental.sdk.activity.recordDetails.leaverecorddetails;

import com.continental.sdk.base.IBasePresenter;
import com.continental.sdk.base.IBaseView;
import com.continental.sdk.bean.response.RecordDetailVo;

public class LeaveRecordDetailsContract {

    interface View extends IBaseView {
        void getLeaveRecordDetailAllSuccess(RecordDetailVo it);
    }

    interface Presenter extends IBasePresenter {
        void getLeaveRecordDetailAll(long applyId);
    }

    interface Model {
        void method();
    }

}
