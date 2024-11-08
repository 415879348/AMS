package com.continental.ams.presenter;

import com.continental.ams.contract.WorkOrderContract;
import com.continental.sdk.Constant;
import com.continental.sdk.base.BaseObserver;
import com.continental.sdk.base.BasePresenter;
import com.continental.sdk.http.HttpService;
import com.continental.sdk.rxjava.HttpResultOperator;
import com.continental.sdk.rxjava.ProgressOperator;
import com.continental.sdk.rxjava.SchedulerUtils;

import java.util.HashMap;
import java.util.Map;

public class WorkOrderPresenter extends BasePresenter<WorkOrderContract.View> implements WorkOrderContract.Presenter {

    public WorkOrderPresenter(WorkOrderContract.View mView) {
        super(mView);
    }

    @Override
    public void getData(int current) {
        Map<String, String> map = new HashMap<>();
        map.put(Constant.KEY_CURRENT, current+"");
        map.put(Constant.KEY_SIZE, Constant.SIZE+"");
        HttpService.get().workOrderProcess(map)
                .lift(new HttpResultOperator<>())
                .compose(SchedulerUtils.io_main_single())
                .lift(new ProgressOperator<>(mView, -1))
                .subscribe(new BaseObserver<>(mView, current == 1 ? mView::refreshData : mView::appendData));
    }


    @Override
    public void getData(int current, Map<String, String> map) {
        map.put(Constant.KEY_CURRENT, current+"");
        map.put(Constant.KEY_SIZE, Constant.SIZE+"");
        HttpService.get().workOrderProcess(map)
                .lift(new HttpResultOperator<>())
                .compose(SchedulerUtils.io_main_single())
                .doFinally(mView::refreshFinish)
                .subscribe(new BaseObserver<>(mView, current == 1 ? mView::refreshData : mView::appendData));
    }
}
