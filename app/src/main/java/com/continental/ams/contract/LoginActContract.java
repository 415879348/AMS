package com.continental.ams.contract;

import com.continental.sdk.base.IBasePresenter;
import com.continental.sdk.base.IBaseView;
import com.continental.sdk.bean.request.LoginVo;

public interface LoginActContract {

    interface View extends IBaseView {
        void onLoginSuc(String token);
    }

    interface Presenter extends IBasePresenter {
        void login(LoginVo it);
    }

}
