package com.continental.ams.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Pair;
import com.continental.ams.R;
import com.blankj.utilcode.util.LogUtils;

import com.continental.ams.contract.WorkOrderCreateContract;
import com.continental.ams.presenter.WorkOrderCreatePresenter;
import com.continental.sdk.Constant;
import com.continental.sdk.base.BaseMvpActivity;
import com.continental.sdk.bean.request.CreateWorkOrderVo;
import com.continental.sdk.bean.response.DeviceBean;
import com.continental.sdk.bean.response.UserVo;
import com.continental.sdk.bean.response.WorkOrderTypeVo;
import com.continental.sdk.dialog.ListPopWindow;
import com.continental.sdk.utils.ClickUtil;
import com.continental.sdk.utils.ResUtils;
import com.continental.sdk.widget.MyTextView;
import com.continental.sdk.widget.SPCardEditView;
import com.continental.sdk.widget.SPSelectorCardView;
import com.continental.sdk.widget.SPTitleView;

import java.util.ArrayList;
import java.util.List;

import androidx.activity.result.ActivityResultLauncher;

public class WorkOrderCreateActivity extends BaseMvpActivity<WorkOrderCreateContract.Presenter> implements WorkOrderCreateContract.View {

    @Override
    protected Pair<Integer, WorkOrderCreateContract.Presenter> layoutAndPresenter() {
        return Pair.create(R.layout.activity_work_order_create, new WorkOrderCreatePresenter(this));
    }

    public static void startActivity(Context context, ActivityResultLauncher<Intent> mLauncher) {
        mLauncher.launch(new Intent(context, WorkOrderCreateActivity.class));
    }

    SPSelectorCardView scv_type, scv_asset_select, scv_handler;

    SPCardEditView cev_name, cev_location, cev_remark;

    MyTextView mv_end, mv_confirm;

    private ListPopWindow<WorkOrderTypeVo> typePop;

    private ListPopWindow<DeviceBean> devicePop;

    private ListPopWindow<UserVo> handlerPop;

    private List<WorkOrderTypeVo> typeList = new ArrayList<>();

    @Override
    protected void init() {
        ((SPTitleView)findViewById(com.continental.sdk.R.id.title_view)).setTitle(ResUtils.getString(R.string.job_create));

        WorkOrderTypeVo vo1 = new WorkOrderTypeVo();
        vo1.setTypeId("1");
        vo1.setTypeName(ResUtils.getString(R.string.job_fault));

        WorkOrderTypeVo vo2 = new WorkOrderTypeVo();
        vo2.setTypeId("2");
        vo2.setTypeName(ResUtils.getString(R.string.job_maintain));

        typeList.add(vo1);
        typeList.add(vo2);

        cev_name = findViewById(R.id.cev_name);
        scv_type = findViewById(R.id.scv_type);
        scv_asset_select = findViewById(R.id.scv_asset_select);
        cev_location = findViewById(R.id.cev_location);
        cev_location.setEnable(false);
        scv_handler = findViewById(R.id.scv_handler);
        cev_remark = findViewById(R.id.cev_remark);
        mv_end = findViewById(R.id.mv_reset);
        mv_confirm = findViewById(R.id.mv_confirm);

        LogUtils.json(typeList);

        scv_type.setOnItemClick(v -> {
            WorkOrderTypeVo vo = (WorkOrderTypeVo) v.getTag();
            if (vo == null) {
                typePop.show(scv_type, "");
            } else {
                typePop.show(scv_type, vo.getTypeId());
            }
        });

        scv_asset_select.setOnItemClick(v -> {
            DeviceBean vo = (DeviceBean) v.getTag();
            if (vo == null) {
                devicePop.show(scv_asset_select, "");
            } else {
                devicePop.show(scv_asset_select, vo.getId());
            }
        });

        scv_handler.setOnItemClick(v -> {
            UserVo vo = (UserVo) v.getTag();
            if (vo == null) {
                handlerPop.show(scv_handler, "");
            } else {
                handlerPop.show(scv_handler, vo.getId());
            }
        });

        mv_end.setOnClickListener(v -> {
            cev_name.setContent("");

            scv_type.setContent("");
            scv_type.setHint(ResUtils.getString(R.string.job_type));
            scv_type.setTag(null);

            scv_asset_select.setContent("");
            scv_asset_select.setHint(ResUtils.getString(R.string.asset_select));
            scv_asset_select.setTag(null);

            scv_handler.setContent("");
            scv_handler.setHint(ResUtils.getString(R.string.handler));
            scv_handler.setTag(null);

            cev_location.setContent("");
            cev_remark.setContent("");
        });

        mv_confirm.setOnClickListener(v -> {
            if (ClickUtil.isFastDoubleClick()) {
                return;
            }
            String name = cev_name.getContent();
            if (TextUtils.isEmpty(name)) {
                showToast(ResUtils.getString(R.string.please_enter)+ ResUtils.getString(R.string.job_name));
                return;
            }

            WorkOrderTypeVo typeVo = (WorkOrderTypeVo) scv_type.getTag();
            String typeId = "";
            if (typeVo != null) {
                typeId = typeVo.getTypeId();
            } else {
                showToast(ResUtils.getString(R.string.please_enter) + ResUtils.getString(R.string.job_type));
                return;
            }

            DeviceBean deviceBean = (DeviceBean) scv_asset_select.getTag();
            String deviceId = "";
            if (deviceBean != null) {
                deviceId = deviceBean.getId();
            } else {
                showToast(ResUtils.getString(R.string.asset_select));
                return;
            }

            UserVo handlerVo = (UserVo) scv_handler.getTag();
            String handlerId = "";
            if (handlerVo != null) {
                handlerId = handlerVo.getId();
            } else {
                showToast(ResUtils.getString(R.string.please_enter) + ResUtils.getString(R.string.handler));
                return;
            }

            String remark = cev_remark.getContent();
            if (TextUtils.isEmpty(remark)) {
                showToast(ResUtils.getString(R.string.please_enter) + ResUtils.getString(R.string.remark));
                return;
            }

            CreateWorkOrderVo it = new CreateWorkOrderVo();
            it.setTitle(name);
            it.setType(typeId);
            it.setDeviceId(deviceId);
            it.setProcessId(handlerId);
            it.setRemark(remark);
            LogUtils.json(it);
            mPresenter.createWorkOrder(it);
        });

        mPresenter.deviceAll();
        mPresenter.userAll();
    }

    @Override
    public void deviceAllSuc(List<DeviceBean> it) {
        LogUtils.json(it);
        if (it.size() > 0) {
            devicePop = new ListPopWindow<>(scv_asset_select,this, it, vo -> {
                LogUtils.json(vo);
                scv_asset_select.setTag(vo);
                scv_asset_select.setContent(vo.getDeviceName());
                if (! TextUtils.isEmpty(vo.getLocation())) {
                    cev_location.setContent(vo.getLocation());
                } else {
                    cev_location.setContent("");
                }

            });
        }

        typePop = new ListPopWindow<>(scv_type,this, typeList, vo -> {
            LogUtils.json(vo);
            scv_type.setTag(vo);
            scv_type.setContent(vo.getTypeName());
        });
    }

    @Override
    public void userAllSuc(List<UserVo> it) {
        LogUtils.json(it);
        if (it.size() > 0) {
            handlerPop = new ListPopWindow<>(scv_handler,this, it, vo -> {
                LogUtils.json(vo);
                scv_handler.setTag(vo);
                scv_handler.setContent(vo.getUsername());
            });
        }
    }

    @Override
    public void createWorkOrderSuc(boolean it) {
        LogUtils.json(it);
        if (it) {
            setResult(Activity.RESULT_OK, new Intent().putExtra(Constant.REFRESH_DATA, "success"));
            finish();
        }
    }
}