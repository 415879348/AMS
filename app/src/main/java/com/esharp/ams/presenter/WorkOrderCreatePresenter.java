package com.esharp.ams.presenter;

import com.esharp.ams.contract.WorkOrderCreateContract;
import com.esharp.sdk.base.BaseObserver;
import com.esharp.sdk.base.BasePresenter;
import com.esharp.sdk.bean.request.CreateWorkOrderVo;
import com.esharp.sdk.http.HttpService;
import com.esharp.sdk.rxjava.HttpResultOperator;
import com.esharp.sdk.rxjava.ProgressOperator;
import com.esharp.sdk.rxjava.SchedulerUtils;

public class WorkOrderCreatePresenter extends BasePresenter<WorkOrderCreateContract.View> implements WorkOrderCreateContract.Presenter {
    public WorkOrderCreatePresenter(WorkOrderCreateContract.View mView) {
        super(mView);
    }

    @Override
    public void deviceAll() {
        HttpService.get().appDeviceAll()
                .lift(new HttpResultOperator<>())
                .compose(SchedulerUtils.io_main_single())
                .lift(new ProgressOperator<>(mView, -1))
                .subscribe(new BaseObserver<>(mView, mView::deviceAllSuc));
    }

    @Override
    public void userAll() {
        HttpService.get().userAll()
                .lift(new HttpResultOperator<>())
                .compose(SchedulerUtils.io_main_single())
                .lift(new ProgressOperator<>(mView, -1))
                .subscribe(new BaseObserver<>(mView, mView::userAllSuc));
    }

    @Override
    public void createWorkOrder(CreateWorkOrderVo it) {
        HttpService.get().createWorkOrder(it)
                .lift(new HttpResultOperator<>())
                .compose(SchedulerUtils.io_main_single())
                .lift(new ProgressOperator<>(mView, -1))
                .subscribe(new BaseObserver<>(mView, mView::createWorkOrderSuc));
    }
}
