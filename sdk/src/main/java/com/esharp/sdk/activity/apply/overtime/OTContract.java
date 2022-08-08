package com.esharp.sdk.activity.apply.overtime;

import com.esharp.sdk.activity.apply.IApplyView;
import com.esharp.sdk.base.IBasePresenter;
import com.esharp.sdk.bean.response.RecordDetailVo;

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
