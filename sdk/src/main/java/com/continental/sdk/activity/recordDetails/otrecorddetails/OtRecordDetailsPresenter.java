package com.continental.sdk.activity.recordDetails.otrecorddetails;

import com.continental.sdk.base.BaseObserver;
import com.continental.sdk.base.BasePresenter;
import com.continental.sdk.http.HttpFunction;
import com.continental.sdk.http.HttpService;
import com.continental.sdk.rxjava.SchedulerUtils;

public class OtRecordDetailsPresenter extends BasePresenter<OtRecordDetailsContract.View> implements OtRecordDetailsContract.Presenter {
    public OtRecordDetailsPresenter(OtRecordDetailsContract.View mView) {
        super(mView);
    }

    @Override
    public void getOtRecordDetailsAll(long applyId) {
        HttpService.get()
                .otOrdinaryFlowDetailsAll(applyId)
                .map(new HttpFunction<>())
                .compose(SchedulerUtils.io_main_single())
                .subscribe(new BaseObserver<>(mView, mView::getOtRecordDetailsAllSuss));
    }
}
