package com.continental.sdk.activity.copyuserselected;

import com.continental.sdk.base.IBasePresenter;
import com.continental.sdk.base.IBaseView;

public class CopyUserSelectedContract {
    interface View extends IBaseView {
        void method();
    }

    interface Presenter extends IBasePresenter {
        void method();
    }

    interface Model {
        void method();
    }
}
