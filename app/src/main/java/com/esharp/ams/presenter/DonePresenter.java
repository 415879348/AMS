package com.esharp.ams.presenter;

import com.esharp.ams.contract.DoneContract;
import com.esharp.sdk.base.BaseObserver;
import com.esharp.sdk.base.BasePresenter;
import com.esharp.sdk.http.HttpService;
import com.esharp.sdk.rxjava.HttpResultOperator;
import com.esharp.sdk.rxjava.ProgressOperator;
import com.esharp.sdk.rxjava.SchedulerUtils;

public class DonePresenter extends BasePresenter<DoneContract.View> implements DoneContract.Presenter {

    public DonePresenter(DoneContract.View mView) {
        super(mView);
    }

    @Override
    public void getData(int current, int size, int step) {
        HttpService.get().workOrderProcess(current, size, step)
                .lift(new HttpResultOperator<>())
                .compose(SchedulerUtils.io_main_single())
                .lift(new ProgressOperator<>(mView, -1))
                .subscribe(new BaseObserver<>(mView, mView::getDataSuc));
    }
}
