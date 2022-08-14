package com.esharp.ams.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;

import com.blankj.utilcode.util.LogUtils;
import com.esharp.ams.R;
import com.esharp.ams.contract.AssetEditContract;
import com.esharp.ams.contract.CreateAssetContract;
import com.esharp.ams.presenter.AssetEditPresenter;
import com.esharp.ams.presenter.CreateAssetPresenter;
import com.esharp.sdk.base.BaseActivity;
import com.esharp.sdk.base.BaseMvpActivity;
import com.esharp.sdk.bean.response.DeviceBean;
import com.esharp.sdk.bean.response.DeviceInfoForm;
import com.esharp.sdk.bean.response.DictionaryBean;
import com.esharp.sdk.widget.MyTextView;
import com.esharp.sdk.widget.SPCardEditView;

import java.util.List;

public class AssetEditActivity extends BaseMvpActivity<AssetEditContract.Presenter> implements AssetEditContract.View {

    @Override
    protected Pair<Integer, AssetEditContract.Presenter> layoutAndPresenter() {
        return Pair.create(R.layout.activity_create_asset, new AssetEditPresenter(this));
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, AssetEditActivity.class);
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
//            it.setDeviceTypeId(0); // brandModelId
            mPresenter.updateAppDevice(it);
        });

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        if (bundle.containsKey("DeviceBean")) {
            DeviceBean it = (DeviceBean) bundle.getSerializable("DeviceBean");
            LogUtils.json(it);
            mPresenter.getData(it.getId());
        }
    }

    @Override
    public void getDataSuc(DeviceBean it) {
        cev_number.setHint(it.getId()+"");
        cev_name.setHint(it.getDeviceName());
        cev_type.setHint(it.getDeviceName());
        cev_brand.setHint(it.getDeviceName());
        cev_model.setHint(it.getDeviceName());
        cev_lwh.setHint(it.getLength() + it.getWidth() + it.getLength());
        cev_place_of_production.setHint(it.getProduction());
//        cev_date_of_manufacture.setHint(it.getProductionDate());
//        cev_warranty_period.setHint(it.getWarrantyDate());
//        cev_remark.setHint(it.getWarrantyDate());
    }

    @Override
    public void dictAllSuc(List<DictionaryBean> it) {

    }

    @Override
    public void updateAppDeviceSuc(boolean it) {

    }


}