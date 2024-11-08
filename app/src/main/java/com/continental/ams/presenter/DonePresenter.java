package com.continental.ams.presenter;

import com.continental.ams.contract.DoneContract;
import com.continental.sdk.Constant;
import com.continental.sdk.base.BaseObserver;
import com.continental.sdk.base.BasePresenter;
import com.continental.sdk.http.HttpService;
import com.continental.sdk.rxjava.HttpResultOperator;
import com.continental.sdk.rxjava.SchedulerUtils;
import java.util.HashMap;
import java.util.Map;

public class DonePresenter extends BasePresenter<DoneContract.View> implements DoneContract.Presenter {

    public DonePresenter(DoneContract.View mView) {
        super(mView);
    }

    @Override
    public void getData(int current, int size) {
        Map<String, String> map = new HashMap<>();
        map.put("current", current+"");
        map.put("size", size+"");
        map.put("step", "0"); // 0待办， 1已办
        HttpService.get().workOrderProcess(map)
                .lift(new HttpResultOperator<>())
                .compose(SchedulerUtils.io_main_single())
                .doFinally(mView::refreshFinish)
                .subscribe(new BaseObserver<>(mView, current == 1 ? mView::refreshData : mView::appendData));
    }

    @Override
    public void getData(int current) {
        Map<String, String> map = new HashMap<>();
        map.put("current", current + "");
        map.put("size", Constant.SIZE + "");
        map.put("step", "0"); // 0待办， 1已办
        map.put("overStep", "0");
        HttpService.get().workOrderProcess(map)
                .lift(new HttpResultOperator<>())
                .compose(SchedulerUtils.io_main_single())
                .doFinally(mView::refreshFinish)
                .subscribe(new BaseObserver<>(mView, current == 1 ? mView::refreshData : mView::appendData));
    }
}
