package com.continental.sdk.activity.recordDetails.attendanceRecordDetails;

import com.continental.sdk.base.BaseObserver;
import com.continental.sdk.base.BasePresenter;
import com.continental.sdk.http.HttpFunction;
import com.continental.sdk.http.HttpService;
import com.continental.sdk.rxjava.SchedulerUtils;

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
