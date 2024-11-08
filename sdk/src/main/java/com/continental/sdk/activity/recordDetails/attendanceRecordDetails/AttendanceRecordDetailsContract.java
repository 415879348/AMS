package com.continental.sdk.activity.recordDetails.attendanceRecordDetails;

import com.continental.sdk.base.IBasePresenter;
import com.continental.sdk.base.IBaseView;
import com.continental.sdk.bean.response.AttendanceDetailsVo;

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
