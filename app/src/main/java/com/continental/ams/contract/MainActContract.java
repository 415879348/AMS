package com.continental.ams.contract;

import android.content.Context;

import com.continental.sdk.base.IBasePresenter;
import com.continental.sdk.base.IHostView;

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
