package com.esharp.ams.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.blankj.utilcode.util.ToastUtils;
import com.esharp.ams.contract.MainActContract;
import com.esharp.sdk.base.BaseObserver;
import com.esharp.sdk.base.BasePresenter;
import com.esharp.sdk.bean.request.JPRegisterVo;
import com.esharp.sdk.http.HttpFunction;
import com.esharp.sdk.http.HttpService;
import com.esharp.sdk.rxjava.HttpResultOperator;
import com.esharp.sdk.rxjava.ProgressOperator;
import com.esharp.sdk.rxjava.SchedulerUtils;

import cn.jpush.android.api.JPushInterface;

public class MainActPresenter extends BasePresenter<MainActContract.IHost> implements MainActContract.Presenter {

    public MainActPresenter(MainActContract.IHost mView) {
        super(mView);
    }

    @Override
    public void jpRegisterId(Context context) {
        if (! TextUtils.isEmpty(JPushInterface.getRegistrationID(context))) {
            JPRegisterVo vo = new JPRegisterVo();
            vo.setRegisterId(JPushInterface.getRegistrationID(context));
            HttpService.get().jpRegisterId(vo)
                    .map(new HttpFunction<>())
                    .compose(SchedulerUtils.io_main_single())
                    .lift(new ProgressOperator<>(mView, -1))
                    .subscribe(new BaseObserver<>(mView, mView::jpRegisterIdSuc));
        }
    }

}
