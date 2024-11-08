package com.continental.sdk.activity.changePassword;

import com.continental.sdk.base.BasePresenter;
import com.continental.sdk.bean.request.ChangePasswordVo;

public class ChangePasswordPresenter extends BasePresenter<ChangePasswordContract.View> implements ChangePasswordContract.Presenter {

    public ChangePasswordPresenter(ChangePasswordContract.View mView) {
        super(mView);
    }


    @Override
    public void changePassword(String password, String newPassword, String confirmPassword) {

        ChangePasswordVo changePasswordVo = new ChangePasswordVo();
        changePasswordVo.setPassword(password);
        changePasswordVo.setNewPassword(newPassword);
        changePasswordVo.setConfirmPassword(confirmPassword);


    }
}
