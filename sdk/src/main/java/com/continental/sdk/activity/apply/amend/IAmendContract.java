package com.continental.sdk.activity.apply.amend;

import com.continental.sdk.activity.apply.IApplyView;
import com.continental.sdk.base.IBasePresenter;
import com.continental.sdk.bean.response.RecordDetailVo;

public interface IAmendContract {

    interface IView extends IApplyView {
        void getAmendRecordDetailSuccess(RecordDetailVo it);
    }

    interface IPresenter extends IBasePresenter {
        void getData();
        void getAmendRecordDetail(long applyId);
    }
}
