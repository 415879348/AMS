package com.continental.sdk.activity.copyuserselected;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.continental.sdk.Constant;
import com.continental.sdk.R;
import com.continental.sdk.base.BaseMvpActivity;
import com.continental.sdk.bean.response.CopyUserListBean;
import com.continental.sdk.widget.swipy.SwipyRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CopyUserSelectedActivity extends BaseMvpActivity<CopyUserSelectedContract.Presenter> implements CopyUserSelectedContract.View {
    @Override
    protected Pair<Integer, CopyUserSelectedContract.Presenter> layoutAndPresenter() {
        return Pair.create(R.layout.spsdk_activity_container, new CopyUserSelectedPresenter(this));
    }

    List<CopyUserListBean> approvalUserList;

    private SwipyRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private CopyUserSelectedAdapter mAdapter;

    public static void startActivity(Context context, ArrayList<CopyUserListBean> list) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.DATA, list);
        Intent intent = new Intent(context, CopyUserSelectedActivity.class);
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


        GridLayoutManager layoutManager = new GridLayoutManager(this,6);


        mRecyclerView.setAdapter(mAdapter = new CopyUserSelectedAdapter(item -> {
            LogUtils.json(item);
        }));
        mRecyclerView.setLayoutManager(layoutManager);
        if (ObjectUtils.isNotEmpty(bundle.getSerializable(Constant.DATA))) {
            approvalUserList = (List<CopyUserListBean>) bundle.getSerializable(Constant.DATA);
            LogUtils.json(approvalUserList);
            mAdapter.insertItem(approvalUserList, 0);
        }
    }
}
