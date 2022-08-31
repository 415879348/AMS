package com.esharp.ams.contract;

import com.esharp.sdk.base.IBasePresenter;
import com.esharp.sdk.base.IBaseView;
import com.esharp.sdk.bean.response.AssetAlertVo;

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
