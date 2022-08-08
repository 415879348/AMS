package com.esharp.sdk.activity.recordDetails.otrecorddetails;

import com.esharp.sdk.base.BaseObserver;
import com.esharp.sdk.base.BasePresenter;
import com.esharp.sdk.http.HttpFunction;
import com.esharp.sdk.http.HttpService;
import com.esharp.sdk.rxjava.SchedulerUtils;

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
