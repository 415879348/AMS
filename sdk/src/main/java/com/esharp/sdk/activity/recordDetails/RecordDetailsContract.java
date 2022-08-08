package com.esharp.sdk.activity.recordDetails;

import com.esharp.sdk.base.IBasePresenter;
import com.esharp.sdk.base.IHostView;
import com.esharp.sdk.bean.response.RecordDetailVo;

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
