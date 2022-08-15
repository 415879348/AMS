package com.esharp.ams.presenter;

import com.esharp.ams.contract.BacklogContract;
import com.esharp.sdk.base.BaseObserver;
import com.esharp.sdk.base.BasePresenter;
import com.esharp.sdk.http.HttpService;
import com.esharp.sdk.rxjava.HttpResultOperator;
import com.esharp.sdk.rxjava.ProgressOperator;
import com.esharp.sdk.rxjava.SchedulerUtils;

import java.util.HashMap;
import java.util.Map;

public class BacklogPresenter extends BasePresenter<BacklogContract.View> implements BacklogContract.Presenter {

    public BacklogPresenter(BacklogContract.View mView) {
        super(mView);
    }

    @Override
    public void getData(int current, int size) {
        Map<String, String> map = new HashMap<>();
        map.put("current", current+"");
        map.put("size", size+"");
        map.put("processStep", "0"); // 0待办， 1已办
        HttpService.get().workOrderProcess(map)
                .lift(new HttpResultOperator<>())
                .compose(SchedulerUtils.io_main_single())
                .lift(new ProgressOperator<>(mView, -1))
                .subscribe(new BaseObserver<>(mView, mView::getDataSuc));
    }


}
