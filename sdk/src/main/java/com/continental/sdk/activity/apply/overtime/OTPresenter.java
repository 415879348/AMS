package com.continental.sdk.activity.apply.overtime;

import com.continental.sdk.base.BaseObserver;
import com.continental.sdk.base.BasePresenter;
import com.continental.sdk.http.HttpFunction;
import com.continental.sdk.http.HttpService;
import com.continental.sdk.rxjava.SchedulerUtils;

public class OTPresenter extends BasePresenter<OTContract.View>  implements OTContract.Presenter {

    public OTPresenter(OTContract.View mView) {
        super(mView);
    }

    @Override
    public void getOTRecordDetail(long applyId) {
        HttpService.get()
                .otOrdinaryFlowDetailsAll(applyId)
                .map(new HttpFunction<>())
                .compose(SchedulerUtils.io_main_single())
                .subscribe(new BaseObserver<>(mView, mView::getOTRecordDetailSuccess));
    }

}
