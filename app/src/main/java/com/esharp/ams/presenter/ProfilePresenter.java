package com.esharp.ams.presenter;

import com.esharp.ams.contract.ProfileContract;
import com.esharp.sdk.base.BaseObserver;
import com.esharp.sdk.base.BasePresenter;
import com.esharp.sdk.http.HttpService;
import com.esharp.sdk.rxjava.HttpResultOperator;
import com.esharp.sdk.rxjava.ProgressOperator;
import com.esharp.sdk.rxjava.SchedulerUtils;

public class ProfilePresenter extends BasePresenter<ProfileContract.View> implements ProfileContract.Presenter {

    public ProfilePresenter(ProfileContract.View mView) {
        super(mView);
    }

    @Override
    public void auth() {
        HttpService.get().auth()
                .lift(new HttpResultOperator<>())
                .compose(SchedulerUtils.io_main_single())
                .lift(new ProgressOperator<>(mView, -1))
                .subscribe(new BaseObserver<>(mView, mView::authSus));
    }

    @Override
    public void jpTest() {
        HttpService.get().jpTest()
                .lift(new HttpResultOperator<>())
                .compose(SchedulerUtils.io_main_single())
                .lift(new ProgressOperator<>(mView, -1))
                .subscribe(new BaseObserver<>(mView, mView::jpTest));
    }

    @Override
    public void logout() {
        HttpService.get().logout()
                .lift(new HttpResultOperator<>())
                .compose(SchedulerUtils.io_main_single())
                .lift(new ProgressOperator<>(mView, -1))
                .subscribe(new BaseObserver<>(mView, mView::onLogoutSuccess));
    }
}