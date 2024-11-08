package com.continental.sdk.activity.recordDetails.otrecorddetails;

import com.continental.sdk.base.IBasePresenter;
import com.continental.sdk.base.IBaseView;
import com.continental.sdk.bean.response.RecordDetailVo;

public class OtRecordDetailsContract {
    interface View extends IBaseView {
        void getOtRecordDetailsAllSuss(RecordDetailVo it);
    }

    interface Presenter extends IBasePresenter {
        void getOtRecordDetailsAll(long applyId);
    }

    interface Model {
        void method();
    }
}
