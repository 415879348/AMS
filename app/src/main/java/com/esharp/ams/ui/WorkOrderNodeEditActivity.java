package com.esharp.ams.ui;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Pair;
import android.widget.LinearLayout;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.bumptech.glide.Glide;
import com.esharp.ams.R;
import com.esharp.ams.contract.WorkOrderNodeEditContract;
import com.esharp.ams.eventbus.EventWorkOrder;
import com.esharp.ams.presenter.WorkOrderNodeEditPresenter;
import com.esharp.sdk.Constant;
import com.esharp.sdk.base.BaseMvpActivity;
import com.esharp.sdk.bean.request.FieldVo;
import com.esharp.sdk.bean.request.FileVo;
import com.esharp.sdk.bean.request.Files;
import com.esharp.sdk.bean.request.WorkOrderNodeVo;
import com.esharp.sdk.bean.response.HandlerVo;
import com.esharp.sdk.bean.response.NodeVo;
import com.esharp.sdk.bean.response.UrlsBean;
import com.esharp.sdk.dialog.CustomDialogBuilder;
import com.esharp.sdk.http.GlideUtils;
import com.esharp.sdk.utils.ClickUtil;
import com.esharp.sdk.utils.FileUtils;
import com.esharp.sdk.utils.PicImageEngine;
import com.esharp.sdk.utils.ResUtils;
import com.esharp.sdk.widget.DeleteImageView;
import com.esharp.sdk.widget.MyTextView;
import com.esharp.sdk.widget.RadiusImageView;
import com.esharp.sdk.widget.SPCardEditView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;

import org.greenrobot.eventbus.EventBus;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import androidx.activity.result.ActivityResultLauncher;

public class WorkOrderNodeEditActivity extends BaseMvpActivity<WorkOrderNodeEditContract.Presenter> implements WorkOrderNodeEditContract.View {

    @Override
    protected Pair<Integer, WorkOrderNodeEditContract.Presenter> layoutAndPresenter() {
        return Pair.create(R.layout.activity_work_order_node_edit, new WorkOrderNodeEditPresenter(this));
    }

    private NodeVo mNodeVo = null;

    private LinearLayout  ll_images;

    Map<String, String> photoMap = new HashMap<>();

    SPCardEditView cev_remark;

    MyTextView mv_reset, mv_confirm, mv_upload;

    public static void startActivity(Context context, NodeVo it, ActivityResultLauncher<Intent> mLauncher) {
        Intent intent = new Intent(context, WorkOrderNodeEditActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("NodeVo", it);
        intent.putExtras(bundle);
        mLauncher.launch(intent);
    }

    @Override
    protected void init() {
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        if (bundle.containsKey("NodeVo")) {
            mNodeVo = (NodeVo) bundle.getSerializable("NodeVo");
        }
        ll_images = findViewById(R.id.ll_images);
        cev_remark = findViewById(R.id.cev_remark);
        mv_upload = findViewById(R.id.mv_upload);
        mv_confirm = findViewById(R.id.mv_confirm);
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
        mv_confirm.setOnClickListener(v -> {
            HandlerVo vo = new HandlerVo();
            vo.setId(mNodeVo.getId());
            vo.setContent(cev_remark.getContent());
            List<String> documentIds = new ArrayList<>();
            Iterator<Map.Entry<String, String >> iterator = photoMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String > entry = iterator.next();
                documentIds.add(entry.getValue());
            }
            if (! documentIds.isEmpty()) {
                vo.setDocumentIds(documentIds);
            }
            mPresenter.nodeUpdate(vo);
        });
        mPresenter.nodeSearch(mNodeVo.getId());
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

                            DeleteImageView deleteImageView = new DeleteImageView(WorkOrderNodeEditActivity.this);
                            deleteImageView.setDeleteTag(photoPath);
                            deleteImageView.setDeleteClick(v -> {
                                new CustomDialogBuilder(WorkOrderNodeEditActivity.this).setTitle(ResUtils.getString(R.string.is_delete_photo))
                                        .setNegativeButton(null)
                                        .setPositiveButton(R.string.confirm, (dialog, which) -> {
                                            if (photoMap.containsKey(photoPath)) {
                                                photoMap.remove(photoPath);
                                                ll_images.removeView(deleteImageView);
                                            }
                                            dialog.dismiss();
                                        }, true).create().show();
                            });

                            Glide.with(WorkOrderNodeEditActivity.this).load(new File(photoPath)).into(deleteImageView.getImageView());
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

    @Override
    public void nodeSearchSuc(WorkOrderNodeVo vo) {
        LogUtils.json(vo);
        ll_images.removeAllViews();
        cev_remark.setContent(vo.getContent());
        List<UrlsBean> urls = vo.getUrls();
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
    public void nodeUpdateSuc(Boolean it) {
        if (it) {
            setResult(Activity.RESULT_OK, new Intent().putExtra(Constant.REFRESH_DATA, "success"));
            finish();
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
}