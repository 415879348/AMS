package com.esharp.sdk.activity.login;

import com.esharp.sdk.base.IBasePresenter;
import com.esharp.sdk.base.IBaseView;
import com.esharp.sdk.bean.response.Token;

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
