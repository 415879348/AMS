package com.esharp.ams.presenter;

import com.esharp.ams.contract.HomeContract;
import com.esharp.sdk.base.BaseObserver;
import com.esharp.sdk.base.BasePresenter;
import com.esharp.sdk.http.HttpFunction;
import com.esharp.sdk.http.HttpService;
import com.esharp.sdk.rxjava.SchedulerUtils;

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {

    public HomePresenter(HomeContract.View mView) {
        super(mView);
    }

    @Override
    public void method() {

    }

    @Override
    public void workOrderCountProcess() {
        HttpService.get().workOrderCountProcess()
                .map(new HttpFunction<>())
                .compose(SchedulerUtils.io_main_single())
                .subscribe(new BaseObserver<>(mView, mView::workOrderCountProcess));
    }

    @Override
    public void workOrderCountOver() {
        HttpService.get().workOrderCountOver()
                .map(new HttpFunction<>())
                .compose(SchedulerUtils.io_main_single())
                .subscribe(new BaseObserver<>(mView, mView::workOrderCountOver));
    }

    @Override
    public void deviceAlertLogCount(int status) {
        HttpService.get().deviceAlertLogCount(status)
                .map(new HttpFunction<>())
                .compose(SchedulerUtils.io_main_single())
                .subscribe(new BaseObserver<>(mView, mView::deviceAlertLogCountSuc));
    }
}
