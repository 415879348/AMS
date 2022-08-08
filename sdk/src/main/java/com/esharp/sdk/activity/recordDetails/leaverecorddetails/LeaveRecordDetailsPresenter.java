package com.esharp.sdk.activity.recordDetails.leaverecorddetails;

import com.esharp.sdk.base.BaseObserver;
import com.esharp.sdk.base.BasePresenter;
import com.esharp.sdk.http.HttpFunction;
import com.esharp.sdk.http.HttpService;
import com.esharp.sdk.rxjava.SchedulerUtils;

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
