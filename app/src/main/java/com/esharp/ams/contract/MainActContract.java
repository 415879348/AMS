package com.esharp.ams.contract;

import android.content.Context;

import com.esharp.sdk.base.IBasePresenter;
import com.esharp.sdk.base.IHostView;

public interface MainActContract {

    interface IHost extends IHostView {
        void onBackPressed();
        void onItemClick();
        void jpRegisterIdSuc(Object it);
    }

    interface Presenter extends IBasePresenter {
        void jpRegisterId(Context context);
    }
}
