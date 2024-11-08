package com.continental.ams.ui.fragment;

import android.util.Pair;
import android.view.View;
import com.blankj.utilcode.util.LogUtils;
import com.continental.ams.R;
import com.continental.ams.adapter.DoneRecordAdapter;
import com.continental.ams.contract.DoneContract;
import com.continental.ams.contract.HomeContract;
import com.continental.ams.presenter.DonePresenter;
import com.continental.ams.ui.WorkOrderDetailActivity;
import com.continental.sdk.base.BaseMvpFragment;
import com.continental.sdk.bean.response.WorkOrderVo;
import com.continental.sdk.widget.swipy.SwipyRefreshLayout;
import com.continental.sdk.widget.swipy.SwipyRefreshLayoutDirection;
import androidx.recyclerview.widget.RecyclerView;

public class DoneFragment extends BaseMvpFragment<DoneContract.Presenter, HomeContract.IHost> implements DoneContract.View {

    @Override
    protected Pair<Integer, DoneContract.Presenter> layoutAndPresenter() {
        return Pair.create(R.layout.fragment_done, new DonePresenter(this));
    }

    private SwipyRefreshLayout refreshLayout;

    private RecyclerView mRecyclerView;

    private DoneRecordAdapter mDoneRecordAdapter;

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

        mRecyclerView.setAdapter(mDoneRecordAdapter = new DoneRecordAdapter(vo -> {
            WorkOrderDetailActivity.startActivity(mContext, vo);
        }));

        initData();
    }

    @Override
    public void refreshData(WorkOrderVo it) {
        LogUtils.json(it);
        current = it.getCurrent();
        mDoneRecordAdapter.refreshAllItems(it.getRecords());
    }

    @Override
    public void appendData(WorkOrderVo it) {
        LogUtils.json(it);
        current = it.getCurrent();
        mDoneRecordAdapter.insertLastItems(it.getRecords());
    }

    private void initData() {
        mDoneRecordAdapter.clearItems();
        current = 1;
        mPresenter.getData(current);
    }

    @Override
    public void refreshFinish() {
        if (refreshLayout != null) refreshLayout.setRefreshing(false);
    }
}
