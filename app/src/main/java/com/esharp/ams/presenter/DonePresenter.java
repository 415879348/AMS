package com.esharp.ams.presenter;

import com.esharp.ams.contract.DoneContract;
import com.esharp.sdk.Constant;
import com.esharp.sdk.base.BaseObserver;
import com.esharp.sdk.base.BasePresenter;
import com.esharp.sdk.http.HttpService;
import com.esharp.sdk.rxjava.HttpResultOperator;
import com.esharp.sdk.rxjava.SchedulerUtils;
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
        HttpService.get().workOrderProcess(map)
                .lift(new HttpResultOperator<>())
                .compose(SchedulerUtils.io_main_single())
                .doFinally(mView::refreshFinish)
                .subscribe(new BaseObserver<>(mView, current == 1 ? mView::refreshData : mView::appendData));
    }
}
