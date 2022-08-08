package com.esharp.ams.ui.fragment;

import android.util.Pair;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.esharp.ams.R;
import com.esharp.ams.adapter.WorkOrderRecordAdapter;
import com.esharp.ams.contract.HomeContract;
import com.esharp.ams.contract.WorkOrderContract;
import com.esharp.ams.presenter.WorkOrderPresenter;
import com.esharp.ams.ui.WorkOrderDetailActivity;
import com.esharp.sdk.base.BaseMvpFragment;
import com.esharp.sdk.bean.response.WorkOrderVo;
import com.esharp.sdk.widget.MyTextView;

import androidx.recyclerview.widget.RecyclerView;

public class WorkOrderFragment extends BaseMvpFragment<WorkOrderContract.Presenter, HomeContract.IHost> implements WorkOrderContract.View {

    @Override
    protected Pair<Integer, WorkOrderContract.Presenter> layoutAndPresenter() {
        return Pair.create(R.layout.fragment_work_order, new WorkOrderPresenter(this));
    }

    private RecyclerView mRecyclerView;

    private MyTextView mtv_date_selector;

    private WorkOrderRecordAdapter mWorkOrderRecordAdapter;

    @Override
    protected void init(View view) {
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setAdapter(mWorkOrderRecordAdapter = new WorkOrderRecordAdapter((it -> {
            WorkOrderDetailActivity.startActivity(mContext, it);
        })));

        mPresenter.getData(1, 20);
    }

    @Override
    public void getDataSuc(WorkOrderVo it) {
        LogUtils.json(it);
        mWorkOrderRecordAdapter.insertLastItems(it.getRecords());
    }
}
