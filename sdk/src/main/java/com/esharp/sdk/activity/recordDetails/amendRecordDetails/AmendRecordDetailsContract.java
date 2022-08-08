package com.esharp.sdk.activity.recordDetails.amendRecordDetails;

import com.esharp.sdk.base.IBasePresenter;
import com.esharp.sdk.base.IBaseView;
import com.esharp.sdk.bean.response.RecordDetailVo;

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
