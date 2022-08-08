package com.esharp.sdk.activity.apply.amend;

import com.esharp.sdk.activity.apply.IApplyView;
import com.esharp.sdk.base.IBasePresenter;
import com.esharp.sdk.bean.response.RecordDetailVo;

public interface IAmendContract {

    interface IView extends IApplyView {
        void getAmendRecordDetailSuccess(RecordDetailVo it);
    }

    interface IPresenter extends IBasePresenter {
        void getData();
        void getAmendRecordDetail(long applyId);
    }
}
