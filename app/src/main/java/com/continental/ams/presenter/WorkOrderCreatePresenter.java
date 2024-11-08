package com.continental.ams.presenter;

import com.continental.ams.contract.WorkOrderCreateContract;
import com.continental.sdk.base.BaseObserver;
import com.continental.sdk.base.BasePresenter;
import com.continental.sdk.bean.request.CreateWorkOrderVo;
import com.continental.sdk.http.HttpService;
import com.continental.sdk.rxjava.HttpResultOperator;
import com.continental.sdk.rxjava.ProgressOperator;
import com.continental.sdk.rxjava.SchedulerUtils;

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
