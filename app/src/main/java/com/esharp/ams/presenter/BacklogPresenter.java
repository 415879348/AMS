package com.esharp.ams.presenter;

import com.esharp.ams.contract.BacklogContract;
import com.esharp.sdk.base.BaseObserver;
import com.esharp.sdk.base.BasePresenter;
import com.esharp.sdk.http.HttpService;
import com.esharp.sdk.rxjava.HttpResultOperator;
import com.esharp.sdk.rxjava.ProgressOperator;
import com.esharp.sdk.rxjava.SchedulerUtils;

public class BacklogPresenter extends BasePresenter<BacklogContract.View> implements BacklogContract.Presenter {

    public BacklogPresenter(BacklogContract.View mView) {
        super(mView);
    }

    @Override
    public void getData(int current, int size) {
        HttpService.get().workOrderProcess(current, size)
                .lift(new HttpResultOperator<>())
                .compose(SchedulerUtils.io_main_single())
                .lift(new ProgressOperator<>(mView, -1))
                .subscribe(new BaseObserver<>(mView, mView::getDataSuc));
    }


}
