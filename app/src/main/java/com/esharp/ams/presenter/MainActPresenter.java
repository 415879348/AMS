package com.esharp.ams.presenter;

import com.esharp.ams.contract.MainActContract;
import com.esharp.sdk.base.BasePresenter;

public class MainActPresenter extends BasePresenter<MainActContract.IHost> implements MainActContract.Presenter {

    public MainActPresenter(MainActContract.IHost mView) {
        super(mView);
    }

    @Override
    public void method() {
//        HttpService.get().login(new LoginVo("super", "123456"))
//                .map(new HttpFunction<>())
//                .compose(SchedulerUtils.io_main_single())
//                .subscribe(new BaseObserver<>(mView, mView::onLoginSuccess));
    }


}
