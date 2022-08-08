package com.esharp.sdk.activity.apply.leave;

import com.esharp.sdk.activity.apply.IApplyView;
import com.esharp.sdk.base.IBasePresenter;
import com.esharp.sdk.bean.response.LeaveTypeVo;
import com.esharp.sdk.bean.response.RecordDetailVo;

import java.util.List;

public interface LeaveContract {
    interface View extends IApplyView {
        void getLeaveTypeListSuccess(List<LeaveTypeVo> it);
        void getLeaveRecordDetailSuccess(RecordDetailVo it);
    }

    interface Presenter extends IBasePresenter {
        void leaveTypeList();
        void getLeaveRecordDetail(long applyId);
    }

    interface Model {
        void method();
    }
}
