package com.continental.ams.presenter;

import com.continental.ams.contract.AssetDetailContract;
import com.continental.sdk.base.BaseObserver;
import com.continental.sdk.base.BasePresenter;
import com.continental.sdk.http.HttpService;
import com.continental.sdk.rxjava.HttpResultOperator;
import com.continental.sdk.rxjava.ProgressOperator;
import com.continental.sdk.rxjava.SchedulerUtils;

public class AssetDetailPresenter extends BasePresenter<AssetDetailContract.View> implements AssetDetailContract.Presenter {
    public AssetDetailPresenter(AssetDetailContract.View mView) {
        super(mView);
    }

    @Override
    public void getData(String deviceId) {
        HttpService.get().queryAppDevice(deviceId+"")
                .lift(new HttpResultOperator<>())
                .compose(SchedulerUtils.io_main_single())
                .lift(new ProgressOperator<>(mView, -1))
                .subscribe(new BaseObserver<>(mView, mView::getDataSuc));
    }
}
