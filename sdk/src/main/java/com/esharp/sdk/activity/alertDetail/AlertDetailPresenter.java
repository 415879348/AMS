package com.esharp.sdk.activity.alertDetail;

import com.esharp.sdk.base.BaseObserver;
import com.esharp.sdk.base.BasePresenter;
import com.esharp.sdk.http.HttpFunction;
import com.esharp.sdk.http.HttpService;
import com.esharp.sdk.rxjava.SchedulerUtils;

public class AlertDetailPresenter extends BasePresenter<AlertDetailContract.View> implements AlertDetailContract.Presenter {

    public AlertDetailPresenter(AlertDetailContract.View mView) {
        super(mView);
    }

    @Override
    public void getAlertDetailById(long id) {
        HttpService.get()
                .getAlertDetailById("3622")
                .map(new HttpFunction<>())
                .compose(SchedulerUtils.io_main_single())
                .subscribe(new BaseObserver<>(mView, mView::onGetAlertDetailSuccess));
    }
}
