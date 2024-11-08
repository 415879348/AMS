package com.continental.sdk.activity.recordDetails.leaverecorddetails;

import com.continental.sdk.base.BaseObserver;
import com.continental.sdk.base.BasePresenter;
import com.continental.sdk.http.HttpFunction;
import com.continental.sdk.http.HttpService;
import com.continental.sdk.rxjava.SchedulerUtils;

public class LeaveRecordDetailsPresenter extends BasePresenter<LeaveRecordDetailsContract.View> implements LeaveRecordDetailsContract.Presenter {

    public LeaveRecordDetailsPresenter(LeaveRecordDetailsContract.View mView) {
        super(mView);
    }

    @Override
    public void getLeaveRecordDetailAll(long applyId) {
        HttpService.get()
                .leaveOrdinaryFlowDetailsAll(applyId)
                .map(new HttpFunction<>())
                .compose(SchedulerUtils.io_main_single())
                .subscribe(new BaseObserver<>(mView, mView::getLeaveRecordDetailAllSuccess));
    }
}
