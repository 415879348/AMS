package com.esharp.sdk.activity.approve;

import com.esharp.sdk.base.BaseObserver;
import com.esharp.sdk.base.BasePresenter;
import com.esharp.sdk.http.HttpFunction;
import com.esharp.sdk.http.HttpService;
import com.esharp.sdk.rxjava.SchedulerUtils;

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
