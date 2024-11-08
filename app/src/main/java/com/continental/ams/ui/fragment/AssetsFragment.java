package com.continental.ams.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import com.blankj.utilcode.util.LogUtils;
import com.continental.ams.R;
import com.continental.ams.adapter.AssetsRecordAdapter;
import com.continental.ams.contract.AssetsContract;
import com.continental.ams.contract.MainActContract;
import com.continental.ams.dialog.FilterAssetDialog;
import com.continental.ams.presenter.AssetsPresenter;
import com.continental.ams.ui.AssetDetailActivity;
import com.continental.ams.ui.AssetEditActivity;
import com.continental.ams.ui.CreateAssetActivity;
import com.continental.ams.ui.MainActivity;
import com.continental.sdk.Constant;
import com.continental.sdk.base.BaseMvpFragment;
import com.continental.sdk.bean.request.IDListVo;
import com.continental.sdk.bean.response.DeviceBean;
import com.continental.sdk.bean.response.DeviceVo;
import com.continental.sdk.dialog.CustomDialogBuilder;
import com.continental.sdk.utils.ClickUtil;
import com.continental.sdk.utils.ResUtils;
import com.continental.sdk.widget.SPIconTextView;
import com.continental.sdk.widget.SPShowTextView;
import com.continental.sdk.widget.swipy.SwipyRefreshLayout;
import com.continental.sdk.widget.swipy.SwipyRefreshLayoutDirection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    Map<String, String> map = new HashMap<>();

    private final ActivityResultLauncher<Intent> mLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        Intent intent;
        if (result.getResultCode() == Activity.RESULT_OK && (intent = result.getData()) != null && (intent.getStringExtra(Constant.REFRESH_DATA)) != null) {
            initData();
        }
    });

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void init(View view) {
        refreshLayout = view.findViewById(R.id.refreshLayout);
        itv_create = view.findViewById(R.id.itv_create);
        stv_data_count = view.findViewById(R.id.stv_data_count);
        itv_edit = view.findViewById(R.id.itv_edit);
        itv_delete = view.findViewById(R.id.itv_delete);
        itv_filter = view.findViewById(R.id.itv_filter);

        itv_edit.setEnable(false);
        itv_delete.setEnable(false);

        mRecyclerView = view.findViewById(R.id.recyclerView);

        itv_create.setOnClickListener(v -> {
            if (ClickUtil.isFastDoubleClick()) {
                return;
            }
            CreateAssetActivity.startActivity(mContext, mLauncher);
        });

        itv_edit.setOnClickListener(v -> {
            if (ClickUtil.isFastDoubleClick()) {
                return;
            }
            List<DeviceBean> list = mAssetsRecordAdapter.getData();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).isCheck()) {
                    AssetEditActivity.startActivity(mContext, list.get(i), mLauncher);
                }
            }
        });

        itv_delete.setOnClickListener(v -> {
            if (ClickUtil.isFastDoubleClick()) {
                return;
            }
            new CustomDialogBuilder(mContext).setTitle(ResUtils.getString(R.string.is_delete_asset))
                    .setNegativeButton(null)
                    .setPositiveButton(R.string.confirm, (dialog, which) -> {
                        List<DeviceBean> list = mAssetsRecordAdapter.getData();
                        ArrayList<String> ids = new ArrayList<>();
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).isCheck()) {
                                ids.add(list.get(i).getId());
                            }
                        }
                        if (ids.size() > 0) {
                            IDListVo vo = new IDListVo();
                            vo.setIds(ids);
                            mPresenter.deleteDevice(vo);
                        }
                        dialog.dismiss();
                    }, true).create().show();
        });

        itv_filter.setOnClickListener(v -> {
            if (ClickUtil.isFastDoubleClick()) {
                return;
            }
            FilterAssetDialog dialog = new FilterAssetDialog((MainActivity)mContext);
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
                    if (list.get(i).isCheck()) {
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
        current = 1;
        LogUtils.json(map);
        mPresenter.getData(current, map);
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

    @Override
    public void refreshFinish() {
        if (refreshLayout != null) refreshLayout.setRefreshing(false);
    }

}
