package com.esharp.ams.ui.fragment;

import android.util.Pair;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.esharp.ams.R;
import com.esharp.ams.adapter.AlertRecordAdapter;
import com.esharp.ams.adapter.DoneRecordAdapter;
import com.esharp.ams.contract.AlertContract;
import com.esharp.ams.contract.DoneContract;
import com.esharp.ams.contract.HomeContract;
import com.esharp.ams.presenter.AlertPresenter;
import com.esharp.ams.presenter.DonePresenter;
import com.esharp.ams.ui.WorkOrderDetailActivity;
import com.esharp.sdk.base.BaseMvpFragment;
import com.esharp.sdk.bean.response.AssetAlertBean;
import com.esharp.sdk.bean.response.AssetAlertVo;
import com.esharp.sdk.bean.response.WorkOrderVo;
import com.esharp.sdk.widget.swipy.SwipyRefreshLayout;
import com.esharp.sdk.widget.swipy.SwipyRefreshLayoutDirection;

import androidx.recyclerview.widget.RecyclerView;

public class AlertFragment extends BaseMvpFragment<AlertContract.Presenter, HomeContract.IHost> implements AlertContract.View {

    @Override
    protected Pair<Integer, AlertContract.Presenter> layoutAndPresenter() {
        return Pair.create(R.layout.fragment_alert, new AlertPresenter(this));
    }

    private SwipyRefreshLayout refreshLayout;

    private RecyclerView mRecyclerView;

    private AlertRecordAdapter mAlertRecordAdapter;

    int current = 1;

    @Override
    protected void init(View view) {
        refreshLayout = view.findViewById(R.id.refreshLayout);
        mRecyclerView = view.findViewById(R.id.recyclerView);

        refreshLayout.setOnRefreshListener(direction -> {
            if (direction == SwipyRefreshLayoutDirection.TOP) {
                initData();
            } else {
                mPresenter.getData(++current);
            }
        });

        mRecyclerView.setAdapter(mAlertRecordAdapter = new AlertRecordAdapter(vo -> {

        }));

        initData();
    }

    @Override
    public void refreshData(AssetAlertVo it) {
        LogUtils.json(it);
        current = it.getCurrent();
        mAlertRecordAdapter.refreshAllItems(it.getRecords());
    }

    @Override
    public void appendData(AssetAlertVo it) {
        LogUtils.json(it);
        current = it.getCurrent();
        mAlertRecordAdapter.insertLastItems(it.getRecords());
    }

    private void initData() {
        mAlertRecordAdapter.clearItems();
        current = 1;
        mPresenter.getData(current);
    }

    @Override
    public void refreshFinish() {
        if (refreshLayout != null) refreshLayout.setRefreshing(false);
    }
}
