package com.continental.sdk.activity.changePassword;

import com.continental.sdk.base.IBasePresenter;
import com.continental.sdk.base.IBaseView;

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
