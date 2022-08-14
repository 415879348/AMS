package com.esharp.ams.ui.fragment;

import android.util.Pair;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.esharp.ams.R;
import com.esharp.ams.adapter.AssetsRecordAdapter;
import com.esharp.ams.contract.AssetsContract;
import com.esharp.ams.contract.HomeContract;
import com.esharp.ams.contract.MainActContract;
import com.esharp.ams.presenter.AssetsPresenter;
import com.esharp.ams.ui.AssetDetailActivity;
import com.esharp.ams.ui.CreateAssetActivity;
import com.esharp.sdk.base.BaseMvpFragment;
import com.esharp.sdk.bean.request.IDListVo;
import com.esharp.sdk.bean.response.DeviceBean;
import com.esharp.sdk.bean.response.DeviceVo;
import com.esharp.sdk.widget.MyTextView;
import com.esharp.sdk.widget.SPIconTextView;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class AssetsFragment extends BaseMvpFragment<AssetsContract.Presenter, MainActContract.IHost> implements AssetsContract.View {

    @Override
    protected Pair<Integer, AssetsContract.Presenter> layoutAndPresenter() {
        return Pair.create(R.layout.fragment_assets, new AssetsPresenter(this));
    }

    private RecyclerView mRecyclerView;

    private SPIconTextView itv_create, itv_edit, itv_delete;

    private MyTextView mtv_date_selector;

    private AssetsRecordAdapter mAssetsRecordAdapter;

    @Override
    protected void init(View view) {
        itv_create = view.findViewById(R.id.itv_create);
        itv_edit = view.findViewById(R.id.itv_edit);
        itv_delete = view.findViewById(R.id.itv_delete);

        itv_delete.setEnable(false);

        mRecyclerView = view.findViewById(R.id.recyclerView);

        itv_create.setOnClickListener(v -> {
            CreateAssetActivity.startActivity(mContext);
        });

        itv_delete.setOnClickListener(v -> {
            List<DeviceBean> list = mAssetsRecordAdapter.getData();

            ArrayList<String> ids = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).isChecked()) {
                    ids.add(list.get(i).getId());
                }
            }
            if (ids.size() > 0) {
                IDListVo vo = new IDListVo();
                vo.setIds(ids);
                mPresenter.deleteDevice(vo);
            }
        });

        mRecyclerView.setAdapter(mAssetsRecordAdapter = new AssetsRecordAdapter(new AssetsRecordAdapter.Listener(){
            @Override
            public void onClick(DeviceBean it) {
                AssetDetailActivity.startActivity(mContext, it);
            }

            @Override
            public void setChecked(Boolean it) {
                List<DeviceBean> list = mAssetsRecordAdapter.getData();
                List<DeviceBean> tempList = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).isChecked()) {
                        tempList.add(list.get(i));
                    }
                }

            }
        }));
        mPresenter.getData(1, 20);
    }

    @Override
    public void getDataSuc(DeviceVo it) {
        LogUtils.json(it);
        mAssetsRecordAdapter.insertLastItems(it.getRecords());
    }

    @Override
    public void deleteDeviceSuc(boolean it) {
        LogUtils.json(it);
        mPresenter.getData(1, 20);
    }

}
