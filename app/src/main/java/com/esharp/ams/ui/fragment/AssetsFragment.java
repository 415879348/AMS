package com.esharp.ams.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.media.DrmInitData;
import android.util.Pair;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.esharp.ams.R;
import com.esharp.ams.adapter.AssetsRecordAdapter;
import com.esharp.ams.adapter.WorkOrderRecordAdapter;
import com.esharp.ams.contract.AssetsContract;
import com.esharp.ams.contract.HomeContract;
import com.esharp.ams.contract.MainActContract;
import com.esharp.ams.dialog.FilterAssetDialog;
import com.esharp.ams.dialog.WorkOrderHandleDialog;
import com.esharp.ams.presenter.AssetsPresenter;
import com.esharp.ams.ui.AssetDetailActivity;
import com.esharp.ams.ui.AssetEditActivity;
import com.esharp.ams.ui.CreateAssetActivity;
import com.esharp.sdk.Constant;
import com.esharp.sdk.base.BaseMvpFragment;
import com.esharp.sdk.bean.request.IDListVo;
import com.esharp.sdk.bean.response.DeviceBean;
import com.esharp.sdk.bean.response.DeviceVo;
import com.esharp.sdk.widget.MyTextView;
import com.esharp.sdk.widget.SPIconTextView;
import com.esharp.sdk.widget.SPShowTextView;
import com.esharp.sdk.widget.swipy.SwipyRefreshLayout;
import com.esharp.sdk.widget.swipy.SwipyRefreshLayoutDirection;

import java.util.ArrayList;
import java.util.List;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.recyclerview.widget.RecyclerView;

public class AssetsFragment extends BaseMvpFragment<AssetsContract.Presenter, MainActContract.IHost> implements AssetsContract.View {

    @Override
    protected Pair<Integer, AssetsContract.Presenter> layoutAndPresenter() {
        return Pair.create(R.layout.fragment_assets, new AssetsPresenter(this));
    }

    private SPIconTextView itv_create, itv_edit, itv_delete, itv_filter;

    private SPShowTextView stv_data_count;

    private SwipyRefreshLayout refreshLayout;

    private RecyclerView mRecyclerView;

    private AssetsRecordAdapter mAssetsRecordAdapter;

    int current = 1;

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
        stv_data_count = view.findViewById(R.id.stv_data_count);
        itv_edit = view.findViewById(R.id.itv_edit);
        itv_delete = view.findViewById(R.id.itv_delete);
        itv_delete = view.findViewById(R.id.itv_filter);

        itv_edit.setEnable(false);
        itv_delete.setEnable(false);

        mRecyclerView = view.findViewById(R.id.recyclerView);

        itv_create.setOnClickListener(v -> {
            CreateAssetActivity.startActivity(mContext, mLauncher);
        });

        itv_edit.setOnClickListener(v -> {
            List<DeviceBean> list = mAssetsRecordAdapter.getData();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).isChecked()) {
                    AssetEditActivity.startActivity(mContext, list.get(i), mLauncher);
                }
            }
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

        itv_filter.setOnClickListener(v -> {
//            FilterAssetDialog dialog = new FilterAssetDialog(this);
//            dialog.setOnClickListener(
//                    vo -> {
//                        if (mWorkOrderBean != null && nodeVo != null) {
//                            vo.setId(nodeVo.getId());
//                            mPresenter.workOrderProcess(vo);
//                        }
//                    },
//                    vo -> {
//                        if (mWorkOrderBean != null && nodeVo != null) {
//                            vo.setId(nodeVo.getId());
//                            mPresenter.workOrderProcess(vo);
//                        }
//                    }
//            );
//            dialog.show();
        });

        refreshLayout.setOnRefreshListener(direction -> {
            if (direction == SwipyRefreshLayoutDirection.TOP) {
                initData();
            } else {
                mPresenter.getData(current++);
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
                if (tempList.size() == 0) {
                    itv_edit.setEnable(false);
                    itv_delete.setEnable(false);
                } else if (tempList.size() == 1) {
                    itv_edit.setEnable(true);
                    itv_delete.setEnable(true);
                } else if (tempList.size() == 2) {
                    itv_edit.setEnable(false);
                    itv_delete.setEnable(true);
                }

            }
        }));

        initData();
    }

    private void initData() {
        mAssetsRecordAdapter.clearItems();
        mPresenter.getData(1);
    }

    @Override
    public void refreshData(DeviceVo it) {
        LogUtils.json(it);
        current = it.getCurrent();
        mAssetsRecordAdapter.refreshAllItems(it.getRecords());
        stv_data_count.setDetail(it.getTotal()+"");
    }

    @Override
    public void appendData(DeviceVo it) {
        LogUtils.json(it);
        current = it.getCurrent();
        mAssetsRecordAdapter.insertLastItems(it.getRecords());
    }

    @Override
    public void deleteDeviceSuc(boolean it) {
        LogUtils.json(it);
        if (it) {
            initData();
        }
    }

}
