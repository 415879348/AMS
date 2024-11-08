package com.continental.sdk.activity.record;

import com.continental.sdk.base.IBasePresenter;
import com.continental.sdk.base.IBaseView;
import com.continental.sdk.bean.response.ApplyVo;

public class RecordContract {
    interface View extends IBaseView {
        void getAttendanceDataSuccess(ApplyVo it);
        void getAmendDataSuccess(ApplyVo it);
        void onRepairRevocationSuccess(boolean isTrue);
        void getLeaveDataSuccess(ApplyVo it);
        void onLeaveRevocationSuccess(boolean isTrue);
        void getOvertimeDataSuccess(ApplyVo it);
        void onOtRevocationSuccess(boolean isTrue);
    }

    interface Presenter extends IBasePresenter {
        
        void getAttendanceData(long startTime,
                               long endTime,
                               int page,
                               int limit,
                               String deptIds,
                               String userIds,
                               int isOverTemperature);
        
        void getAmendData(long startTime,
                          long endTime,
                          int page,
                          int limit,
                          String userIds);

        void repairOrdinaryFlowRevocation(Long applyId);

        void getLeaveData(long startTime,
                          long endTime,
                          int page,
                          int limit,
                          String userIds);

        void leaveOrdinaryFlowRevocation(Long applyId);

        void getOvertimeData(long startTime,
                             long endTime,
                             int page,
                             int limit,
                             String userIds);

        void oTOrdinaryFlowRevocation(Long applyId);
    }

    interface Model {
        void method();
    }
}
