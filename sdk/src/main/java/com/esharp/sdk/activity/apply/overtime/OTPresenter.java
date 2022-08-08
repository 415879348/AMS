package com.esharp.sdk.activity.apply.overtime;

import com.esharp.sdk.base.BaseObserver;
import com.esharp.sdk.base.BasePresenter;
import com.esharp.sdk.http.HttpFunction;
import com.esharp.sdk.http.HttpService;
import com.esharp.sdk.rxjava.SchedulerUtils;

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
