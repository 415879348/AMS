package com.esharp.ams.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.util.Pair;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.esharp.ams.R;
import com.esharp.ams.adapter.BacklogRecordAdapter;
import com.esharp.ams.contract.BacklogContract;
import com.esharp.ams.contract.MainActContract;
import com.esharp.ams.eventbus.EventBacklog;
import com.esharp.ams.presenter.BacklogPresenter;
import com.esharp.ams.ui.WorkOrderDetailActivity;
import com.esharp.sdk.Constant;
import com.esharp.sdk.base.BaseMvpFragment;
import com.esharp.sdk.bean.response.WorkOrderVo;
import com.esharp.sdk.widget.swipy.SwipyRefreshLayout;
import com.esharp.sdk.widget.swipy.SwipyRefreshLayoutDirection;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.recyclerview.widget.RecyclerView;

public class BacklogFragment extends BaseMvpFragment<BacklogContract.Presenter, MainActContract.IHost> implements BacklogContract.View {

    @Override
    protected Pair<Integer, BacklogContract.Presenter> layoutAndPresenter() {
        return Pair.create(R.layout.fragment_backlog, new BacklogPresenter(this));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    private RecyclerView mRecyclerView;

    private BacklogRecordAdapter mBacklogRecordAdapter;

    private SwipyRefreshLayout refreshLayout;

    private final ActivityResultLauncher<Intent> mLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        Intent intent;
        if (result.getResultCode() == Activity.RESULT_OK && (intent = result.getData()) != null && (intent.getStringExtra(Constant.REFRESH_DATA)) != null) {
            initData();
        }
    });

    int current = 1;

    @Override
    protected void init(View view) {
        EventBus.getDefault().register(this);

        refreshLayout = view.findViewById(R.id.refreshLayout);
        mRecyclerView = view.findViewById(R.id.recyclerView);

        refreshLayout.setOnRefreshListener(direction -> {
            if (direction == SwipyRefreshLayoutDirection.TOP) {
                initData();
            } else {
                mPresenter.getData(++current);
            }
        });
        mRecyclerView.setAdapter(mBacklogRecordAdapter = new BacklogRecordAdapter(item -> {
            WorkOrderDetailActivity.startActivity(getActivity(), item, WorkOrderDetailActivity.HANDLE, mLauncher);
            mHostView.onItemClick();
        }));
        initData();
    }

    private void initData() {
        mBacklogRecordAdapter.clearItems();
        current = 1;
        LogUtils.json(current);
        mPresenter.getData(current);
    }

    @Override
    public void refreshData(WorkOrderVo it) {
        LogUtils.json(it);
        current = it.getCurrent();
        mBacklogRecordAdapter.refreshAllItems(it.getRecords());
    }

    @Override
    public void appendData(WorkOrderVo it) {
        LogUtils.json(it);
        current = it.getCurrent();
        mBacklogRecordAdapter.insertLastItems(it.getRecords());
    }

    @Override
    public void refreshFinish() {
        if (refreshLayout != null) refreshLayout.setRefreshing(false);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventBacklog it) {
        initData();
    }

}
