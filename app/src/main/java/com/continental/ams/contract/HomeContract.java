package com.continental.ams.contract;

import com.continental.sdk.base.IBasePresenter;
import com.continental.sdk.base.IBaseView;
import com.continental.sdk.base.IHostView;

public interface HomeContract {
    interface IHost extends IHostView {
        void onItemClick();
    }

    interface View extends IBaseView {
        void workOrderCountProcess(Integer it);
        void workOrderCountOver(Integer it);
        void deviceAlertLogCountSuc(Integer it);
    }

    interface Presenter extends IBasePresenter {
        void method();
        void workOrderCountProcess();
        void workOrderCountOver();
        void deviceAlertLogCount(int status);
    }

}
