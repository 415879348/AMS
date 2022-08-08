package com.esharp.ams.contract;

import com.esharp.sdk.base.IBasePresenter;
import com.esharp.sdk.base.IHostView;

public interface MainActContract {

    interface IHost extends IHostView {
        void onBackPressed();
        void onItemClick();
    }

//    interface View extends IHost {
//        void method();
//    }

    interface Presenter extends IBasePresenter {
        void method();
    }
}
