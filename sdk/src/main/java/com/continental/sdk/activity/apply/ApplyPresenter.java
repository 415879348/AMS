package com.continental.sdk.activity.apply;

import com.continental.sdk.base.BaseObserver;
import com.continental.sdk.base.BasePresenter;
import com.continental.sdk.bean.request.RequestApplyVo;
import com.continental.sdk.http.HttpFunction;
import com.continental.sdk.http.HttpService;
import com.continental.sdk.rxjava.SchedulerUtils;

public class ApplyPresenter extends BasePresenter<ApplyContract.IHost> implements ApplyContract.Presenter {

    public ApplyPresenter(ApplyContract.IHost mView) {
        super(mView);
    }

    @Override
    public void repairOrdinaryFlowCreate(RequestApplyVo it) {
        HttpService.get()
                .repairOrdinaryFlowCreate(it)
                .map(new HttpFunction<>())
                .compose(SchedulerUtils.io_main_single())
                .subscribe(new BaseObserver<>(mView, mView::onOrdinaryFlowCreateSuccess));
    }

    @Override
    public void repairOrdinaryFlowUpdate(RequestApplyVo it) {
        HttpService.get()
                .repairOrdinaryFlowUpdate(it)
                .map(new HttpFunction<>())
                .compose(SchedulerUtils.io_main_single())
                .subscribe(new BaseObserver<>(mView, mView::onOrdinaryFlowUpdateSuccess));
    }

    @Override
    public void leaveOrdinaryFlowCreate(RequestApplyVo it) {
        HttpService.get()
                .leaveOrdinaryFlowCreate(it)
                .map(new HttpFunction<>())
                .compose(SchedulerUtils.io_main_single())
                .subscribe(new BaseObserver<>(mView, mView::onOrdinaryFlowCreateSuccess));
    }

    @Override
    public void otOrdinaryFlowCreate(RequestApplyVo it) {
        HttpService.get()
                .otOrdinaryFlowCreate(it)
                .map(new HttpFunction<>())
                .compose(SchedulerUtils.io_main_single())
                .subscribe(new BaseObserver<>(mView, mView::onOrdinaryFlowCreateSuccess));
    }
}
