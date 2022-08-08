package com.esharp.sdk.activity.approve;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;

import com.blankj.utilcode.util.LogUtils;
import com.esharp.sdk.R;
import com.esharp.sdk.base.BaseMvpActivity;
import com.esharp.sdk.bean.response.RepairDetailVo;

public class ApproveActivity extends BaseMvpActivity<ApproveContract.Presenter> implements ApproveContract.View {

    @Override
    protected Pair<Integer, ApproveContract.Presenter> layoutAndPresenter() {
        return Pair.create(R.layout.spsdk_activity_approve_leave, new ApprovePresenter(this));
    }

    @Override
    protected void init() {
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        if (bundle.containsKey("applyId")) {
            String applyId = bundle.getString("applyId");
            LogUtils.json(applyId);
            mPresenter.repairDetails("applyId");
        }


    }

    @Override
    public void getRepairDetailsSuccess(RepairDetailVo it) {
        LogUtils.json(it);
    }

    public static void startActivity(Context context, String applyId) {
        Intent intent = new Intent(context, ApproveActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString("applyId", applyId);

        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
