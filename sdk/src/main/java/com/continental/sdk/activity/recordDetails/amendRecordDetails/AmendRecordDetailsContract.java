package com.continental.sdk.activity.recordDetails.amendRecordDetails;

import com.continental.sdk.base.IBasePresenter;
import com.continental.sdk.base.IBaseView;
import com.continental.sdk.bean.response.RecordDetailVo;

public class AmendRecordDetailsContract {
    interface View extends IBaseView {
        void getAmendRecordDetailSuccess(RecordDetailVo it);
    }

    interface Presenter extends IBasePresenter {
        void getAmendRecordDetail(long applyId);
    }

    interface Model {
        void method();
    }

}
