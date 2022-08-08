package com.esharp.ams.presenter;

import com.esharp.ams.contract.LoginActContract;
import com.esharp.sdk.base.BaseObserver;
import com.esharp.sdk.base.BasePresenter;
import com.esharp.sdk.bean.request.LoginVo;
import com.esharp.sdk.http.HttpService;
import com.esharp.sdk.rxjava.HttpResultOperator;
import com.esharp.sdk.rxjava.ProgressOperator;
import com.esharp.sdk.rxjava.SchedulerUtils;

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
