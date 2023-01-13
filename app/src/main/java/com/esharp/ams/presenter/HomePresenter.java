package com.esharp.ams.presenter;

import com.esharp.ams.contract.HomeContract;
import com.esharp.sdk.base.BaseObserver;
import com.esharp.sdk.base.BasePresenter;
import com.esharp.sdk.http.HttpFunction;
import com.esharp.sdk.http.HttpService;
import com.esharp.sdk.rxjava.SchedulerUtils;
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
