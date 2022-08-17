package com.esharp.ams.presenter;

import com.esharp.ams.contract.AssetsContract;
import com.esharp.sdk.Constant;
import com.esharp.sdk.base.BaseObserver;
import com.esharp.sdk.base.BasePresenter;
import com.esharp.sdk.bean.request.IDListVo;
import com.esharp.sdk.http.HttpService;
import com.esharp.sdk.rxjava.HttpResultOperator;
import com.esharp.sdk.rxjava.ProgressOperator;
import com.esharp.sdk.rxjava.SchedulerUtils;
import java.util.Map;

public class AssetsPresenter extends BasePresenter<AssetsContract.View> implements AssetsContract.Presenter {

    public AssetsPresenter(AssetsContract.View mView) {
        super(mView);
    }

    @Override
    public void getData(int current) {
        HttpService.get().device(current, Constant.SIZE)
                .lift(new HttpResultOperator<>())
                .compose(SchedulerUtils.io_main_single())
                .lift(new ProgressOperator<>(mView, -1))
                .subscribe(new BaseObserver<>(mView, current == 1 ? mView::refreshData : mView::appendData));
    }

    @Override
    public void getData(int current, Map<String, String> map) {
        map.put(Constant.KEY_CURRENT, current+"");
        map.put(Constant.KEY_SIZE, Constant.SIZE+"");
        HttpService.get().device(map)
                .lift(new HttpResultOperator<>())
                .compose(SchedulerUtils.io_main_single())
                .doFinally(mView::refreshFinish)
                .subscribe(new BaseObserver<>(mView, current == 1 ? mView::refreshData : mView::appendData));
    }

    @Override
    public void deleteDevice(IDListVo ids) {
        HttpService.get().deleteAppDevice(ids)
                .lift(new HttpResultOperator<>())
                .compose(SchedulerUtils.io_main_single())
                .lift(new ProgressOperator<>(mView, -1))
                .subscribe(new BaseObserver<>(mView, mView::deleteDeviceSuc));
    }


}
