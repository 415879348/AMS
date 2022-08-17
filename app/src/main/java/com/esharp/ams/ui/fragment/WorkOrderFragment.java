package com.esharp.ams.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.blankj.utilcode.util.LogUtils;
import com.esharp.ams.R;
import com.esharp.ams.adapter.WorkOrderRecordAdapter;
import com.esharp.ams.contract.HomeContract;
import com.esharp.ams.contract.WorkOrderContract;
import com.esharp.ams.dialog.FilterAssetDialog;
import com.esharp.ams.dialog.FilterWorkOrderDialog;
import com.esharp.ams.dialog.WorkOrderHandleDialog;
import com.esharp.ams.presenter.WorkOrderPresenter;
import com.esharp.ams.ui.CreateAssetActivity;
import com.esharp.ams.ui.MainActivity;
import com.esharp.ams.ui.WorkOrderCreateActivity;
import com.esharp.ams.ui.WorkOrderDetailActivity;
import com.esharp.sdk.Constant;
import com.esharp.sdk.base.BaseMvpFragment;
import com.esharp.sdk.bean.response.WorkOrderVo;
import com.esharp.sdk.widget.MyTextView;
import com.esharp.sdk.widget.SPIconTextView;
import com.esharp.sdk.widget.SPShowTextView;
import com.esharp.sdk.widget.swipy.SwipyRefreshLayout;
import com.esharp.sdk.widget.swipy.SwipyRefreshLayoutDirection;

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
        }
    });

    @Override
    protected void init(View view) {
        refreshLayout = view.findViewById(R.id.refreshLayout);
        itv_create = view.findViewById(R.id.itv_create);
        itv_filter = view.findViewById(R.id.itv_filter);
        stv_data_count = view.findViewById(R.id.stv_data_count);
        mRecyclerView = view.findViewById(R.id.recyclerView);

        itv_create.setOnClickListener(v -> {
            WorkOrderCreateActivity.startActivity(mContext, mLauncher);
        });

        itv_filter.setOnClickListener(v -> {
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
}
