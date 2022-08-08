package com.esharp.sdk.activity.apply.leave;

import com.esharp.sdk.base.BaseObserver;
import com.esharp.sdk.base.BasePresenter;
import com.esharp.sdk.http.HttpFunction;
import com.esharp.sdk.http.HttpService;
import com.esharp.sdk.rxjava.SchedulerUtils;

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
