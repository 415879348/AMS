package com.esharp.ams.presenter;

import com.esharp.ams.contract.AssetEditContract;
import com.esharp.sdk.base.BaseObserver;
import com.esharp.sdk.base.BasePresenter;
import com.esharp.sdk.bean.response.DeviceInfoForm;
import com.esharp.sdk.http.HttpService;
import com.esharp.sdk.rxjava.HttpResultOperator;
import com.esharp.sdk.rxjava.ProgressOperator;
import com.esharp.sdk.rxjava.SchedulerUtils;

import java.util.HashMap;
import java.util.Map;

public class AssetEditPresenter extends BasePresenter<AssetEditContract.View> implements AssetEditContract.Presenter {
    public AssetEditPresenter(AssetEditContract.View mView) {
        super(mView);
    }

    @Override
    public void getData(String deviceId) {

//        HttpService.get()
//                .setDefaultCard(cardId)
//                .lift(new HttpResultOperator<>())
//                .flatMap(is -> HttpService.get().getPaymentCards())
//                .lift(new HttpResultOperator<>())
//                .compose(SchedulerUtils.io_main_single())
//                .lift(new ProgressOperator<>(mView))
//                .subscribe(new BaseObserver<>(mView, mView::showPaymentCards));


        HttpService.get().queryAppDevice(deviceId)
                .lift(new HttpResultOperator<>())
                .compose(SchedulerUtils.io_main_single())
                .lift(new ProgressOperator<>(mView, -1))
                .subscribe(new BaseObserver<>(mView, mView::getDataSuc));
    }

    @Override
    public void updateDevice(DeviceInfoForm it) {
        HttpService.get().updateAppDevice(it)
                .lift(new HttpResultOperator<>())
                .compose(SchedulerUtils.io_main_single())
                .lift(new ProgressOperator<>(mView, -1))
                .subscribe(new BaseObserver<>(mView, mView::updateDeviceSuc));
    }


    @Override
    public void assetType() {
        // 资产类型
        Map<String, String> map = new HashMap<>();
        map.put("dictType", "2");
        HttpService.get().dictAll(map)
                .lift(new HttpResultOperator<>())
                .compose(SchedulerUtils.io_main_single())
                .lift(new ProgressOperator<>(mView, -1))
                .subscribe(new BaseObserver<>(mView, mView::assetTypeSuc));
    }

    @Override
    public void assetBrand() {
        // 资产品牌
        Map<String, String> map = new HashMap<>();
        map.put("dictType", "0");
        HttpService.get().dictAll(map)
                .lift(new HttpResultOperator<>())
                .compose(SchedulerUtils.io_main_single())
                .lift(new ProgressOperator<>(mView, -1))
                .subscribe(new BaseObserver<>(mView, mView::assetBrandSuc));
    }

    @Override
    public void assetModel(String brandID) {
        // 资产型号
        Map<String, String> map = new HashMap<>();
        map.put("dictType", "1");
        map.put("brandID", brandID);
        HttpService.get().dictAll(map)
                .lift(new HttpResultOperator<>())
                .compose(SchedulerUtils.io_main_single())
                .lift(new ProgressOperator<>(mView, -1))
                .subscribe(new BaseObserver<>(mView, mView::assetModelSuc));
    }


}
