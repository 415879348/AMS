package com.esharp.ams.presenter;

import com.esharp.ams.contract.WorkOrderDetailContract;
import com.esharp.sdk.base.BaseObserver;
import com.esharp.sdk.base.BasePresenter;
import com.esharp.sdk.bean.response.HandlerVo;
import com.esharp.sdk.http.HttpService;
import com.esharp.sdk.rxjava.HttpResultOperator;
import com.esharp.sdk.rxjava.ProgressOperator;
import com.esharp.sdk.rxjava.SchedulerUtils;

public class WorkOrderDetailPresenter extends BasePresenter<WorkOrderDetailContract.View> implements WorkOrderDetailContract.Presenter {

    public WorkOrderDetailPresenter(WorkOrderDetailContract.View mView) {
        super(mView);
    }

    @Override
    public void workOrderID(String workOrderID) {
        HttpService.get().workOrderID(workOrderID)
                .lift(new HttpResultOperator<>())
                .compose(SchedulerUtils.io_main_single())
                .lift(new ProgressOperator<>(mView, -1))
                .subscribe(new BaseObserver<>(mView, mView::workOrderIDSuc));
    }


    @Override
    public void workOrderNode(String workOrderID) {
        HttpService.get().workOrderNode(workOrderID)
                .lift(new HttpResultOperator<>())
                .compose(SchedulerUtils.io_main_single())
                .lift(new ProgressOperator<>(mView, -1))
                .subscribe(new BaseObserver<>(mView, mView::workOrderNodeSuc));
    }

    @Override
    public void workOrderProcess(HandlerVo it) {
        HttpService.get().workOrderProcess(it)
                .lift(new HttpResultOperator<>())
                .compose(SchedulerUtils.io_main_single())
                .lift(new ProgressOperator<>(mView, -1))
                .subscribe(new BaseObserver<>(mView, mView::workOrderProcessSuc));
    }

    @Override
    public void userAll() {
        HttpService.get().userAll()
                .lift(new HttpResultOperator<>())
                .compose(SchedulerUtils.io_main_single())
                .lift(new ProgressOperator<>(mView, -1))
                .subscribe(new BaseObserver<>(mView, mView::userAllSuc));
    }
}