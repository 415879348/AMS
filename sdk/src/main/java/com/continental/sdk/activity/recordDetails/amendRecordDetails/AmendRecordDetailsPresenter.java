package com.continental.sdk.activity.recordDetails.amendRecordDetails;

import com.continental.sdk.base.BaseObserver;
import com.continental.sdk.base.BasePresenter;
import com.continental.sdk.http.HttpFunction;
import com.continental.sdk.http.HttpService;
import com.continental.sdk.rxjava.SchedulerUtils;

public class AmendRecordDetailsPresenter extends BasePresenter<AmendRecordDetailsContract.View> implements AmendRecordDetailsContract.Presenter {
    public AmendRecordDetailsPresenter(AmendRecordDetailsContract.View mView) {
        super(mView);
    }

    @Override
    public void getAmendRecordDetail(long applyId) {
        HttpService.get()
                .repairOrdinaryFlowDetailsAll(applyId)
                .map(new HttpFunction<>())
                .compose(SchedulerUtils.io_main_single())
                .subscribe(new BaseObserver<>(mView, mView::getAmendRecordDetailSuccess));
    }
}
