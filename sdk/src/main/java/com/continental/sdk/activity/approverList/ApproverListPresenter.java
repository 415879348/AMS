package com.continental.sdk.activity.approverList;

import com.continental.sdk.base.BaseObserver;
import com.continental.sdk.base.BasePresenter;
import com.continental.sdk.bean.response.StaffVo;
import com.continental.sdk.http.HttpFunction;
import com.continental.sdk.http.HttpService;
import com.continental.sdk.rxjava.SchedulerUtils;
import com.continental.sdk.utils.CollectionTool;

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
