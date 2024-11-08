package com.continental.ams.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.blankj.utilcode.util.ToastUtils;
import com.continental.ams.contract.MainActContract;
import com.continental.sdk.base.BaseObserver;
import com.continental.sdk.base.BasePresenter;
import com.continental.sdk.bean.request.JPRegisterVo;
import com.continental.sdk.http.HttpFunction;
import com.continental.sdk.http.HttpService;
import com.continental.sdk.rxjava.HttpResultOperator;
import com.continental.sdk.rxjava.ProgressOperator;
import com.continental.sdk.rxjava.SchedulerUtils;

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
