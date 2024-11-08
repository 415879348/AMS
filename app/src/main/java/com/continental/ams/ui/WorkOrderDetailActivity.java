package com.continental.ams.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.continental.ams.R;
import com.continental.ams.contract.WorkOrderDetailContract;
import com.continental.ams.dialog.WorkOrderHandleDialog;
import com.continental.ams.eventbus.EventWorkOrder;
import com.continental.ams.presenter.WorkOrderDetailPresenter;
import com.continental.sdk.Constant;
import com.continental.sdk.SPGlobalManager;
import com.continental.sdk.base.BaseMvpActivity;
import com.continental.sdk.bean.response.NodeVo;
import com.continental.sdk.bean.response.UrlsBean;
import com.continental.sdk.bean.response.UserVo;
import com.continental.sdk.bean.response.WorkOrderBean;
import com.continental.sdk.dialog.PicturePreviewDialog;
import com.continental.sdk.http.GlideUtils;
import com.continental.sdk.utils.ClickUtil;
import com.continental.sdk.utils.DateTimeUtils;
import com.continental.sdk.utils.ResUtils;
import com.continental.sdk.widget.AutoNextLineLinearlayout;
import com.continental.sdk.widget.MyTextView;
import com.continental.sdk.widget.RadiusImageView;
import com.continental.sdk.widget.SPIconTextView;
import com.continental.sdk.widget.SPSelectorView;
import com.continental.sdk.widget.SPShowTextView;
import org.greenrobot.eventbus.EventBus;
import java.util.List;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.cardview.widget.CardView;

public class WorkOrderDetailActivity extends BaseMvpActivity<WorkOrderDetailContract.Presenter> implements WorkOrderDetailContract.View {

    @Override
    protected Pair<Integer, WorkOrderDetailContract.Presenter> layoutAndPresenter() {
        return Pair.create(R.layout.activity_work_order_detail, new WorkOrderDetailPresenter(this));
    }

    public static void startActivity(Context context, WorkOrderBean it) {
        startActivity(context, it, NORMAL, null);
    }

    public static void startActivity(Context context, WorkOrderBean it, int mode, ActivityResultLauncher<Intent> launcher) {
        Intent intent = new Intent(context, WorkOrderDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("WorkOrderBean", it);
        bundle.putSerializable("Mode", mode);
        intent.putExtras(bundle);
        if (launcher != null) {
            launcher.launch(intent);
        } else {
            context.startActivity(intent);
        }
    }

    public static final int NORMAL = 0;
    public static final int HANDLE = 1;

    private WorkOrderBean mWorkOrderBean = null;

    private int mMode = NORMAL;

    private LinearLayout ll_handler, ll_images;

    private SPShowTextView stv_job_number, stv_job_name, stv_job_type,
            stv_asset_name, stv_location, stv_originator,
            stv_time_of_initiation, stv_remark, stv_handler,
            stv_processing_time, stv_remark2;

    private SPSelectorView sv_selector;

    private MyTextView mv_handle;

    private NodeVo nodeVo = null;

    private ActivityResultLauncher<Intent> mLauncher = null;

    @Override
    protected void init() {
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;

        TextView title_text = findViewById(R.id.title_text);
        title_text.setText(R.string.job_detail);
        ll_images = findViewById(R.id.ll_images);

        mv_handle = findViewById(R.id.mv_handle);
        mv_handle.setOnClickListener(v -> {
            if (ClickUtil.isFastDoubleClick()) {
                return;
            }
            WorkOrderHandleDialog dialog = new WorkOrderHandleDialog(this, mWorkOrderBean);
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
//        mPresenter.userAll();
        mPresenter.workOrderID(mWorkOrderBean.getId()+"");
        mPresenter.workOrderNode(mWorkOrderBean.getId()+"");

        mLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            Intent intent;
            if (result.getResultCode() == Activity.RESULT_OK && (intent = result.getData()) != null && (intent.getStringExtra(Constant.REFRESH_DATA)) != null) {
                mPresenter.workOrderNode(mWorkOrderBean.getId()+"");
            }
        });
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
        stv_job_type.setDetail(it.getType());

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

        ll_images.removeAllViews();
        List<UrlsBean> urls = it.getUrls();
        if (urls.size() > 0) {
            findViewById(R.id.ll_appendix).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.ll_appendix).setVisibility(View.GONE);
        }
        if (urls.size() > 0) {
            for (int j = 0; j < urls.size(); j++) {
                RadiusImageView riv = new RadiusImageView(WorkOrderDetailActivity.this);
                LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(SizeUtils.dp2px(80),SizeUtils.dp2px(80));
                param.leftMargin = 20;
                riv.setPadding(SizeUtils.dp2px(5), SizeUtils.dp2px(5), SizeUtils.dp2px(5), SizeUtils.dp2px(5));
                riv.setLayoutParams(param);
                riv.setBackground(ResUtils.getDrawable(R.drawable.spsdk_shape_rec_grey));
                String url = urls.get(j).getUrl();
                riv.setOnClickListener(v -> new PicturePreviewDialog(WorkOrderDetailActivity.this, url).show());
                ll_images.addView(riv);
                GlideUtils.showImage(riv, url);
            }
        }
    }

    @Override
    public void workOrderNodeSuc(List<NodeVo> it) {
        LogUtils.json(it);
        if (it.size() < 1){
            return;
        }
        nodeVo = it.get(it.size() - 1);
        ll_handler = findViewById(R.id.ll_handler);
        ll_handler.removeAllViews();

        for (int i = 0; i < it.size(); i++) {
            NodeVo vo = it.get(i);
            CardView item_handler = (CardView) LayoutInflater.from(this).inflate(R.layout.item_handler, ll_handler, false);
            ll_handler.addView(item_handler);
            stv_handler = item_handler.findViewById(R.id.stv_handler);
            stv_handler.setDetail(vo.getUsername());
            SPIconTextView itv_edit = item_handler.findViewById(R.id.itv_edit);
            itv_edit.setVisibility(View.GONE);
            itv_edit.setOnClickListener(v -> {
                WorkOrderNodeEditActivity.startActivity(WorkOrderDetailActivity.this, vo, mLauncher);
            });
            LogUtils.json(vo);
            LogUtils.json(SPGlobalManager.getUserVo());
//            if ((vo.getUserId() + "").equals(SPGlobalManager.getUserVo().getId()) ||
//                    vo.getStatus() == 1) {
//                itv_edit.setVisibility(View.VISIBLE);
//            } else {
//                itv_edit.setVisibility(View.GONE);
//            }
            stv_processing_time = item_handler.findViewById(R.id.stv_processing_time);
            if (vo.getEndTime() != null) {
                stv_processing_time.setDetail(DateTimeUtils.millis2Date(vo.getEndTime()));
            }
            stv_remark2 = item_handler.findViewById(R.id.stv_remark2);
            stv_remark2.setDetail(vo.getContent());
            AutoNextLineLinearlayout ll_images = item_handler.findViewById(R.id.ll_images);
            ll_images.removeAllViews();
            List<UrlsBean> urls = vo.getUrls();
            if (urls.size() > 0) {
                item_handler.findViewById(R.id.ll_appendix).setVisibility(View.VISIBLE);
            } else {
                item_handler.findViewById(R.id.ll_appendix).setVisibility(View.GONE);
            }
            if (urls.size() > 0) {
                for (int j = 0; j < urls.size(); j++) {
                    RadiusImageView riv = new RadiusImageView(WorkOrderDetailActivity.this);
                    LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(SizeUtils.dp2px(80),SizeUtils.dp2px(80));
                    param.leftMargin = 20;
                    riv.setPadding(SizeUtils.dp2px(5), SizeUtils.dp2px(5), SizeUtils.dp2px(5), SizeUtils.dp2px(5));
                    riv.setLayoutParams(param);
                    riv.setBackground(ResUtils.getDrawable(R.drawable.spsdk_shape_rec_grey));
                    String url = urls.get(j).getUrl();
                    riv.setOnClickListener(v -> new PicturePreviewDialog(WorkOrderDetailActivity.this, url).show());
                    ll_images.addView(riv);
                    GlideUtils.showImage(riv, url);
                }
            }
        }

    }

    @Override
    public void workOrderProcessSuc(Boolean it) {
        if (it) {
            EventBus.getDefault().post(new EventWorkOrder());
            setResult(Activity.RESULT_OK, new Intent().putExtra(Constant.REFRESH_DATA, "success"));
            finish();
        }
    }

    @Override
    public void userAllSuc(List<UserVo> it) {
        LogUtils.json(it);
    }
}