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

public class CreateAssetPresenter extends BasePresenter<CreateAssetContract.IHost> implements CreateAssetContract.Presenter {

    public CreateAssetPresenter(CreateAssetContract.IHost mView) {
        super(mView);
    }

    @Override
    public void addAppDevice(DeviceInfoForm it) {
        HttpService.get().addAppDevice(it)
                .lift(new HttpResultOperator<>())
                .compose(SchedulerUtils.io_main_single())
                .lift(new ProgressOperator<>(mView, -1))
                .subscribe(new BaseObserver<>(mView, mView::addAppDevice));
    }
}
