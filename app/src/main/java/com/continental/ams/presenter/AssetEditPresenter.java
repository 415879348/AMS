package com.continental.ams.presenter;

import android.util.Pair;

import com.continental.ams.contract.AssetEditContract;
import com.continental.ams.ui.CreateAssetActivity;
import com.continental.sdk.base.BaseObserver;
import com.continental.sdk.base.BasePresenter;
import com.continental.sdk.bean.request.FileVo;
import com.continental.sdk.bean.request.Files;
import com.continental.sdk.bean.response.DeviceInfoForm;
import com.continental.sdk.http.HttpService;
import com.continental.sdk.rxjava.HttpResultOperator;
import com.continental.sdk.rxjava.ProgressOperator;
import com.continental.sdk.rxjava.SchedulerUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.core.Single;

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
    public void updateDevice(DeviceInfoForm it) {
        HttpService.get().updateAppDevice(it)
                .lift(new HttpResultOperator<>())
                .compose(SchedulerUtils.io_main_single())
                .lift(new ProgressOperator<>(mView, -1))
                .subscribe(new BaseObserver<>(mView, mView::updateDeviceSuc));
    }

    @Override
    public void deviceAll() {
        HttpService.get().appDeviceAll()
                .lift(new HttpResultOperator<>())
                .compose(SchedulerUtils.io_main_single())
                .lift(new ProgressOperator<>(mView, -1))
                .subscribe(new BaseObserver<>(mView, mView::deviceAllSuc));
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

    @Override
    public void uploadPhoto(List<String> photos, Files files) {
        HttpService.get().documentMore(files)
                .lift(new HttpResultOperator<>())
                .map(strings -> Pair.create(photos, strings))
                .compose(SchedulerUtils.io_main_single())
                .lift(new ProgressOperator<>(mView, -1))
                .subscribe(new BaseObserver<>(mView, mView::uploadPhotoSus));
    }

    @Override
    public void deviceFieldValue(Map<String, String> params) {
        HttpService.get().deviceFieldValue(params)
                .lift(new HttpResultOperator<>())
                .compose(SchedulerUtils.io_main_single())
                .lift(new ProgressOperator<>(mView, -1))
                .subscribe(new BaseObserver<>(mView, mView::deviceFieldSuc));
    }

}
