package com.continental.ams.presenter;

import com.continental.ams.contract.LoginActContract;
import com.continental.sdk.base.BaseObserver;
import com.continental.sdk.base.BasePresenter;
import com.continental.sdk.bean.request.LoginVo;
import com.continental.sdk.http.HttpService;
import com.continental.sdk.rxjava.HttpResultOperator;
import com.continental.sdk.rxjava.ProgressOperator;
import com.continental.sdk.rxjava.SchedulerUtils;

public class LoginActPresenter extends BasePresenter<LoginActContract.View> implements LoginActContract.Presenter {

    public LoginActPresenter(LoginActContract.View mView) {
        super(mView);
    }

    @Override
    public void login(LoginVo it) {
        HttpService.get().login(it)
                .lift(new HttpResultOperator<>())
                .compose(SchedulerUtils.io_main_single())
                .lift(new ProgressOperator<>(mView, -1))
                .subscribe(new BaseObserver<>(mView, mView::onLoginSuc));
    }

}
