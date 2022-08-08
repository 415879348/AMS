package com.esharp.ams.contract;

import com.esharp.sdk.base.IBasePresenter;
import com.esharp.sdk.base.IBaseView;
import com.esharp.sdk.base.IHostView;

public interface HomeContract {
    interface IHost extends IHostView {
        void onItemClick();
    }

    interface View extends IBaseView {
        void workOrderCountProcess(Integer it);
        void workOrderCountOver(Integer it);
    }

    interface Presenter extends IBasePresenter {
        void method();
        void workOrderCountProcess();
        void workOrderCountOver();
    }

}
