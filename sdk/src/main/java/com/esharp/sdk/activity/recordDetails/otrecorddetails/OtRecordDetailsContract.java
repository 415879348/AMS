package com.esharp.sdk.activity.recordDetails.otrecorddetails;

import com.esharp.sdk.base.IBasePresenter;
import com.esharp.sdk.base.IBaseView;
import com.esharp.sdk.bean.response.RecordDetailVo;

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
