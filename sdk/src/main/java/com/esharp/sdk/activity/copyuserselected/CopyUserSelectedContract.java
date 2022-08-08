package com.esharp.sdk.activity.copyuserselected;

import com.esharp.sdk.base.IBasePresenter;
import com.esharp.sdk.base.IBaseView;

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
