package com.esharp.ams.presenter;

import com.esharp.ams.contract.AssetEditContract;
import com.esharp.sdk.base.BaseObserver;
import com.esharp.sdk.base.BasePresenter;
import com.esharp.sdk.bean.response.DeviceInfoForm;
import com.esharp.sdk.http.HttpService;
import com.esharp.sdk.rxjava.HttpResultOperator;
import com.esharp.sdk.rxjava.ProgressOperator;
import com.esharp.sdk.rxjava.SchedulerUtils;

public class AssetEditPresenter extends BasePresenter<AssetEditContract.View> implements AssetEditContract.Presenter {
    public AssetEditPresenter(AssetEditContract.View mView) {
        super(mView);
    }

    @Override
    public void getData(String deviceId) {
        HttpService.get().queryAppDevice(deviceId)
                .lift(new HttpResultOperator<>())
                .compose(SchedulerUtils.io_main_single())
                .lift(new ProgressOperator<>(mView, -1))
                .subscribe(new BaseObserver<>(mView, mView::getDataSuc));
    }

    @Override
    public void updateAppDevice(DeviceInfoForm it) {
        //        HttpService.get().dictAll(dictType)
//                .lift(new HttpResultOperator<>())
//                .compose(SchedulerUtils.io_main_single())
//                .lift(new ProgressOperator<>(mView, -1))
//                .subscribe(new BaseObserver<>(mView, mView::dictAllSuc));
    }
//
//    @Override
//    public void dictAssetType() {
//
//        HttpService.get().dictAll(0)
//                .lift(new HttpResultOperator<>())
//                .compose(SchedulerUtils.io_main_single())
//                .lift(new ProgressOperator<>(mView, -1))
//                .subscribe(new BaseObserver<>(mView, mView::dictAllSuc));
//    }
//
//    @Override
//    public void dictAssetType(int dictType) {
//
//        HttpService.get().dictAll(dictType)
//                .lift(new HttpResultOperator<>())
//                .compose(SchedulerUtils.io_main_single())
//                .lift(new ProgressOperator<>(mView, -1))
//                .subscribe(new BaseObserver<>(mView, mView::dictAllSuc));
//    }

}
