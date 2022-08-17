package com.esharp.ams.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.esharp.ams.R;
import com.esharp.ams.contract.WorkOrderDetailContract;
import com.esharp.ams.dialog.WorkOrderHandleDialog;
import com.esharp.ams.presenter.WorkOrderDetailPresenter;
import com.esharp.sdk.base.BaseMvpActivity;
import com.esharp.sdk.bean.response.NodeVo;
import com.esharp.sdk.bean.response.UserVo;
import com.esharp.sdk.bean.response.WorkOrderBean;
import com.esharp.sdk.bean.response.WorkOrderTypeVo;
import com.esharp.sdk.utils.DateTimeUtils;
import com.esharp.sdk.utils.ResUtils;
import com.esharp.sdk.widget.MyTextView;
import com.esharp.sdk.widget.SPSelectorView;
import com.esharp.sdk.widget.SPShowTextView;

import java.util.List;

import androidx.cardview.widget.CardView;

public class WorkOrderDetailActivity extends BaseMvpActivity<WorkOrderDetailContract.Presenter> implements WorkOrderDetailContract.View {

    @Override
    protected Pair<Integer, WorkOrderDetailContract.Presenter> layoutAndPresenter() {
        return Pair.create(R.layout.activity_work_order_detail, new WorkOrderDetailPresenter(this));
    }

    public static void startActivity(Context context, WorkOrderBean it) {
        startActivity(context, it, NORMAL);
    }

    public static void startActivity(Context context, WorkOrderBean it, int mode) {
        Intent intent = new Intent(context, WorkOrderDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("WorkOrderBean", it);
        bundle.putSerializable("Mode", mode);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static final int NORMAL = 0;
    public static final int HANDLE = 1;

    private WorkOrderBean mWorkOrderBean = null;

    private int mMode = NORMAL;

    private LinearLayout ll_handler = null;

    private SPShowTextView stv_job_number, stv_job_name, stv_job_type,
            stv_asset_name, stv_location, stv_originator,
            stv_time_of_initiation, stv_remark, stv_handler,
            stv_processing_time, stv_remark2;

    private SPSelectorView sv_selector;

    private MyTextView mv_handle;

    private NodeVo nodeVo = null;

    @Override
    protected void init() {
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;

        TextView title_text = findViewById(R.id.title_text);
        title_text.setText(R.string.job_detail);

        mv_handle = findViewById(R.id.mv_handle);
        mv_handle.setOnClickListener(v -> {
            WorkOrderHandleDialog dialog = new WorkOrderHandleDialog(this);
            dialog.setOnClickListener(
                    vo -> {
                        if (mWorkOrderBean != null && nodeVo != null) {
                            vo.setId(nodeVo.getId());
                            mPresenter.workOrderProcess(vo);
                        }
                    },
                    vo -> {
                        if (mWorkOrderBean != null && nodeVo != null) {
                            vo.setId(nodeVo.getId());
                            mPresenter.workOrderProcess(vo);
                        }
                    }
            );
            dialog.show();
        });

        if (bundle.containsKey("WorkOrderBean")) {
            mWorkOrderBean = (WorkOrderBean) bundle.getSerializable("WorkOrderBean");
        }
        if (bundle.containsKey("Mode")) {
            mMode = bundle.getInt("Mode");
            if (mMode == NORMAL) {
                mv_handle.setVisibility(View.GONE);
            } else if (mMode == HANDLE) {
                mv_handle.setVisibility(View.VISIBLE);
            }
        }
        LogUtils.json(mWorkOrderBean);
        mPresenter.userAll();
        mPresenter.workOrderID(mWorkOrderBean.getId()+"");
        mPresenter.workOrderNode(mWorkOrderBean.getId()+"");
    }

    @Override
    public void workOrderIDSuc(WorkOrderBean it) {
        LogUtils.json(it);
//        mWorkOrderBean = it;
        stv_job_number = findViewById(R.id.stv_job_number);
        stv_job_number.setDetail(it.getOrderNo());

        stv_job_name = findViewById(R.id.stv_job_name);
        stv_job_name.setDetail(it.getTitle());

        stv_job_type = findViewById(R.id.stv_job_type);

        switch (it.getType()) {
            case 1:
                stv_job_type.setDetail(ResUtils.getString(R.string.job_fault));
                break;
            case 2:
                stv_job_type.setDetail(ResUtils.getString(R.string.job_maintain));
                break;
        }

        stv_asset_name = findViewById(R.id.stv_asset_name);
        stv_asset_name.setDetail(it.getDeviceName());

        stv_location = findViewById(R.id.stv_location);
        stv_location.setDetail(it.getAddress());

        stv_originator = findViewById(R.id.stv_originator);
        stv_originator.setDetail(it.getApplyName());

        stv_time_of_initiation = findViewById(R.id.stv_time_of_initiation);
        stv_time_of_initiation.setDetail(DateTimeUtils.millis2Date(it.getApplyTime()));

        stv_remark = findViewById(R.id.stv_remark);
        stv_remark.setDetail(it.getRemark());
    }

    @Override
    public void workOrderNodeSuc(List<NodeVo> it) {
        LogUtils.json(it);
        nodeVo = it.get(it.size() - 1);
        ll_handler = findViewById(R.id.ll_handler);

        for (int i = 0; i < it.size(); i++) {
            NodeVo vo = it.get(i);
            CardView item_handler = (CardView) LayoutInflater.from(this).inflate(R.layout.item_handler, ll_handler, false);
            stv_handler = item_handler.findViewById(R.id.stv_handler);
            stv_handler.setDetail(vo.getUsername());
            stv_processing_time = item_handler.findViewById(R.id.stv_processing_time);
            if (vo.getEndTime() != null) {
                stv_processing_time.setDetail(DateTimeUtils.millis2Date(vo.getEndTime()));
            }
            stv_remark2 = item_handler.findViewById(R.id.stv_remark2);
            stv_remark2.setDetail(vo.getContent());
            ll_handler.addView(item_handler);
        }

    }

    @Override
    public void workOrderProcessSuc(Boolean it) {
        if (it) {
            finish();
        }
    }

    @Override
    public void userAllSuc(List<UserVo> it) {
        LogUtils.json(it);

    }
}