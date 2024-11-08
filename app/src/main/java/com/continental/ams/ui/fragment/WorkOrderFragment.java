package com.continental.ams.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.blankj.utilcode.util.LogUtils;
import com.continental.ams.R;
import com.continental.ams.adapter.WorkOrderRecordAdapter;
import com.continental.ams.contract.HomeContract;
import com.continental.ams.contract.WorkOrderContract;
import com.continental.ams.dialog.FilterWorkOrderDialog;
import com.continental.ams.eventbus.EventBacklog;
import com.continental.ams.eventbus.EventWorkOrder;
import com.continental.ams.presenter.WorkOrderPresenter;
import com.continental.ams.ui.MainActivity;
import com.continental.ams.ui.WorkOrderCreateActivity;
import com.continental.ams.ui.WorkOrderDetailActivity;
import com.continental.sdk.Constant;
import com.continental.sdk.base.BaseMvpFragment;
import com.continental.sdk.bean.response.WorkOrderVo;
import com.continental.sdk.utils.ClickUtil;
import com.continental.sdk.widget.SPIconTextView;
import com.continental.sdk.widget.SPShowTextView;
import com.continental.sdk.widget.swipy.SwipyRefreshLayout;
import com.continental.sdk.widget.swipy.SwipyRefreshLayoutDirection;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.HashMap;
import java.util.Map;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.recyclerview.widget.RecyclerView;

public class WorkOrderFragment extends BaseMvpFragment<WorkOrderContract.Presenter, HomeContract.IHost> implements WorkOrderContract.View {

    @Override
    protected Pair<Integer, WorkOrderContract.Presenter> layoutAndPresenter() {
        return Pair.create(R.layout.fragment_work_order, new WorkOrderPresenter(this));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    private SPIconTextView itv_create, itv_filter;

    private SPShowTextView stv_data_count;

    private SwipyRefreshLayout refreshLayout;

    private RecyclerView mRecyclerView;

    private WorkOrderRecordAdapter mWorkOrderRecordAdapter;

    int current = 1;

    Map<String, String> map = new HashMap<>();

    private final ActivityResultLauncher<Intent> mLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        Intent intent;
        if (result.getResultCode() == Activity.RESULT_OK && (intent = result.getData()) != null && (intent.getStringExtra(Constant.REFRESH_DATA)) != null) {
            initData();
            EventBus.getDefault().post(new EventBacklog());
        }
    });

    @Override
    protected void init(View view) {
        EventBus.getDefault().register(this);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        itv_create = view.findViewById(R.id.itv_create);
        itv_filter = view.findViewById(R.id.itv_filter);
        stv_data_count = view.findViewById(R.id.stv_data_count);
        mRecyclerView = view.findViewById(R.id.recyclerView);

        itv_create.setOnClickListener(v -> {
            if (ClickUtil.isFastDoubleClick()) {
                return;
            }
            WorkOrderCreateActivity.startActivity(mContext, mLauncher);
        });

        itv_filter.setOnClickListener(v -> {
            if (ClickUtil.isFastDoubleClick()) {
                return;
            }
            FilterWorkOrderDialog dialog = new FilterWorkOrderDialog((MainActivity)mContext);
            dialog.setOnClickListener(
                    vo -> {
                        map = vo;
                        initData();
                    }
            );
            WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
            params.x = 10;
            params.y = 100;
            dialog.getWindow().setAttributes(params);
            dialog.getWindow().setGravity(Gravity.TOP);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.show();
        });

        refreshLayout.setOnRefreshListener(direction -> {
            if (direction == SwipyRefreshLayoutDirection.TOP) {
                initData();
            } else {
                mPresenter.getData(++current, map);
            }
        });

        mRecyclerView.setAdapter(mWorkOrderRecordAdapter = new WorkOrderRecordAdapter((it -> {
            WorkOrderDetailActivity.startActivity(mContext, it);
        })));

        initData();
    }

    private void initData() {
        mWorkOrderRecordAdapter.clearItems();
        LogUtils.json(map);
        current = 1;
        mPresenter.getData(current, map);
    }

    @Override
    public void refreshData(WorkOrderVo it) {
        LogUtils.json(it);
        current = it.getCurrent();
        mWorkOrderRecordAdapter.refreshAllItems(it.getRecords());
        stv_data_count.setDetail(it.getTotal()+"");
    }

    @Override
    public void appendData(WorkOrderVo it) {
        LogUtils.json(it);
        current = it.getCurrent();
        mWorkOrderRecordAdapter.insertLastItems(it.getRecords());
    }

    @Override
    public void refreshFinish() {
        if (refreshLayout != null) refreshLayout.setRefreshing(false);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventWorkOrder it) {
        initData();
    }
}
