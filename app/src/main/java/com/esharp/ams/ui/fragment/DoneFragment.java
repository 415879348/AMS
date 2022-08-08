package com.esharp.ams.ui.fragment;

import android.util.Pair;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.esharp.ams.R;
import com.esharp.ams.adapter.DoneRecordAdapter;
import com.esharp.ams.contract.DoneContract;
import com.esharp.ams.contract.HomeContract;
import com.esharp.ams.presenter.DonePresenter;
import com.esharp.ams.ui.WorkOrderDetailActivity;
import com.esharp.sdk.base.BaseMvpFragment;
import com.esharp.sdk.bean.response.WorkOrderVo;
import com.esharp.sdk.widget.MyTextView;

import androidx.recyclerview.widget.RecyclerView;

public class DoneFragment extends BaseMvpFragment<DoneContract.Presenter, HomeContract.IHost> implements DoneContract.View {

    @Override
    protected Pair<Integer, DoneContract.Presenter> layoutAndPresenter() {
        return Pair.create(R.layout.fragment_done, new DonePresenter(this));
    }

    private RecyclerView mRecyclerView;

    private MyTextView mtv_date_selector;

    private DoneRecordAdapter mDoneRecordAdapter;

    @Override
    protected void init(View view) {
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setAdapter(mDoneRecordAdapter = new DoneRecordAdapter(vo -> {
            WorkOrderDetailActivity.startActivity(mContext, vo);
        }));

        mPresenter.getData(1, 20, 0);
    }

    @Override
    public void getDataSuc(WorkOrderVo it) {
        LogUtils.json(it);
        mDoneRecordAdapter.insertLastItems(it.getRecords());
    }
}
