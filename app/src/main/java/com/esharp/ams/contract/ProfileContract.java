package com.esharp.ams.contract;

import com.esharp.sdk.base.IBasePresenter;
import com.esharp.sdk.base.IBaseView;
import com.esharp.sdk.bean.response.AssetAlertBean;
import com.esharp.sdk.bean.response.UserVo;

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
