package com.esharp.ams.ui.fragment;

import android.util.Pair;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.esharp.ams.R;
import com.esharp.ams.adapter.AssetsRecordAdapter;
import com.esharp.ams.contract.AssetsContract;
import com.esharp.ams.contract.HomeContract;
import com.esharp.ams.contract.MainActContract;
import com.esharp.ams.presenter.AssetsPresenter;
import com.esharp.ams.ui.AssetDetailActivity;
import com.esharp.ams.ui.CreateAssetActivity;
import com.esharp.sdk.base.BaseMvpFragment;
import com.esharp.sdk.bean.response.DeviceBean;
import com.esharp.sdk.bean.response.DeviceVo;
import com.esharp.sdk.widget.MyTextView;
import com.esharp.sdk.widget.SPIconTextView;

import androidx.recyclerview.widget.RecyclerView;

public class AssetsFragment extends BaseMvpFragment<AssetsContract.Presenter, MainActContract.IHost> implements AssetsContract.View {

    @Override
    protected Pair<Integer, AssetsContract.Presenter> layoutAndPresenter() {
        return Pair.create(R.layout.fragment_assets, new AssetsPresenter(this));
    }

    private RecyclerView mRecyclerView;

    private SPIconTextView itv_create;

    private MyTextView mtv_date_selector;

    private AssetsRecordAdapter mAssetsRecordAdapter;

    @Override
    protected void init(View view) {
        itv_create = view.findViewById(R.id.itv_create);
        mRecyclerView = view.findViewById(R.id.recyclerView);

        itv_create.setOnClickListener(v -> {
            CreateAssetActivity.startActivity(mContext);
        });

        mRecyclerView.setAdapter(mAssetsRecordAdapter = new AssetsRecordAdapter(item -> {
            AssetDetailActivity.startActivity(mContext, item);
        }));

        mPresenter.getData(1, 20);
    }

    @Override
    public void getDataSuc(DeviceVo it) {
        LogUtils.json(it);
        mAssetsRecordAdapter.insertLastItems(it.getRecords());
    }

}
