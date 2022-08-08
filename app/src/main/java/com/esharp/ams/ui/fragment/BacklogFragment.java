package com.esharp.ams.ui.fragment;

import android.util.Pair;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.esharp.ams.R;
import com.esharp.ams.adapter.BacklogRecordAdapter;
import com.esharp.ams.contract.BacklogContract;
import com.esharp.ams.contract.MainActContract;
import com.esharp.ams.presenter.BacklogPresenter;
import com.esharp.ams.ui.WorkOrderDetailActivity;
import com.esharp.sdk.base.BaseMvpFragment;
import com.esharp.sdk.bean.response.WorkOrderVo;
import com.esharp.sdk.widget.MyTextView;

import androidx.recyclerview.widget.RecyclerView;

public class BacklogFragment extends BaseMvpFragment<BacklogContract.Presenter, MainActContract.IHost> implements BacklogContract.View {

    @Override
    protected Pair<Integer, BacklogContract.Presenter> layoutAndPresenter() {
        return Pair.create(R.layout.fragment_backlog, new BacklogPresenter(this));
    }

    private RecyclerView mRecyclerView;

    private MyTextView mtv_date_selector;

    private BacklogRecordAdapter mBacklogRecordAdapter;

    @Override
    protected void init(View view) {
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setAdapter(mBacklogRecordAdapter = new BacklogRecordAdapter(item -> {
            WorkOrderDetailActivity.startActivity(getActivity(), item, WorkOrderDetailActivity.HANDLE);
            mHostView.onItemClick();
        }));
        mPresenter.getData(1, 20);
    }

    @Override
    public void getDataSuc(WorkOrderVo it) {
        LogUtils.json(it);
        mBacklogRecordAdapter.insertLastItems(it.getRecords());
    }

}
