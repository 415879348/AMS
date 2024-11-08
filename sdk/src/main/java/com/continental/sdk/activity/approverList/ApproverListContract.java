package com.continental.sdk.activity.approverList;

import com.continental.sdk.base.IBasePresenter;
import com.continental.sdk.base.IBaseView;
import com.continental.sdk.bean.response.StaffVo;

import java.util.List;

public class ApproverListContract {
    interface View extends IBaseView {
        void getUserListSuccess(List<StaffVo> it);
    }

    interface Presenter extends IBasePresenter {
        void userList(List<StaffVo> currentList);
    }

    interface Model {
        void method();
    }
}
