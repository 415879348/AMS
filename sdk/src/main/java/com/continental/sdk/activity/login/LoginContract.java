package com.continental.sdk.activity.login;

import com.continental.sdk.base.IBasePresenter;
import com.continental.sdk.base.IBaseView;
import com.continental.sdk.bean.response.Token;

public class LoginContract {
    interface View extends IBaseView {
        void onLoginSuccess(Token it);
    }

    interface Presenter extends IBasePresenter {
        void login(String account, String password);
    }

    interface Model {
        void method();
    }
}
