package com.esharp.sdk.activity.alertDetail;

import com.esharp.sdk.base.IBasePresenter;
import com.esharp.sdk.base.IBaseView;
import com.esharp.sdk.bean.response.AlertDetailVo;

public class AlertDetailContract {
    interface View extends IBaseView {
        void onGetAlertDetailSuccess(AlertDetailVo it);
    }

    interface Presenter extends IBasePresenter {
        void getAlertDetailById(long id);
    }

    interface Model {
        void method();
    }
}
