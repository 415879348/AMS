package com.esharp.sdk.activity.record;

import com.blankj.utilcode.util.LogUtils;
import com.esharp.sdk.base.BaseObserver;
import com.esharp.sdk.base.BasePresenter;
import com.esharp.sdk.http.HttpFunction;
import com.esharp.sdk.http.HttpService;
import com.esharp.sdk.rxjava.SchedulerUtils;
import com.esharp.sdk.utils.BuildMap;

import java.util.Map;

public class RecordPresenter extends BasePresenter<RecordContract.View> implements RecordContract.Presenter {

    public RecordPresenter(RecordContract.View mView) {
        super(mView);
    }

    @Override
    public void getAttendanceData(long startTime,
                                  long endTime,
                                  int page,
                                  int limit,
                                  String deptIds,
                                  String userIds,
                                  int isOverTemperature) {
        Map<String, String> map = null;

        if (isOverTemperature == -1) {
            map = BuildMap.init()
                    .put("startTime", startTime+"")
                    .put("endTime", endTime+"")
                    .put("page", page+"")
                    .put("limit", limit+"")
                    .put("deptIds", deptIds)
                    .build();
        } else {
            map = BuildMap.init()
                    .put("startTime", startTime+"")
                    .put("endTime", endTime+"")
                    .put("page", page+"")
                    .put("limit", limit+"")
                    .put("deptIds", deptIds)
                    .put("isOverTemperature", isOverTemperature+"")
                    .build();
        }

        LogUtils.json(map);

        HttpService.get()
//                .attendancePaging(startTime, endTime, page, limit, deptIds, userIds, isOverTemperature)
                .attendancePaging(map)
                .map(new HttpFunction<>())
                .compose(SchedulerUtils.io_main_single())
                .subscribe(new BaseObserver<>(mView, mView::getAttendanceDataSuccess));
    }

    @Override
    public void getAmendData(long startTime,
                             long endTime,
                             int page,
                             int limit,
                             String userIds) {

        HttpService.get()
                .repairPaging(startTime, endTime, page, limit, userIds)
                .map(new HttpFunction<>())
                .compose(SchedulerUtils.io_main_single())
                .subscribe(new BaseObserver<>(mView, mView::getAmendDataSuccess));
    }

    @Override
    public void repairOrdinaryFlowRevocation(Long applyId) {
        HttpService.get()
                .repairOrdinaryFlowRevocation(applyId)
                .map(new HttpFunction<>())
                .compose(SchedulerUtils.io_main_single())
                .subscribe(new BaseObserver<>(mView, mView::onRepairRevocationSuccess));
    }

    @Override
    public void getLeaveData(long startTime,
                             long endTime,
                             int page,
                             int limit,
                             String userIds) {

        HttpService.get()
                .leavePaging(startTime, endTime, page, limit, userIds)
                .map(new HttpFunction<>())
                .compose(SchedulerUtils.io_main_single())
                .subscribe(new BaseObserver<>(mView, mView::getLeaveDataSuccess));
    }

    @Override
    public void leaveOrdinaryFlowRevocation(Long applyId) {
        HttpService.get()
                .leaveOrdinaryFlowRevocation(applyId)
                .map(new HttpFunction<>())
                .compose(SchedulerUtils.io_main_single())
                .subscribe(new BaseObserver<>(mView, mView::onRepairRevocationSuccess));
    }

    @Override
    public void getOvertimeData(long startTime,
                                 long endTime,
                                 int page,
                                 int limit,
                                 String userIds) {

        HttpService.get()
                .otPaging(startTime, endTime, page, limit, userIds)
                .map(new HttpFunction<>())
                .compose(SchedulerUtils.io_main_single())
                .subscribe(new BaseObserver<>(mView, mView::getOvertimeDataSuccess));
    }

    @Override
    public void oTOrdinaryFlowRevocation(Long applyId) {
        HttpService.get()
                .oTOrdinaryFlowRevocation(applyId)
                .map(new HttpFunction<>())
                .compose(SchedulerUtils.io_main_single())
                .subscribe(new BaseObserver<>(mView, mView::onRepairRevocationSuccess));
    }
}
