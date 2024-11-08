package com.continental.sdk.activity.login;

import com.continental.sdk.base.BasePresenter;

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    public LoginPresenter(LoginContract.View mView) {
        super(mView);
    }

    @Override
    public void login(String account, String password) {
//        HttpService.get().login(account, password)
//                .map(new HttpFunction<>())
//                .compose(SchedulerUtils.io_main_single())
//                .subscribe(new BaseObserver<>(mView, mView::onLoginSuccess));
    }
}
