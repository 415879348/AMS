package com.continental.ams.presenter;

import com.continental.ams.contract.HomeContract;
import com.continental.sdk.base.BaseObserver;
import com.continental.sdk.base.BasePresenter;
import com.continental.sdk.http.HttpFunction;
import com.continental.sdk.http.HttpService;
import com.continental.sdk.rxjava.SchedulerUtils;
import java.util.HashMap;
import java.util.Map;

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
        Map<String, String> map = new HashMap<>();
        map.put("overStep", "0");
        HttpService.get().workOrderCountOver(map)
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
