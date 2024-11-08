package com.continental.sdk.activity.recordDetails;

import com.continental.sdk.base.IBasePresenter;
import com.continental.sdk.base.IHostView;
import com.continental.sdk.bean.response.RecordDetailVo;

public interface RecordDetailsContract {

    interface IHost extends IHostView {
        void onBackPressed();
        void getAmendRecordDetailSuccess(RecordDetailVo it);
        void getLeaveRecordDetailAllSuccess(RecordDetailVo it);
        void getOtRecordDetailsAllSuss(RecordDetailVo it);
    }

    interface View extends IHost {

    }


    interface Presenter extends IBasePresenter {

    }

    interface Model {
        void method();
    }
}
