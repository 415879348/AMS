package com.esharp.ams.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.esharp.ams.R;
import com.esharp.ams.contract.AssetDetailContract;
import com.esharp.ams.presenter.AssetDetailPresenter;
import com.esharp.sdk.base.BaseMvpActivity;
import com.esharp.sdk.bean.response.DeviceBean;
import com.esharp.sdk.http.GlideUtils;
import com.esharp.sdk.utils.DateTimeUtils;
import com.esharp.sdk.utils.ResUtils;
import com.esharp.sdk.widget.RadiusImageView;
import com.esharp.sdk.widget.SPShowTextView;

public class AssetDetailActivity extends BaseMvpActivity<AssetDetailContract.Presenter> implements AssetDetailContract.View {

    @Override
    protected Pair<Integer, AssetDetailContract.Presenter> layoutAndPresenter() {
        return Pair.create(R.layout.activity_asset_detail, new AssetDetailPresenter(this));
    }

    public static void startActivity(Context context, DeviceBean it) {
        Intent intent = new Intent(context, AssetDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("DeviceBean", it);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    SPShowTextView stv_asset_number, stv_asset_name, stv_asset_type,
            stv_asset_brand, stv_asset_model, stv_asset_location, stv_asset_size,
            stv_place_of_production, stv_date_of_manufacture, stv_warranty_period,
            stv_asset_status, stv_asset_remark, stv_asset_picture,
            stv_creation_time, stv_update_time, stv_weight, stv_color, stv_up_assets;

    LinearLayout ll_img;

    View v_color;

    @Override
    protected void init() {
        TextView title_text = findViewById(R.id.title_text);
        title_text.setText(R.string.asset_detail);

        stv_asset_number = findViewById(R.id.stv_asset_number);
        stv_asset_name = findViewById(R.id.stv_asset_name);
        stv_asset_type = findViewById(R.id.stv_asset_type);
        stv_asset_brand = findViewById(R.id.stv_asset_brand);
        stv_asset_model = findViewById(R.id.stv_asset_model);
        stv_asset_location = findViewById(R.id.stv_asset_location);
        stv_asset_size = findViewById(R.id.stv_asset_size);
        stv_weight = findViewById(R.id.stv_weight);
        v_color = findViewById(R.id.v_color);
        stv_color = findViewById(R.id.stv_color);
        stv_place_of_production = findViewById(R.id.stv_place_of_production);
        stv_date_of_manufacture = findViewById(R.id.stv_date_of_manufacture);
        stv_warranty_period = findViewById(R.id.stv_warranty_period);
        stv_up_assets = findViewById(R.id.stv_up_assets);
        stv_asset_status = findViewById(R.id.stv_asset_status);
        stv_asset_remark = findViewById(R.id.stv_asset_remark);
        stv_asset_picture = findViewById(R.id.stv_asset_picture);
        stv_creation_time = findViewById(R.id.stv_creation_time);
        stv_update_time = findViewById(R.id.stv_update_time);
        ll_img = findViewById(R.id.ll_img);

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
        LogUtils.json(it);

        stv_asset_number.setDetail(it.getDeviceNumber());
        stv_asset_name.setDetail(it.getDeviceName());
        stv_asset_type.setDetail(it.getDeviceType());
        stv_asset_brand.setDetail(it.getBrand());
        stv_asset_model.setDetail(it.getBrandModel());
        stv_asset_location.setDetail(it.getLocation());

        if (! TextUtils.isEmpty(it.getLength()) &&
                ! TextUtils.isEmpty(it.getWidth()) &&
                ! TextUtils.isEmpty(it.getHeight())) {
            stv_asset_size.setDetail(it.getLength() + ", " + it.getWidth() + ", " + it.getHeight());
        }
        if (it.getWeight() != null) {
            stv_weight.setDetail(it.getWeight());
        }

        if (! TextUtils.isEmpty(it.getColor())) {
            stv_color.setDetail(it.getColor());
            v_color.setBackgroundColor(ColorUtils.string2Int(it.getColor()));
        }

        stv_place_of_production.setDetail(it.getProduction());

        if (it.getProductionDate() != null && it.getProductionDate() != 0) {
            stv_date_of_manufacture.setDetail(DateTimeUtils.millis2Date(it.getProductionDate()));
        }
        if (it.getWarrantyDate() != null && it.getWarrantyDate() != 0) {
            stv_warranty_period.setDetail(DateTimeUtils.millis2Date(it.getWarrantyDate()));
        }

        stv_up_assets.setDetail(it.getParentName());

//        裝置狀態 0:正常 1:準備中 2:異常 3:初始化失敗，需要手動操作
        switch (it.getStatus()) {
            case 0 :
                stv_asset_status.setDetail(ResUtils.getString(R.string.regular));
                break;
            case 1 :
                stv_asset_status.setDetail(ResUtils.getString(R.string.in_preparation));
                break;
            case 2 :
                stv_asset_status.setDetail(ResUtils.getString(R.string.abnormal));
                break;
            case 3 :
                stv_asset_status.setDetail(ResUtils.getString(R.string.initialization_failed));
                break;
        }

        stv_asset_remark.setDetail(it.getRemark());

        if (it.getUrls().size() > 0) {

            for (int i = 0; i < it.getUrls().size(); i++) {

                RadiusImageView riv = new RadiusImageView(AssetDetailActivity.this);
                LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(SizeUtils.dp2px(80),SizeUtils.dp2px(80));
                param.leftMargin = 20;
                riv.setPadding(SizeUtils.dp2px(5), SizeUtils.dp2px(5), SizeUtils.dp2px(5), SizeUtils.dp2px(5));
                riv.setLayoutParams(param);
                riv.setBackground(ResUtils.getDrawable(R.drawable.spsdk_shape_rec_grey));
                ll_img.addView(riv);
                GlideUtils.showImage(riv, it.getUrls().get(i).getUrl());
            }

        }

        if (it.getCreateTime() != null) {
            stv_creation_time.setDetail(DateTimeUtils.millis2Date(it.getCreateTime()));
        }

        if (it.getUpdateTime() != null) {
            stv_update_time.setDetail(DateTimeUtils.millis2Date(it.getUpdateTime()));
        }

    }

}