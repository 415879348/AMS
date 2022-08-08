package com.esharp.sdk.activity.recordDetails.leaverecorddetails;

import com.esharp.sdk.base.IBasePresenter;
import com.esharp.sdk.base.IBaseView;
import com.esharp.sdk.bean.response.RecordDetailVo;

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
