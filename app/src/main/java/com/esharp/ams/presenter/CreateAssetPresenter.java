package com.esharp.ams.presenter;

import com.esharp.ams.contract.BacklogContract;
import com.esharp.ams.contract.CreateAssetContract;
import com.esharp.sdk.base.BaseObserver;
import com.esharp.sdk.base.BasePresenter;
import com.esharp.sdk.bean.response.DeviceInfoForm;
import com.esharp.sdk.http.HttpService;
import com.esharp.sdk.rxjava.HttpResultOperator;
import com.esharp.sdk.rxjava.ProgressOperator;
import com.esharp.sdk.rxjava.SchedulerUtils;

import java.util.HashMap;
import java.util.Map;

public class CreateAssetPresenter extends BasePresenter<CreateAssetContract.View> implements CreateAssetContract.Presenter {

    public CreateAssetPresenter(CreateAssetContract.View mView) {
        super(mView);
    }

    @Override
    public void addAppDevice(DeviceInfoForm it) {
        HttpService.get().addAppDevice(it)
                .lift(new HttpResultOperator<>())
                .compose(SchedulerUtils.io_main_single())
                .lift(new ProgressOperator<>(mView, -1))
                .subscribe(new BaseObserver<>(mView, mView::addAppDeviceSuc));
    }

    @Override
    public void generateCode() {
        HttpService.get().generateCode()
                .lift(new HttpResultOperator<>())
                .compose(SchedulerUtils.io_main_single())
                .lift(new ProgressOperator<>(mView, -1))
                .subscribe(new BaseObserver<>(mView, mView::generateCodeSuc));
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
