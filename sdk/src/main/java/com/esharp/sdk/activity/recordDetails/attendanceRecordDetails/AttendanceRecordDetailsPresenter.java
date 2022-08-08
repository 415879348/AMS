package com.esharp.sdk.activity.recordDetails.attendanceRecordDetails;

import com.esharp.sdk.base.BaseObserver;
import com.esharp.sdk.base.BasePresenter;
import com.esharp.sdk.http.HttpFunction;
import com.esharp.sdk.http.HttpService;
import com.esharp.sdk.rxjava.SchedulerUtils;

public class AttendanceRecordDetailsPresenter extends BasePresenter<AttendanceRecordDetailsContract.View> implements AttendanceRecordDetailsContract.Presenter {
    public AttendanceRecordDetailsPresenter(AttendanceRecordDetailsContract.View mView) {
        super(mView);
    }

    @Override
    public void attendanceDetails(long recordId) {
        HttpService.get()
                .attendanceDetails(recordId)
                .map(new HttpFunction<>())
                .compose(SchedulerUtils.io_main_single())
                .subscribe(new BaseObserver<>(mView, mView::getAttendanceDetailSuccess));
    }
}
