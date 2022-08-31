package com.esharp.ams.ui.fragment;

import android.util.Pair;
import android.view.View;
import com.blankj.utilcode.util.LogUtils;
import com.esharp.ams.R;
import com.esharp.ams.adapter.AlertRecordAdapter;
import com.esharp.ams.contract.AlertContract;
import com.esharp.ams.contract.HomeContract;
import com.esharp.ams.eventbus.EventAlert;
import com.esharp.ams.presenter.AlertPresenter;
import com.esharp.sdk.base.BaseMvpFragment;
import com.esharp.sdk.bean.response.AssetAlertVo;
import com.esharp.sdk.widget.swipy.SwipyRefreshLayout;
import com.esharp.sdk.widget.swipy.SwipyRefreshLayoutDirection;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import androidx.recyclerview.widget.RecyclerView;

public class AlertFragment extends BaseMvpFragment<AlertContract.Presenter, HomeContract.IHost> implements AlertContract.View {

    @Override
    protected Pair<Integer, AlertContract.Presenter> layoutAndPresenter() {
        return Pair.create(R.layout.fragment_alert, new AlertPresenter(this));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    private SwipyRefreshLayout refreshLayout;

    private RecyclerView mRecyclerView;

    private AlertRecordAdapter mAlertRecordAdapter;

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

        mRecyclerView.setAdapter(mAlertRecordAdapter = new AlertRecordAdapter(vo -> {
            if (vo.getStatus() == 0) {
                mPresenter.deviceAlertLogProcess(vo.getId());
            }
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

    @Override
    public void deviceAlertLogProcessSuc(boolean it) {
        initData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventAlert it) {
        initData();
    }

}
