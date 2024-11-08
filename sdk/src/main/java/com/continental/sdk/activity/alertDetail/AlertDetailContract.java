package com.continental.sdk.activity.alertDetail;

import com.continental.sdk.base.IBasePresenter;
import com.continental.sdk.base.IBaseView;
import com.continental.sdk.bean.response.AlertDetailVo;

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
