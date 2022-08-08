package com.esharp.sdk.activity.recordDetails.amendRecordDetails;

import com.esharp.sdk.base.BaseObserver;
import com.esharp.sdk.base.BasePresenter;
import com.esharp.sdk.http.HttpFunction;
import com.esharp.sdk.http.HttpService;
import com.esharp.sdk.rxjava.SchedulerUtils;

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
