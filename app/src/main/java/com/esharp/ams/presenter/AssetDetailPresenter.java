package com.esharp.ams.presenter;

import com.esharp.ams.contract.AssetDetailContract;
import com.esharp.sdk.base.BaseObserver;
import com.esharp.sdk.base.BasePresenter;
import com.esharp.sdk.http.HttpService;
import com.esharp.sdk.rxjava.HttpResultOperator;
import com.esharp.sdk.rxjava.ProgressOperator;
import com.esharp.sdk.rxjava.SchedulerUtils;

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
