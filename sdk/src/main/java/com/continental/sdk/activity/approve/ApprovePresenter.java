package com.continental.sdk.activity.approve;

import com.continental.sdk.base.BaseObserver;
import com.continental.sdk.base.BasePresenter;
import com.continental.sdk.http.HttpFunction;
import com.continental.sdk.http.HttpService;
import com.continental.sdk.rxjava.SchedulerUtils;

public class ApprovePresenter extends BasePresenter<ApproveContract.View> implements ApproveContract.Presenter {
    public ApprovePresenter(ApproveContract.View mView) {
        super(mView);
    }

    @Override
    public void repairDetails(String applyId) {
        HttpService.get()
                .repairDetails(applyId)
                .map(new HttpFunction<>())
                .compose(SchedulerUtils.io_main_single())
                .subscribe(new BaseObserver<>(mView, mView::getRepairDetailsSuccess));
    }
}
