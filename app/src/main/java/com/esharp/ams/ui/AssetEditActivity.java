package com.esharp.ams.ui;

import android.app.Activity;
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
import com.esharp.ams.ui.fragment.AssetsFragment;
import com.esharp.sdk.Constant;
import com.esharp.sdk.base.BaseActivity;
import com.esharp.sdk.base.BaseMvpActivity;
import com.esharp.sdk.bean.response.DeviceBean;
import com.esharp.sdk.bean.response.DeviceInfoForm;
import com.esharp.sdk.bean.response.DictionaryBean;
import com.esharp.sdk.dialog.ListPopWindow;
import com.esharp.sdk.utils.DateTimeUtils;
import com.esharp.sdk.utils.ResUtils;
import com.esharp.sdk.widget.DateTimeSelector;
import com.esharp.sdk.widget.MyTextView;
import com.esharp.sdk.widget.SPCardEditView;
import com.esharp.sdk.widget.SPSelectorCardView;
import com.esharp.sdk.widget.SPTitleView;

import java.util.List;

import androidx.activity.result.ActivityResultLauncher;

public class AssetEditActivity extends BaseMvpActivity<AssetEditContract.Presenter> implements AssetEditContract.View {

    @Override
    protected Pair<Integer, AssetEditContract.Presenter> layoutAndPresenter() {
        return Pair.create(R.layout.activity_asset_edit, new AssetEditPresenter(this));
    }

    public static void startActivity(Context context, DeviceBean it, ActivityResultLauncher<Intent> mLauncher) {
        Intent intent = new Intent(context, AssetEditActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("DeviceBean", it);
        intent.putExtras(bundle);
        mLauncher.launch(intent);
    }

    SPSelectorCardView scv_type, scv_brand, scv_model, scv_date_of_manufacture, scv_warranty_period;

    SPCardEditView cev_number, cev_name, cev_location, cev_lwh, cev_place_of_production, cev_remark;

    MyTextView mv_end, mv_confirm;

    DeviceBean deviceBean;

    private ListPopWindow<DictionaryBean> assetTypePop, assetBrandPop, assetModelPop;

    @Override
    protected void init() {
        ((SPTitleView)findViewById(com.esharp.sdk.R.id.title_view)).setTitle(ResUtils.getString(R.string.asset_edit));

        cev_number = findViewById(R.id.cev_number);
        cev_number.setEnable(false);
        cev_name = findViewById(R.id.cev_name);
        scv_type = findViewById(R.id.scv_type);
        scv_brand = findViewById(R.id.scv_brand);
        scv_model = findViewById(R.id.scv_model);
        cev_location = findViewById(R.id.cev_location);
        cev_lwh = findViewById(R.id.cev_lwh);
        cev_place_of_production = findViewById(R.id.cev_place_of_production);

        scv_date_of_manufacture = findViewById(R.id.scv_date_of_manufacture);
        scv_warranty_period = findViewById(R.id.scv_warranty_period);

        cev_remark = findViewById(R.id.cev_remark);

        mv_end = findViewById(R.id.mv_end);
        mv_confirm = findViewById(R.id.mv_confirm);

        scv_type.setOnItemClick(v -> {
            DictionaryBean vo = (DictionaryBean) v.getTag();
            if (vo == null) {
                assetTypePop.show(scv_type, "");
            } else {
                assetTypePop.show(scv_type, vo.getId());
            }
        });

        scv_brand.setOnItemClick(v -> {
            DictionaryBean vo = (DictionaryBean) v.getTag();
            if (vo == null) {
                assetBrandPop.show(scv_brand, "");
            } else {
                assetBrandPop.show(scv_brand, vo.getId());
            }
        });

        scv_model.setOnItemClick(v -> {
            if (assetModelPop == null) {
                return;
            }

            DictionaryBean vo = (DictionaryBean) v.getTag();
            if (vo == null) {
                assetModelPop.show(scv_model, "");
            } else {
                assetModelPop.show(scv_model, vo.getId());
            }
        });

        scv_date_of_manufacture.setOnItemClick(v -> {
            new DateTimeSelector(v.getContext(), datetime -> {
                LogUtils.json(datetime);
                scv_date_of_manufacture.setContent(datetime);
            });
        });

        scv_warranty_period.setOnItemClick(v -> {
            new DateTimeSelector(v.getContext(), datetime -> {
                LogUtils.json(datetime);
                scv_warranty_period.setContent(datetime);
            });
        });

        mv_end.setOnClickListener(v -> {
            cev_number.setContent("");
            cev_name.setContent("");

            scv_type.setContent("");
            scv_type.setHint(ResUtils.getString(R.string.asset_type));
            scv_type.setTag(null);

            scv_brand.setContent("");
            scv_brand.setHint(ResUtils.getString(R.string.brand));
            scv_brand.setTag(null);

            scv_model.setContent("");
            scv_model.setHint(ResUtils.getString(R.string.model));
            scv_model.setTag(null);
            assetModelPop = null;

            cev_lwh.setContent("");
            cev_place_of_production.setContent("");

            scv_date_of_manufacture.setContent("");
            scv_warranty_period.setContent("");

            cev_remark.setContent("");
        });

        mv_confirm.setOnClickListener(v -> {
            String number = cev_number.getContent();
            String name = cev_name.getContent();

            DictionaryBean typeVo = (DictionaryBean) scv_type.getTag();
            String deviceTypeId = typeVo.getId();

            DictionaryBean brandVo = (DictionaryBean) scv_brand.getTag();
            String brandId = brandVo.getId();

            DictionaryBean modelVo = (DictionaryBean) scv_model.getTag();
            String brandModelId = modelVo.getId();

            String lwh = cev_lwh.getContent();
            String place_of_production = cev_place_of_production.getContent();
            String date_of_manufacture = scv_date_of_manufacture.getContent();
            String warranty_period = scv_warranty_period.getContent();
            String remark = cev_remark.getContent();

            DeviceInfoForm it = new DeviceInfoForm();
            it.setId(deviceBean.getId());
            it.setDeviceNumber(number);
            it.setDeviceName(name);
            it.setDeviceTypeId(deviceTypeId);
            it.setBrandId(brandId);
            it.setBrandModelId(brandModelId);
            it.setRemark(remark);
            it.setProduction(place_of_production);
            it.setProdDate(DateTimeUtils.parseToLong(date_of_manufacture));
            it.setWarranty(DateTimeUtils.parseToLong(warranty_period));

            String[] list = lwh.split(",");

            if (list.length == 3) {
                it.setLength(list[0]);
                it.setWidth(list[1]);
                it.setHeight(list[2]);
            }

            LogUtils.json(it);
            mPresenter.updateDevice(it);
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
        deviceBean = it;

        cev_number.setContent(it.getDeviceNumber());
        cev_name.setContent(it.getDeviceName());
        cev_remark.setContent(it.getRemark());
        cev_location.setContent(it.getLocation());

        cev_lwh.setContent(it.getLength() + "," + it.getWidth() + "," +  it.getLength());
        cev_place_of_production.setContent(it.getProduction());

        if (it.getProductionDate() != null) {
            scv_date_of_manufacture.setContent(DateTimeUtils.millis2Date(it.getProductionDate()));
        }
        if (it.getWarrantyDate() != null) {
            scv_warranty_period.setContent(DateTimeUtils.millis2Date(it.getWarrantyDate()));
        }

        mPresenter.assetType();
        mPresenter.assetBrand();
    }

    @Override
    public void updateDeviceSuc(boolean it) {
        LogUtils.json(it);
        if (it) {
            setResult(Activity.RESULT_OK, new Intent().putExtra(Constant.REFRESH_DATA, "success"));
            finish();
        }
    }


    @Override
    public void assetTypeSuc(List<DictionaryBean> it) {
        LogUtils.json(it);
        if (it.size() > 0) {
            assetTypePop = new ListPopWindow<>(scv_type,this, it, vo -> {
                LogUtils.json(vo);
                scv_type.setTag(vo);
                scv_type.setContent(vo.getDictName());
            });

            for (int i = 0; i < it.size(); i++) {
                DictionaryBean vo = it.get(i);
                if (vo.getId().equals(deviceBean.getDeviceTypeId())) {
                    scv_type.setTag(vo);
                    scv_type.setContent(vo.getDictName());
                }
            }
        }
    }

    @Override
    public void assetBrandSuc(List<DictionaryBean> it) {
        LogUtils.json(it);
        if (it.size() > 0) {
            assetBrandPop = new ListPopWindow<>(scv_brand,this, it, vo -> {
                LogUtils.json(vo);
                scv_brand.setTag(vo);
                scv_brand.setContent(vo.getDictName());
                mPresenter.assetModel(vo.getId());
            });

            for (int i = 0; i < it.size(); i++) {
                DictionaryBean vo = it.get(i);
                if (vo.getId().equals(deviceBean.getBrandId())) {
                    scv_brand.setTag(vo);
                    scv_brand.setContent(vo.getDictName());
                    mPresenter.assetModel(vo.getId());
                }
            }
        }
    }

    @Override
    public void assetModelSuc(List<DictionaryBean> it) {
        LogUtils.json(it);
        if (it.size() > 0) {
            assetModelPop = new ListPopWindow<>(scv_model,this, it, vo -> {
                LogUtils.json(vo);
                scv_model.setTag(vo);
                scv_model.setContent(vo.getDictName());
            });

            if (scv_model.getTag() == null) {
                for (int i = 0; i < it.size(); i++) {
                    DictionaryBean vo = it.get(i);
                    if (vo.getId().equals(deviceBean.getBrandModelId())) {
                        scv_model.setTag(vo);
                        scv_model.setContent(vo.getDictName());
                    }
                }
            } else {
                scv_model.setTag(it.get(0));
                scv_model.setContent(it.get(0).getDictName());
            }
        }
    }

}