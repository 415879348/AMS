package com.esharp.sdk.activity.approverList;

import com.esharp.sdk.base.IBasePresenter;
import com.esharp.sdk.base.IBaseView;
import com.esharp.sdk.bean.response.StaffVo;

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
