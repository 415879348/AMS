package com.esharp.ams.presenter;

import android.util.Pair;

import com.esharp.ams.contract.WorkOrderNodeEditContract;
import com.esharp.sdk.base.BaseObserver;
import com.esharp.sdk.base.BasePresenter;
import com.esharp.sdk.bean.request.Files;
import com.esharp.sdk.bean.response.HandlerVo;
import com.esharp.sdk.http.HttpService;
import com.esharp.sdk.rxjava.HttpResultOperator;
import com.esharp.sdk.rxjava.ProgressOperator;
import com.esharp.sdk.rxjava.SchedulerUtils;

import java.util.List;

public class WorkOrderNodeEditPresenter extends BasePresenter<WorkOrderNodeEditContract.View> implements WorkOrderNodeEditContract.Presenter {

    public WorkOrderNodeEditPresenter(WorkOrderNodeEditContract.View mView) {
        super(mView);
    }

    @Override
    public void nodeSearch(Long nodeId) {
        HttpService.get().nodeSearch(nodeId)
                .lift(new HttpResultOperator<>())
                .compose(SchedulerUtils.io_main_single())
                .lift(new ProgressOperator<>(mView, -1))
                .subscribe(new BaseObserver<>(mView, mView::nodeSearchSuc));
    }

    @Override
    public void nodeUpdate(HandlerVo vo) {
        HttpService.get().nodeUpdate(vo)
                .lift(new HttpResultOperator<>())
                .compose(SchedulerUtils.io_main_single())
                .lift(new ProgressOperator<>(mView, -1))
                .subscribe(new BaseObserver<>(mView, mView::nodeUpdateSuc));
    }


    @Override
    public void uploadPhoto(List<String> photos, Files files) {
        HttpService.get().documentMore(files)
                .lift(new HttpResultOperator<>())
                .map(strings -> Pair.create(photos, strings))
                .compose(SchedulerUtils.io_main_single())
                .lift(new ProgressOperator<>(mView, -1))
                .subscribe(new BaseObserver<>(mView, mView::uploadPhotoSus));
    }
}