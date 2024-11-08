package com.continental.sdk.activity.apply.overtime;

import com.continental.sdk.activity.apply.IApplyView;
import com.continental.sdk.base.IBasePresenter;
import com.continental.sdk.bean.response.RecordDetailVo;

public interface OTContract {
    interface View extends IApplyView {
        void getOTRecordDetailSuccess(RecordDetailVo it);
    }

    interface Presenter extends IBasePresenter {
        void getOTRecordDetail(long applyId);
    }

    interface Model {
        void method();
    }
}
