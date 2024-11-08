package com.continental.ams.contract;

import com.continental.sdk.base.IBasePresenter;
import com.continental.sdk.base.IBaseView;
import com.continental.sdk.bean.response.AssetAlertBean;
import com.continental.sdk.bean.response.UserVo;

public interface ProfileContract {
    interface View extends IBaseView {
        void authSus(UserVo it);

        void jpTest(AssetAlertBean it);

        void onLogoutSuccess(Boolean it);
    }

    interface Presenter extends IBasePresenter {
        void auth();

        void jpTest();

        void logout();
    }

}
