package com.continental.ams.ui;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Pair;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.bumptech.glide.Glide;

import com.continental.ams.R;
import com.continental.ams.contract.AssetEditContract;
import com.continental.ams.presenter.AssetEditPresenter;
import com.continental.sdk.Constant;
import com.continental.sdk.base.BaseMvpActivity;
import com.continental.sdk.bean.request.FileVo;
import com.continental.sdk.bean.request.FieldVo;
import com.continental.sdk.bean.request.Files;
import com.continental.sdk.bean.response.DeviceBean;
import com.continental.sdk.bean.response.DeviceFieldValueBean;
import com.continental.sdk.bean.response.DeviceInfoForm;
import com.continental.sdk.bean.response.DictionaryBean;
import com.continental.sdk.bean.response.UrlsBean;
import com.continental.sdk.dialog.CustomDialogBuilder;
import com.continental.sdk.dialog.ListPopWindow;
import com.continental.sdk.http.GlideUtils;
import com.continental.sdk.utils.ClickUtil;
import com.continental.sdk.utils.DateTimeUtils;
import com.continental.sdk.utils.FileUtils;
import com.continental.sdk.utils.PicImageEngine;
import com.continental.sdk.utils.ResUtils;
import com.continental.sdk.widget.DateTimeSelector;
import com.continental.sdk.widget.DeleteImageView;
import com.continental.sdk.widget.MyTextView;
import com.continental.sdk.widget.SPCardEditView;
import com.continental.sdk.widget.SPSelectorCardView;
import com.continental.sdk.widget.SPTitleView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import androidx.activity.result.ActivityResultLauncher;
import top.defaults.colorpicker.ColorPickerPopup;

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

    SPSelectorCardView scv_type, scv_brand, scv_model, scv_up_assets, scv_date_of_manufacture, scv_warranty_period;

    SPCardEditView cev_number, cev_name, cev_location, cev_l, cev_w, cev_h, cev_weight, cev_place_of_production, cev_remark, cev_color;

    MyTextView mv_reset, mv_confirm, mv_upload;

    DeviceBean deviceBean;

    private ListPopWindow<DictionaryBean> assetTypePop, assetBrandPop, assetModelPop;

    private ListPopWindow<DeviceBean> assetPop;

    ImageView  iv_color;

    LinearLayout ll_field, ll_images;

    Map<String, String> photoMap = new HashMap<>();

    private List<DeviceFieldValueBean> deviceFieldValueForms = new ArrayList<>();

    @Override
    protected void init() {
        ((SPTitleView)findViewById(com.continental.sdk.R.id.title_view)).setTitle(ResUtils.getString(R.string.asset_edit));

        cev_number = findViewById(R.id.cev_number);
        cev_number.setEnable(false);
        cev_name = findViewById(R.id.cev_name);
        scv_type = findViewById(R.id.scv_type);
        scv_brand = findViewById(R.id.scv_brand);
        scv_model = findViewById(R.id.scv_model);
        scv_up_assets = findViewById(R.id.scv_up_assets);
        cev_location = findViewById(R.id.cev_location);
        cev_weight = findViewById(R.id.cev_weight);
        cev_l = findViewById(R.id.cev_l);
        cev_w = findViewById(R.id.cev_w);
        cev_h = findViewById(R.id.cev_h);
        cev_place_of_production = findViewById(R.id.cev_place_of_production);

        scv_date_of_manufacture = findViewById(R.id.scv_date_of_manufacture);
        scv_warranty_period = findViewById(R.id.scv_warranty_period);

        cev_remark = findViewById(R.id.cev_remark);
        ll_field = findViewById(R.id.ll_field);
        cev_color = findViewById(R.id.cev_color);
        cev_color.setEnable(false);
        iv_color = findViewById(R.id.iv_color);
        mv_reset = findViewById(R.id.mv_reset);
        mv_confirm = findViewById(R.id.mv_confirm);

        mv_upload = findViewById(R.id.mv_upload);
        ll_images = findViewById(R.id.ll_images);

        iv_color.setTag(ColorUtils.int2RgbString(ResUtils.getColor(R.color.spsdk_color_blue)));
        iv_color.setOnClickListener(v -> {
            if (ClickUtil.isFastDoubleClick()) {
                return;
            }
            new ColorPickerPopup.Builder(this)
                    .initialColor(ColorUtils.string2Int((String) v.getTag())) // Set initial color
                    .enableBrightness(false) // Enable brightness slider or not
                    .enableAlpha(false) // Enable alpha slider or not
                    .okTitle(ResUtils.getString(R.string.choose))
                    .cancelTitle(ResUtils.getString(R.string.spsdk_cancel))
                    .showIndicator(true)
                    .showValue(false)
                    .build()
                    .show(v, new ColorPickerPopup.ColorPickerObserver() {
                        @Override
                        public void onColorPicked(int color) {
                            LogUtils.i(ColorUtils.int2RgbString(color));
                            v.setBackgroundColor(color);
                            v.setTag(ColorUtils.int2RgbString(color));
                            cev_color.setContent(ColorUtils.int2RgbString(color));
                        }
                    });
        });

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

        scv_up_assets.setOnItemClick(v -> {
            if (assetPop == null) {
                return;
            }

            DeviceBean vo = (DeviceBean) v.getTag();
            if (vo == null) {
                assetPop.show(scv_up_assets, "");
            } else {
                assetPop.show(scv_up_assets, vo.getId());
            }
        });

        scv_date_of_manufacture.setOnItemClick(v -> {
            new DateTimeSelector(v.getContext(), datetime -> {
                LogUtils.json(datetime);
                scv_date_of_manufacture.setContent(datetime);
            }, DateTimeUtils.parseToLong(scv_warranty_period.getContent()), null);
        });

        scv_warranty_period.setOnItemClick(v -> {
            new DateTimeSelector(v.getContext(), datetime -> {
                LogUtils.json(datetime);
                scv_warranty_period.setContent(datetime);
            }, null, DateTimeUtils.parseToLong(scv_date_of_manufacture.getContent()));
        });

        mv_upload.setOnClickListener(v -> {
            if (!ClickUtil.isFastDoubleClick()) {
                LogUtils.i(PermissionUtils.isGranted(Manifest.permission.CAMERA));
                LogUtils.i(PermissionUtils.isGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE));
                // 手动禁止权限下次会主动弹出权限窗口，代码禁止权限下次不会主动弹出权限窗口
                if (PermissionUtils.isGranted(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    selectPicture();
                } else {
                    isGranted();
                }
            }
        });

        mv_reset.setOnClickListener(v -> {

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

            scv_up_assets.setContent("");
            scv_up_assets.setHint(ResUtils.getString(R.string.up_assets));
            scv_up_assets.setTag(null);
            assetPop = null;

            photoMap.clear();
            ll_images.removeAllViews();

            cev_location.setContent("");
            cev_l.setContent("");
            cev_w.setContent("");
            cev_h.setContent("");
            cev_weight.setContent("");

            cev_color.setContent("");
            iv_color.setBackgroundColor(ResUtils.getColor(R.color.spsdk_transparent_00));
            iv_color.setTag(ColorUtils.int2RgbString(ResUtils.getColor(R.color.spsdk_color_blue)));

            cev_place_of_production.setContent("");

            scv_date_of_manufacture.setContent("");
            scv_warranty_period.setContent("");

            cev_remark.setContent("");
        });

        mv_confirm.setOnClickListener(v -> {

            if (ClickUtil.isFastDoubleClick()) {
                return;
            }

            String number = cev_number.getContent();
            if (TextUtils.isEmpty(number)) {
                showToast(ResUtils.getString(R.string.please_enter)+ ResUtils.getString(R.string.asset_number));
                return;
            }

            String name = cev_name.getContent();
            if (TextUtils.isEmpty(name)) {
                showToast(ResUtils.getString(R.string.please_enter) + ResUtils.getString(R.string.asset_name));
                return;
            }

            DictionaryBean typeVo = (DictionaryBean) scv_type.getTag();
            String deviceTypeId = "";
            if (typeVo != null) {
                deviceTypeId = typeVo.getId();
            } else {
                showToast(ResUtils.getString(R.string.please_enter) + ResUtils.getString(R.string.asset_type));
                return;
            }

            DictionaryBean brandVo = (DictionaryBean) scv_brand.getTag();
            String brandId = "";
            if (brandVo != null) {
                brandId = brandVo.getId();
            } else {
                showToast(ResUtils.getString(R.string.please_enter) + ResUtils.getString(R.string.brand));
                return;
            }

            DictionaryBean modelVo = (DictionaryBean) scv_model.getTag();
            String brandModelId = "";
            if (modelVo != null) {
                brandModelId = modelVo.getId();
            } else {
                showToast(ResUtils.getString(R.string.please_enter) + ResUtils.getString(R.string.model));
                return;
            }

            DeviceBean upAssetVo = (DeviceBean) scv_up_assets.getTag();
            String upAssetId = "";
            if (upAssetVo != null) {
                upAssetId = upAssetVo.getId();
            } else {
                showToast(ResUtils.getString(R.string.please_enter) + ResUtils.getString(R.string.up_assets));
                return;
            }

            String location = cev_location.getContent();
            String size_l = cev_l.getContent();
            String size_w = cev_w.getContent();
            String size_h = cev_h.getContent();
            String weight = cev_weight.getContent();

            String color = "";
            if (! TextUtils.isEmpty(cev_color.getContent())) {
                color = cev_color.getContent();
            }

            String place_of_production = cev_place_of_production.getContent();
            String date_of_manufacture = scv_date_of_manufacture.getContent();
            String warranty_period = scv_warranty_period.getContent();
            String remark = cev_remark.getContent();

            for (int i = 0; i < deviceFieldValueForms.size(); i++) {
                DeviceFieldValueBean vo = deviceFieldValueForms.get(i);
                SPCardEditView view = (SPCardEditView) ll_field.getChildAt(i);
                vo.setValue(view.getContent());
            }

            DeviceInfoForm it = new DeviceInfoForm();
            it.setId(deviceBean.getId());
            it.setDeviceNumber(number);
            it.setDeviceName(name);
            it.setDeviceTypeId(deviceTypeId);
            it.setBrandId(brandId);
            it.setBrandModelId(brandModelId);
            it.setParentId(upAssetId);
            it.setRemark(remark);
            it.setColor(color);
            it.setLocation(location);
            it.setProduction(place_of_production);
            it.setProdDate(DateTimeUtils.parseToLong(date_of_manufacture));
            it.setWarranty(DateTimeUtils.parseToLong(warranty_period));
            it.setWeight(weight);
            it.setDeviceFieldValueForms(deviceFieldValueForms);

            if (! TextUtils.isEmpty(cev_color.getContent())){
                it.setColor(cev_color.getContent());
            }

            List<String> documentIds = new ArrayList<>();
            Iterator<Map.Entry<String, String >> iterator = photoMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String > entry = iterator.next();
                documentIds.add(entry.getValue());
            }
            if (! documentIds.isEmpty()) {
                it.setDocumentIds(documentIds);
            }
            if (! TextUtils.isEmpty(size_l)){
                it.setLength(size_l);
            }

            if (! TextUtils.isEmpty(size_w)){
                it.setWidth(size_w);
            }

            if (! TextUtils.isEmpty(size_h)){
                it.setHeight(size_h);
            }

            LogUtils.json(it);
            mPresenter.updateDevice(it);
        });

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        if (bundle.containsKey("DeviceBean")) {
            DeviceBean it = (DeviceBean) bundle.getSerializable("DeviceBean");

            mPresenter.getData(it.getId());
        }
    }

    @Override
    public void getDataSuc(DeviceBean it) {
        deviceBean = it;
        LogUtils.json(it);
        mPresenter.assetType();
        mPresenter.assetBrand();
        mPresenter.deviceAll();

        cev_number.setContent(it.getDeviceNumber());
        cev_name.setContent(it.getDeviceName());
        cev_remark.setContent(it.getRemark());
        cev_location.setContent(it.getLocation());

        if (! TextUtils.isEmpty(it.getColor())) {
            cev_color.setContent(it.getColor());
            iv_color.setTag(it.getColor());
            iv_color.setBackgroundColor(ColorUtils.string2Int(it.getColor()));
        }

        if (it.getLength() != null) {
            cev_l.setContent(it.getLength());
        }

        if (it.getWidth() != null) {
            cev_w.setContent(it.getWidth());
        }

        if (it.getHeight() != null) {
            cev_h.setContent(it.getHeight());
        }

        LogUtils.i(cev_l.getContent(), cev_w.getContent(), cev_h.getContent());
        cev_weight.setContent(it.getWeight());

        cev_place_of_production.setContent(it.getProduction());

        if (it.getProductionDate() != null && it.getProductionDate() > 0) {
            scv_date_of_manufacture.setContent(DateTimeUtils.millis2Date(it.getProductionDate()));
        }
        if (it.getWarrantyDate() != null && it.getWarrantyDate() > 0) {
            scv_warranty_period.setContent(DateTimeUtils.millis2Date(it.getWarrantyDate()));
        }

        List<UrlsBean> urls = it.getUrls();
        ll_images.removeAllViews();
        for (int i = 0; i < urls.size(); i++) {
            String photoUrl = urls.get(i).getUrl();
            String photoID = urls.get(i).getId()+"";
            DeleteImageView deleteImageView = new DeleteImageView(this);
            deleteImageView.setDeleteTag(photoID);
            deleteImageView.setDeleteClick(v -> {
                new CustomDialogBuilder(this).setTitle(ResUtils.getString(R.string.is_delete_photo))
                        .setNegativeButton(null)
                        .setPositiveButton(R.string.confirm, (dialog, which) -> {
                            if (photoMap.containsKey(photoUrl)) {
                                photoMap.remove(photoUrl);
                                ll_images.removeView(deleteImageView);
                            }
                            dialog.dismiss();
                        }, true)
                        .create().show();
            });
            GlideUtils.showImage(deleteImageView.getImageView(), photoUrl);
            photoMap.put(photoUrl, photoID);
            ll_images.addView(deleteImageView);
        }

    }

    @Override
    public void deviceAllSuc(List<DeviceBean> it) {
        LogUtils.json(it);
        if (it.size() > 0) {
            assetPop = new ListPopWindow<>(scv_up_assets,this, it, vo -> {
                LogUtils.json(vo);
                scv_up_assets.setTag(vo);
                scv_up_assets.setContent(vo.getDeviceName());
            });

            for (int i = 0; i < it.size(); i++) {
                DeviceBean vo = it.get(i);
                if (vo.getId().equals(deviceBean.getParentId())) {
                    scv_up_assets.setTag(vo);
                    scv_up_assets.setContent(vo.getDeviceName());

                }
            }
        }
    }

    @Override
    public void deviceFieldSuc(List<FieldVo> it) {
        LogUtils.json(it);
        if (it == null || it.size() == 0) {
            return;
        }
        ll_field.removeAllViews();
        deviceFieldValueForms.clear();
        for (int i = 0; i < it.size(); i++) {
            FieldVo vo = it.get(i);
            DeviceFieldValueBean fieldValueBean = new DeviceFieldValueBean();
            fieldValueBean.setCode(vo.getCode());
            fieldValueBean.setFieldId(vo.getId());
            SPCardEditView v = new SPCardEditView(this);

            if (vo.getDeviceFieldValue() != null) {
                if (TextUtils.isEmpty(vo.getDeviceFieldValue().getValue())) {
                    v.setHint(vo.getFieldName());
                } else {
                    v.setContent(vo.getDeviceFieldValue().getValue());
                }
            }

//            类型 0：整数 1：小数 2：字符串 3：日期 4：单选 5：下拉
            switch (vo.getType()) {
                case 0:
                case 1:
                    v.setInputType(InputType.TYPE_CLASS_NUMBER);
                    break;
                case 2:
                    // 默认就是字符串
                    break;
                case 3:
                    v.setInputType(InputType.TYPE_CLASS_DATETIME);
                    break;
                case 4:
                    break;
                case 5:
                    break;
            }

            deviceFieldValueForms.add(fieldValueBean);
            ll_field.addView(v, new LinearLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));
        }
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

                    Map<String, String> map = new HashMap<>();
                    map.put("deviceId", deviceBean.getId());
                    map.put("deviceTypeId", vo.getFeatures());
                    mPresenter.deviceFieldValue(map);
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

    @Override
    public void uploadPhotoSus(Pair<List<String>, List<String>> it) {
        LogUtils.i(it);
        List<String> localPath = it.first;
        List<String> urlPath = it.second;
        for (int i = 0; i < localPath.size(); i++) {
            photoMap.put(localPath.get(i), urlPath.get(i));
        }
    }

    private void isGranted() {
        if (!PermissionUtils.isGranted(Manifest.permission.CAMERA)
                && !PermissionUtils.isGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            permission(PermissionConstants.CAMERA, PermissionConstants.STORAGE);
        } else if (!PermissionUtils.isGranted(Manifest.permission.CAMERA)) {
            permission(PermissionConstants.CAMERA);
        } else if (!PermissionUtils.isGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            permission(PermissionConstants.STORAGE);
        }
    }

    private void permission(@PermissionConstants.PermissionGroup final String... permissions) {
        PermissionUtils.permission(permissions)
                .callback(new PermissionUtils.FullCallback() {

                    @Override
                    public void onGranted(List<String> permissionsGranted) {
                        LogUtils.json(permissionsGranted);
                        selectPicture();
                    }

                    @Override
                    public void onDenied(List<String> permissionsDeniedForever, List<String> permissionsDenied) {
                        LogUtils.json(permissionsDenied);
                        showToast(R.string.spsdk_camera_storage_permission);
                    }
                }).request();
    }

    private void selectPicture() {
        if (ll_images.getChildCount() == Constant.MAX_SELECT_NUM) {
            showToast(R.string.photo_max6);
            return;
        }
        PictureSelector.create(this)
//                .openGallery(PictureMimeType.ofImage())
                .openCamera(PictureMimeType.ofImage())
                .imageEngine(new PicImageEngine())
                .maxSelectNum(Constant.MAX_SELECT_NUM - ll_images.getChildCount())
                .imageSpanCount(4)
                .selectionMode(PictureConfig.MULTIPLE)
                .isPreviewImage(true)
                .isCamera(true)
                .isCompress(true)
                .isEnableCrop(true)
                .withAspectRatio(1, 1)
                .isGif(true)
                .freeStyleCropEnabled(false)
                .showCropFrame(true)
                .showCropGrid(true)
                .rotateEnabled(false)
                .scaleEnabled(true)
                .cropImageWideHigh(Constant.CROP_IMAGE_WIDE_HIGH, Constant.CROP_IMAGE_WIDE_HIGH)
                .forResult(new OnResultCallbackListener<LocalMedia>() {

                    @Override
                    public void onResult(List<LocalMedia> result) {
                        if (result == null || result.isEmpty() || result.get(0) == null) return;

                        List<String> photoPaths = new ArrayList<>();
                        Files files = new Files();
                        for (int i = 0; i < result.size(); i++) {
                            LogUtils.i(result.get(i));
                            String photoPath = FileUtils.getPhotoPathFromLocal(result.get(i));
                            LogUtils.i(photoPath);
                            photoPaths.add(photoPath);

                            Uri sourceUri = Uri.fromFile(new File(photoPath));

                            DeleteImageView deleteImageView = new DeleteImageView(AssetEditActivity.this);
                            deleteImageView.setDeleteTag(photoPath);
                            deleteImageView.setDeleteClick(v -> {
                                new CustomDialogBuilder(AssetEditActivity.this).setTitle(ResUtils.getString(R.string.is_delete_photo))
                                        .setNegativeButton(null)
                                        .setPositiveButton(R.string.confirm, (dialog, which) -> {
                                            if (photoMap.containsKey(photoPath)) {
                                                photoMap.remove(photoPath);
                                                ll_images.removeView(deleteImageView);
                                            }
                                            dialog.dismiss();
                                        }, true).create().show();
                            });

                            Glide.with(AssetEditActivity.this).load(new File(photoPath)).into(deleteImageView.getImageView());
                            ll_images.addView(deleteImageView);

                            ContentResolver resolver = getContentResolver();

                            Bitmap mBitmap;

                            //1.将相册返回的uri转为Bitmap
                            try {
                                mBitmap = MediaStore.Images.Media.getBitmap(resolver, sourceUri);

                                //2.压缩图片,第二个入参表示图片压缩率，如果是100就表示不压缩
                                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                                mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);

                                //3.将压缩后的图片进行base64编码
                                byte[] bytes = bos.toByteArray();
                                String imgBase64 = Base64.encodeToString(bytes, Base64.DEFAULT);
                                LogUtils.i(imgBase64);

                                FileVo vo = new FileVo();
                                vo.setType(0);
                                vo.setExtension("jpeg");
                                vo.setBase64(imgBase64);
                                files.getDocumentForms().add(vo);
                            } catch (IOException e) {
                                e.printStackTrace();
                                showToast(R.string.picture_cancel);
                            }
                        }
                        if (files.getDocumentForms().size() > 0) {
                            mPresenter.uploadPhoto(photoPaths, files);
                        }
                    }

                    @Override
                    public void onCancel() {
                        showToast(ResUtils.getString(R.string.picture_cancel));
                    }

                });
    }

}