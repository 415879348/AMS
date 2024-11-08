package com.continental.ams.contract;

import com.continental.sdk.base.IBasePresenter;
import com.continental.sdk.base.IBaseView;
import com.continental.sdk.bean.response.AssetAlertVo;

public interface AlertContract {
    interface View extends IBaseView {
        void refreshData(AssetAlertVo it);

        void appendData(AssetAlertVo it);

        void refreshFinish();

        void deviceAlertLogProcessSuc(boolean it);
    }

    interface Presenter extends IBasePresenter {
        void getData(int current);

        void deviceAlertLogProcess(String id);
    }
}
