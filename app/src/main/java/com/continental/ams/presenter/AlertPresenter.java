package com.continental.ams.presenter;

import com.continental.ams.contract.AlertContract;
import com.continental.sdk.Constant;
import com.continental.sdk.base.BaseObserver;
import com.continental.sdk.base.BasePresenter;
import com.continental.sdk.http.HttpFunction;
import com.continental.sdk.http.HttpService;
import com.continental.sdk.rxjava.SchedulerUtils;

import java.util.HashMap;
import java.util.Map;

public class AlertPresenter extends BasePresenter<AlertContract.View> implements AlertContract.Presenter {

    public AlertPresenter(AlertContract.View mView) {
        super(mView);
    }

    @Override
    public void getData(int current) {
        Map<String, String> map = new HashMap<>();
        map.put("current", current + "");
        map.put("size", Constant.SIZE + "");
//        map.put("status", "0"); // 状态 0：未处理 1：已处理
        HttpService.get().deviceAlertLog(map)
                .map(new HttpFunction<>())
                .compose(SchedulerUtils.io_main_single())
                .doFinally(mView::refreshFinish)
                .subscribe(new BaseObserver<>(mView, current == 1 ? mView::refreshData : mView::appendData));
    }

    @Override
    public void deviceAlertLogProcess(String id) {
        HttpService.get().deviceAlertLogProcess(id)
                .map(new HttpFunction<>())
                .compose(SchedulerUtils.io_main_single())
                .subscribe(new BaseObserver<>(mView, mView::deviceAlertLogProcessSuc));
    }
}
