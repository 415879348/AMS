package com.esharp.ams.contract;

import com.esharp.sdk.base.IBasePresenter;
import com.esharp.sdk.base.IHostView;
import com.esharp.sdk.bean.request.LoginVo;

public interface LoginActContract {

    interface View extends IHostView {
        void onLoginSuc(String token);
    }

    interface Presenter extends IBasePresenter {
        void login(LoginVo it);
    }

}
