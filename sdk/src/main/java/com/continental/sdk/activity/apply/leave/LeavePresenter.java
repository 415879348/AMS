package com.continental.sdk.activity.apply.leave;

import com.continental.sdk.base.BaseObserver;
import com.continental.sdk.base.BasePresenter;
import com.continental.sdk.http.HttpFunction;
import com.continental.sdk.http.HttpService;
import com.continental.sdk.rxjava.SchedulerUtils;

public class LeavePresenter extends BasePresenter<LeaveContract.View>  implements LeaveContract.Presenter {

    public LeavePresenter(LeaveContract.View mView) {
        super(mView);
    }

    @Override
    public void leaveTypeList() {
        HttpService.get()
                .leaveTypeList()
                .map(new HttpFunction<>())
                .compose(SchedulerUtils.io_main_single())
                .subscribe(new BaseObserver<>(mView, mView::getLeaveTypeListSuccess));
    }

    @Override
    public void getLeaveRecordDetail(long applyId) {
        HttpService.get()
                .leaveOrdinaryFlowDetailsAll(applyId)
                .map(new HttpFunction<>())
                .compose(SchedulerUtils.io_main_single())
                .subscribe(new BaseObserver<>(mView, mView::getLeaveRecordDetailSuccess));
    }
}
