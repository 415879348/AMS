package com.continental.sdk.activity.alertDetail;

import com.continental.sdk.base.BaseObserver;
import com.continental.sdk.base.BasePresenter;
import com.continental.sdk.http.HttpFunction;
import com.continental.sdk.http.HttpService;
import com.continental.sdk.rxjava.SchedulerUtils;

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
