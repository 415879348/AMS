package com.continental.ams.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.util.Pair;
import android.view.View;
import com.continental.ams.R;
import com.blankj.utilcode.util.LogUtils;
import com.continental.ams.adapter.BacklogRecordAdapter;
import com.continental.ams.contract.BacklogContract;
import com.continental.ams.contract.MainActContract;
import com.continental.ams.eventbus.EventBacklog;
import com.continental.ams.presenter.BacklogPresenter;
import com.continental.ams.ui.WorkOrderDetailActivity;
import com.continental.sdk.Constant;
import com.continental.sdk.base.BaseMvpFragment;
import com.continental.sdk.bean.response.WorkOrderVo;
import com.continental.sdk.widget.swipy.SwipyRefreshLayout;
import com.continental.sdk.widget.swipy.SwipyRefreshLayoutDirection;
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
