package com.continental.sdk.activity.apply.amend;

import com.continental.sdk.base.BaseObserver;
import com.continental.sdk.base.BasePresenter;
import com.continental.sdk.http.HttpFunction;
import com.continental.sdk.http.HttpService;
import com.continental.sdk.rxjava.SchedulerUtils;

public class AmendPresenter extends BasePresenter<IAmendContract.IView> implements IAmendContract.IPresenter {

    public AmendPresenter(IAmendContract.IView mView) {
        super(mView);
    }

    @Override
    public void getData() {

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
