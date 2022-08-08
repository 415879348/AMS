package com.esharp.sdk.activity.recordDetails.attendanceRecordDetails;

import com.esharp.sdk.base.IBasePresenter;
import com.esharp.sdk.base.IBaseView;
import com.esharp.sdk.bean.response.AttendanceDetailsVo;

public class AttendanceRecordDetailsContract {
    interface View extends IBaseView {
        void getAttendanceDetailSuccess(AttendanceDetailsVo it);
    }

    interface Presenter extends IBasePresenter {
        void attendanceDetails(long recordId);
    }

    interface Model {
        void method();
    }
}
