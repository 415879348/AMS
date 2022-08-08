package com.esharp.ams.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.LogUtils;
import com.esharp.ams.R;
import com.esharp.ams.contract.CreateAssetContract;
import com.esharp.ams.contract.WorkOrderDetailContract;
import com.esharp.ams.dialog.WorkOrderHandleDialog;
import com.esharp.ams.presenter.CreateAssetPresenter;
import com.esharp.ams.presenter.WorkOrderDetailPresenter;
import com.esharp.sdk.base.BaseActivity;
import com.esharp.sdk.base.BaseMvpActivity;
import com.esharp.sdk.bean.response.DeviceBean;
import com.esharp.sdk.bean.response.DeviceInfoForm;
import com.esharp.sdk.bean.response.NodeVo;
import com.esharp.sdk.bean.response.UserVo;
import com.esharp.sdk.bean.response.WorkOrderBean;
import com.esharp.sdk.utils.DateTimeUtils;
import com.esharp.sdk.widget.MyTextView;
import com.esharp.sdk.widget.SPCardEditView;
import com.esharp.sdk.widget.SPSelectorView;
import com.esharp.sdk.widget.SPShowTextView;

import java.util.List;

import androidx.cardview.widget.CardView;

public class CreateAssetActivity extends BaseMvpActivity<CreateAssetContract.Presenter> implements CreateAssetContract.IHost {

    @Override
    protected Pair<Integer, CreateAssetContract.Presenter> layoutAndPresenter() {
        return Pair.create(R.layout.activity_create_asset, new CreateAssetPresenter(this));
    }

    @Override
    public BaseActivity getHost() {
        return this;
    }

    @Override
    protected boolean isShowTitle() {
        return true;
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, CreateAssetActivity.class);
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    SPCardEditView cev_number, cev_name, cev_type,
            cev_brand, cev_model, cev_lwh,
            cev_place_of_production,cev_date_of_manufacture,
            cev_warranty_period,cev_remark;

    MyTextView mv_end, mv_confirm;

    @Override
    protected void init() {

        cev_number = findViewById(R.id.cev_number);
        cev_name = findViewById(R.id.cev_name);
        cev_type = findViewById(R.id.cev_type);
        cev_brand = findViewById(R.id.cev_brand);
        cev_model = findViewById(R.id.cev_model);
        cev_lwh = findViewById(R.id.cev_lwh);
        cev_place_of_production = findViewById(R.id.cev_place_of_production);
        cev_date_of_manufacture = findViewById(R.id.cev_date_of_manufacture);
        cev_warranty_period = findViewById(R.id.cev_warranty_period);
        cev_remark = findViewById(R.id.cev_remark);

        mv_end = findViewById(R.id.mv_end);
        mv_confirm = findViewById(R.id.mv_confirm);

//        mPresenter.getData();

        mv_end.setOnClickListener(v -> {
            cev_number.setContent("");
            cev_name.setContent("");
            cev_type.setContent("");
            cev_brand.setContent("");
            cev_model.setContent("");
            cev_lwh.setContent("");
            cev_place_of_production.setContent("");
            cev_date_of_manufacture.setContent("");
            cev_warranty_period.setContent("");
            cev_remark.setContent("");
        });

        mv_confirm.setOnClickListener(v -> {
            String number = cev_number.getContent();
            String name = cev_name.getContent();
            String type = cev_type.getContent(); // 资产类型
            String brand = cev_brand.getContent();
            String model = cev_model.getContent();
            String lwh = cev_lwh.getContent();
            String place_of_production = cev_place_of_production.getContent();
            String date_of_manufacture = cev_date_of_manufacture.getContent();
            String warranty_period = cev_warranty_period.getContent();
            String remark = cev_remark.getContent();

            DeviceInfoForm it = new DeviceInfoForm();
            it.setDeviceNumber(number);
            it.setDeviceName(name);
            it.setDeviceTypeId(0); // brandModelId
            mPresenter.addAppDevice(it);
        });
    }

    @Override
    public void getDataSuc() {

    }

    @Override
    public void addAppDevice(boolean it) {

    }


}