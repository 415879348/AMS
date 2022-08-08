package com.esharp.sdk.activity.changePassword;

import com.esharp.sdk.base.IBasePresenter;
import com.esharp.sdk.base.IBaseView;

public class ChangePasswordContract {
    interface View extends IBaseView {
        void onChangePasswordSuccess(boolean isTrue);
    }

    interface Presenter extends IBasePresenter {
        void changePassword(String password, String newPassword, String confirmPassword);
    }

    interface Model {
        void method();
    }
}
