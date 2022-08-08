package com.esharp.sdk.activity.approverSelected;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.esharp.sdk.Constant;
import com.esharp.sdk.R;
import com.esharp.sdk.base.BaseMvpActivity;
import com.esharp.sdk.bean.response.ApprovalUserBean;
import com.esharp.sdk.widget.swipy.SwipyRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ApproverSelectedActivity extends BaseMvpActivity<ApproverSelectedContract.Presenter> implements ApproverSelectedContract.View {

    @Override
    protected Pair<Integer, ApproverSelectedContract.Presenter> layoutAndPresenter() {
        return Pair.create(R.layout.spsdk_activity_container, new ApproverSelectedPresenter(this));
    }

    List<ApprovalUserBean> approvalUserList;

    private SwipyRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private ApproverSelectedAdapter mAdapter;

    public static void startActivity(Context context, ArrayList<ApprovalUserBean> list) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.DATA, list);
        Intent intent = new Intent(context, ApproverSelectedActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public void method() {

    }

    @Override
    protected void init() {
        Bundle bundle = getIntent().getExtras();

        mRefreshLayout = findViewById(R.id.refreshLayout);
        mRecyclerView = findViewById(R.id.recyclerView);

        mRecyclerView.setAdapter(mAdapter = new ApproverSelectedAdapter(item -> {
            LogUtils.json(item);
        }));

        if (ObjectUtils.isNotEmpty(bundle.getSerializable(Constant.DATA))) {
            approvalUserList = (List<ApprovalUserBean>) bundle.getSerializable(Constant.DATA);
            LogUtils.json(approvalUserList);
            mAdapter.insertItem(approvalUserList, 0);
        }
    }
}
