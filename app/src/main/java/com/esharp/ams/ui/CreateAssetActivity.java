package com.esharp.ams.ui;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
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
import com.esharp.ams.R;
import com.esharp.ams.contract.CreateAssetContract;
import com.esharp.ams.presenter.CreateAssetPresenter;
import com.esharp.sdk.Constant;
import com.esharp.sdk.base.BaseMvpActivity;
import com.esharp.sdk.bean.FileVo;
import com.esharp.sdk.bean.request.FieldVo;
import com.esharp.sdk.bean.response.DeviceBean;
import com.esharp.sdk.bean.response.DeviceFieldValueBean;
import com.esharp.sdk.bean.response.DeviceInfoForm;
import com.esharp.sdk.bean.response.DictionaryBean;
import com.esharp.sdk.dialog.CustomDialogBuilder;
import com.esharp.sdk.dialog.ListPopWindow;
import com.esharp.sdk.utils.DateTimeUtils;
import com.esharp.sdk.utils.FileUtils;
import com.esharp.sdk.utils.PicImageEngine;
import com.esharp.sdk.utils.ResUtils;
import com.esharp.sdk.widget.DateTimeSelector;
import com.esharp.sdk.widget.MyTextView;
import com.esharp.sdk.widget.SPCardEditView;
import com.esharp.sdk.widget.SPSelectorCardView;
import com.esharp.sdk.widget.SPTitleView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.activity.result.ActivityResultLauncher;
import top.defaults.colorpicker.ColorPickerPopup;

public class CreateAssetActivity extends BaseMvpActivity<CreateAssetContract.Presenter> implements CreateAssetContract.View {

    @Override
    protected Pair<Integer, CreateAssetContract.Presenter> layoutAndPresenter() {
        return Pair.create(R.layout.activity_create_asset, new CreateAssetPresenter(this));
    }

    public static void startActivity(Context context, ActivityResultLauncher<Intent> mLauncher) {
        mLauncher.launch(new Intent(context, CreateAssetActivity.class));
    }

    SPSelectorCardView scv_type, scv_brand, scv_model, scv_up_assets, scv_date_of_manufacture, scv_warranty_period;

    SPCardEditView cev_number, cev_name, cev_l, cev_w, cev_h, cev_weight, cev_location, cev_place_of_production, cev_remark, cev_color;

    MyTextView mtv_generate, mv_reset, mv_confirm, mv_upload1, mv_upload2, mv_upload3, mv_delete1, mv_delete2, mv_delete3;

    ImageView iv_picture1, iv_picture2, iv_picture3, iv_color;

    LinearLayout ll_field;

    String photoID1 = "";
    String photoID2 = "";
    String photoID3 = "";

    private ListPopWindow<DictionaryBean> assetTypePop, assetBrandPop, assetModelPop;
    private ListPopWindow<DeviceBean> assetPop;

    private List<DeviceFieldValueBean> deviceFieldValueForms = new ArrayList<>();

    public static final int REQUEST_1 = 101;
    public static final int REQUEST_2 = 102;
    public static final int REQUEST_3 = 103;

    @Override
    protected void init() {
        ((SPTitleView) findViewById(com.esharp.sdk.R.id.title_view)).setTitle(ResUtils.getString(R.string.asset_create));

        mtv_generate = findViewById(R.id.mtv_generate);
        cev_number = findViewById(R.id.cev_number);
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
        iv_color = findViewById(R.id.iv_color);
        mv_reset = findViewById(R.id.mv_reset);
        mv_confirm = findViewById(R.id.mv_confirm);

        mv_upload1 = findViewById(R.id.mv_upload1);
        mv_delete1 = findViewById(R.id.mv_delete1);
        iv_picture1 = findViewById(R.id.iv_picture1);
        mv_upload2 = findViewById(R.id.mv_upload2);
        mv_delete2 = findViewById(R.id.mv_delete2);
        iv_picture2 = findViewById(R.id.iv_picture2);
        mv_upload3 = findViewById(R.id.mv_upload3);
        mv_delete3= findViewById(R.id.mv_delete3);
        iv_picture3 = findViewById(R.id.iv_picture3);

        mtv_generate.setOnClickListener(v -> {
            mPresenter.generateCode();
        });

        iv_color.setTag(ColorUtils.int2RgbString(ResUtils.getColor(R.color.spsdk_color_blue)));
        iv_color.setOnClickListener(v -> {
            new ColorPickerPopup.Builder(this)
                .initialColor(ColorUtils.string2Int((String) v.getTag())) // Set initial color
                .enableBrightness(false) // Enable brightness slider or not
                .enableAlpha(false) // Enable alpha slider or not
                .okTitle(ResUtils.getString(R.string.choose))
                .cancelTitle(ResUtils.getString(R.string.cancel))
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
                mPresenter.deviceAll();
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

         mv_delete1.setOnClickListener(v -> {
            new CustomDialogBuilder(CreateAssetActivity.this).setTitle(ResUtils.getString(R.string.is_delete_photo))
                    .setNegativeButton(null)
                    .setPositiveButton(R.string.confirm, (dialog, which) -> {
                        photoID1 = "";
                        Glide.with(CreateAssetActivity.this).load(R.mipmap.spsdk_pic_error).into(iv_picture1);
                        dialog.dismiss();
                    }, true).create().show();
        });

        mv_delete2.setOnClickListener(v -> {
            new CustomDialogBuilder(CreateAssetActivity.this).setTitle(ResUtils.getString(R.string.is_delete_photo))
                    .setNegativeButton(null)
                    .setPositiveButton(R.string.confirm, (dialog, which) -> {
                        photoID2 = "";
                        Glide.with(CreateAssetActivity.this).load(R.mipmap.spsdk_pic_error).into(iv_picture2);
                        dialog.dismiss();
                    }, true).create().show();
        });

        mv_delete3.setOnClickListener(v -> {
            new CustomDialogBuilder(CreateAssetActivity.this).setTitle(ResUtils.getString(R.string.is_delete_photo))
                    .setNegativeButton(null)
                    .setPositiveButton(R.string.confirm, (dialog, which) -> {
                        photoID3 = "";
                        Glide.with(CreateAssetActivity.this).load(R.mipmap.spsdk_pic_error).into(iv_picture3);
                        dialog.dismiss();
                    }, true).create().show();
        });

        mv_upload1.setOnClickListener(v -> {
            LogUtils.i(PermissionUtils.isGranted(Manifest.permission.CAMERA));
            LogUtils.i(PermissionUtils.isGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE));
            // 手动禁止权限下次会主动弹出权限窗口，代码禁止权限下次不会主动弹出权限窗口
            if (PermissionUtils.isGranted(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                selectPicture(iv_picture1, REQUEST_1);
            } else {
                isGranted(iv_picture1, REQUEST_1);
            }
        });

        mv_upload2.setOnClickListener(v -> {
            if (PermissionUtils.isGranted(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                selectPicture(iv_picture2, REQUEST_2);
            } else {
                isGranted(iv_picture2, REQUEST_2);
            }
        });

        mv_upload3.setOnClickListener(v -> {
            if (PermissionUtils.isGranted(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                selectPicture(iv_picture3, REQUEST_3);
            } else {
                isGranted(iv_picture3, REQUEST_3);
            }
        });

        mv_reset.setOnClickListener(v -> {
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

            scv_up_assets.setContent("");
            scv_up_assets.setHint(ResUtils.getString(R.string.up_assets));
            scv_up_assets.setTag(null);
            assetPop = null;

            photoID1 = "";
            Glide.with(CreateAssetActivity.this).load(R.mipmap.spsdk_pic_error).into(iv_picture1);
            photoID2 = "";
            Glide.with(CreateAssetActivity.this).load(R.mipmap.spsdk_pic_error).into(iv_picture2);
            photoID3 = "";
            Glide.with(CreateAssetActivity.this).load(R.mipmap.spsdk_pic_error).into(iv_picture3);

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
            it.setDeviceNumber(number);
            it.setDeviceName(name);
            it.setRemark(remark);
            it.setDeviceTypeId(deviceTypeId);
            it.setBrandId(brandId);
            it.setBrandModelId(brandModelId);
            it.setParentId(upAssetId);
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
            if (!TextUtils.isEmpty(photoID1)) {
                documentIds.add(photoID1);
            }
            if (!TextUtils.isEmpty(photoID2)) {
                documentIds.add(photoID2);
            }
            if (!TextUtils.isEmpty(photoID3)) {
                documentIds.add(photoID3);
            }
            it.setDocumentIds(documentIds);

            if (! TextUtils.isEmpty(size_l)){
                it.setLength(size_l);
            }

            if (! TextUtils.isEmpty(size_w)){
                it.setLength(size_w);
            }

            if (! TextUtils.isEmpty(size_h)){
                it.setLength(size_h);
            }

            LogUtils.json(it);

            mPresenter.addAppDevice(it);
        });

        mPresenter.assetType();
        mPresenter.assetBrand();
        mPresenter.deviceAll();
    }

    @Override
    public void addAppDeviceSuc(boolean it) {
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

                mPresenter.deviceField(vo.getFeatures());
            });
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
            scv_model.setTag(it.get(0));
            scv_model.setContent(it.get(0).getDictName());
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
        }
    }

    @Override
    public void deviceFieldSuc(List<FieldVo> it) {
        LogUtils.json(it);
        ll_field.removeAllViews();
        deviceFieldValueForms.clear();
        for (int i = 0; i < it.size(); i++) {
            FieldVo vo = it.get(i);
            DeviceFieldValueBean fieldValueBean = new DeviceFieldValueBean();
            fieldValueBean.setCode(vo.getCode());
            fieldValueBean.setFieldId(vo.getId());
            SPCardEditView v = new SPCardEditView(CreateAssetActivity.this);
            v.setHint(vo.getFieldName());
            deviceFieldValueForms.add(fieldValueBean);
            ll_field.addView(v, new LinearLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));
        }
    }

    @Override
    public void generateCodeSuc(String it) {
        LogUtils.json(it);
        cev_number.setContent(it);
    }

    @Override
    public void uploadPhotoSus1(String it) {
        photoID1 = it;
    }

    @Override
    public void uploadPhotoSus2(String it) {
        photoID2 = it;
    }

    @Override
    public void uploadPhotoSus3(String it) {
        photoID3 = it;
    }

    private void isGranted(ImageView iv, int request) {
        if (!PermissionUtils.isGranted(Manifest.permission.CAMERA)
                && !PermissionUtils.isGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            permission(request, iv, PermissionConstants.CAMERA, PermissionConstants.STORAGE);
        } else if (!PermissionUtils.isGranted(Manifest.permission.CAMERA)) {
            permission(request, iv, PermissionConstants.CAMERA);
        } else if (!PermissionUtils.isGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            permission(request, iv, PermissionConstants.STORAGE);
        }
    }

    private void permission(int request, ImageView iv, @PermissionConstants.Permission final String... permissions) {
        PermissionUtils.permission(permissions)
                .callback(new PermissionUtils.FullCallback() {

                    @Override
                    public void onGranted(List<String> permissionsGranted) {
                        LogUtils.json(permissionsGranted);
                        selectPicture(iv, request);
                    }

                    @Override
                    public void onDenied(List<String> permissionsDeniedForever, List<String> permissionsDenied) {
                        LogUtils.json(permissionsDenied);
                        showToast(R.string.spsdk_camera_storage_permission);
                    }
                }).request();
    }

    private void selectPicture(ImageView iv, int request) {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .imageEngine(new PicImageEngine())
                .maxSelectNum(1)
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
                .cropImageWideHigh(SizeUtils.dp2px( 60), SizeUtils.dp2px( 60))
                .forResult(new OnResultCallbackListener<LocalMedia>() {

                    @Override
                    public void onResult(List<LocalMedia> result) {
                        if (result == null || result.isEmpty() || result.get(0) == null) return;
                        String photoPath = FileUtils.getPhotoPathFromLocal(result.get(0));
                        LogUtils.i(result.get(0));
                        LogUtils.i(photoPath);

                        Uri sourceUri = Uri.fromFile(new File(photoPath));

                        Glide.with(CreateAssetActivity.this).load(new File(photoPath)).into(iv);

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

//                            switch (request) {
//                                case  REQUEST_1:
//                                    if (!TextUtils.isEmpty(photoID1)) {
//                                        vo.setId(photoID1);
//                                    }
//                                    break;
//                                case  REQUEST_2:
//                                    if (!TextUtils.isEmpty(photoID2)) {
//                                        vo.setId(photoID2);
//                                    }
//                                    break;
//                                case  REQUEST_3:
//                                    if (!TextUtils.isEmpty(photoID3)) {
//                                        vo.setId(photoID3);
//                                    }
//                                    break;
//                            }

                            vo.setType(0);
                            vo.setExtension("jpeg");
                            vo.setBase64(imgBase64);
                            mPresenter.uploadPhoto(request, vo);

                        } catch (IOException e) {
                            e.printStackTrace();
                            showToast(R.string.picture_cancel);
                        }
                    }

                    @Override
                    public void onCancel() {
                        showToast(ResUtils.getString(R.string.picture_cancel));
                    }

                });
    }

}