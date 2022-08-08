package com.esharp.sdk.activity.approverList;

import com.esharp.sdk.base.BaseObserver;
import com.esharp.sdk.base.BasePresenter;
import com.esharp.sdk.bean.response.StaffVo;
import com.esharp.sdk.http.HttpFunction;
import com.esharp.sdk.http.HttpService;
import com.esharp.sdk.rxjava.SchedulerUtils;
import com.esharp.sdk.utils.CollectionTool;

import java.util.List;

public class ApproverListPresenter extends BasePresenter<ApproverListContract.View> implements ApproverListContract.Presenter {

    public ApproverListPresenter(ApproverListContract.View mView) {
        super(mView);
    }

    @Override
    public void userList(List<StaffVo> currentList) {
        HttpService.get()
                .userList()
                .map(new HttpFunction<>())
                .map(it -> CollectionTool.deDuplication(it, currentList))
                .compose(SchedulerUtils.io_main_single())
                .subscribe(new BaseObserver<>(mView, mView::getUserListSuccess));
    }

}
