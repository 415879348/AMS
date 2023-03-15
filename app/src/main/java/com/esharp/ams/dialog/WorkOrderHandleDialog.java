package com.esharp.ams.dialog;

import android.Manifest;
import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.esharp.ams.R;
import com.esharp.ams.ui.CreateAssetActivity;
import com.esharp.ams.ui.WorkOrderDetailActivity;
import com.esharp.sdk.Constant;
import com.esharp.sdk.SPGlobalManager;
import com.esharp.sdk.base.BaseObserver;
import com.esharp.sdk.bean.request.FileVo;
import com.esharp.sdk.bean.request.Files;
import com.esharp.sdk.bean.response.HandlerVo;
import com.esharp.sdk.bean.response.UserVo;
import com.esharp.sdk.bean.response.WorkOrderBean;
import com.esharp.sdk.dialog.BaseAlertDialog;
import com.esharp.sdk.dialog.CustomDialogBuilder;
import com.esharp.sdk.dialog.ListPopWindow;
import com.esharp.sdk.http.HttpFunction;
import com.esharp.sdk.http.HttpService;
import com.esharp.sdk.rxjava.HttpResultOperator;
import com.esharp.sdk.rxjava.ProgressOperator;
import com.esharp.sdk.rxjava.SchedulerUtils;
import com.esharp.sdk.utils.ClickUtil;
import com.esharp.sdk.utils.FileUtils;
import com.esharp.sdk.utils.PicImageEngine;
import com.esharp.sdk.utils.ResUtils;
import com.esharp.sdk.widget.DeleteImageView;
import com.esharp.sdk.widget.MyTextView;
import com.esharp.sdk.widget.OnClickCallback;
import com.esharp.sdk.widget.SPNoteView;
import com.esharp.sdk.widget.SPSelectorView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.yanzhenjie.permission.AndPermission;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;

/**
 * 功能描述：添加申请
 *
 * @author (作者) someone
 * 创建时间： 2021/7/9
 */
public class WorkOrderHandleDialog extends BaseAlertDialog {
    private ImageView iv_cancel;
    private SPNoteView nv_remark;
    private SPSelectorView sv_selector;
    private MyTextView mv_end;
    private MyTextView mv_assign;
    private MyTextView mv_upload;
    private ListPopWindow<UserVo> selectorPopWindow;
    private List<UserVo> userList;
    private LinearLayout ll_images;
    private Map<String, String> photoMap = new HashMap<>();
    private WorkOrderDetailActivity mView;
    private WorkOrderBean mWorkOrderBean = null;

    public WorkOrderHandleDialog(@NonNull WorkOrderDetailActivity view, WorkOrderBean workOrderBean) {
        super(view, R.layout.dialog_work_order_handle);
        mView = view;
        mWorkOrderBean = workOrderBean;
        iv_cancel = findViewById(R.id.iv_cancel);
        nv_remark = findViewById(R.id.nv_remark);
        sv_selector = findViewById(R.id.sv_selector);
        mv_end = findViewById(R.id.mv_reset);
        mv_assign = findViewById(R.id.mv_assign);
        mv_upload = findViewById(R.id.mv_upload);
        ll_images = findViewById(R.id.ll_images);
        iv_cancel.setOnClickListener(v -> {
            cancel();
        });
        mv_upload.setOnClickListener(v -> {
            if (ClickUtil.isFastDoubleClick()) {
                return;
            }
            LogUtils.i(PermissionUtils.isGranted(Manifest.permission.CAMERA));
            LogUtils.i(PermissionUtils.isGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE));
            if (PermissionUtils.isGranted(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                selectPicture();
            } else {
                isGranted();
            }
        });

        HttpService.get()
                .userAll()
                .map(new HttpFunction<>())
                .compose(SchedulerUtils.io_main_single())
                .lift(new ProgressOperator<>(mView, -1))
                .subscribe(it -> {
                    userList = it;
                    if (userList.size() > 0) {
                        sv_selector.setTag(userList.get(0));

                        selectorPopWindow = new ListPopWindow<>(nv_remark, getContext(), userList, userVo -> {
                            LogUtils.json(userVo);
                            sv_selector.setTag(userVo);
                            sv_selector.setContent(userVo.getUsername());
                        });

                        sv_selector.setContent(userList.get(0).getUsername());
                    }
                });
    }

    public BaseAlertDialog setOnClickListener(OnClickCallback<HandlerVo> it, OnClickCallback<HandlerVo> it2) {
        mv_end.setOnClickListener(v-> {
            String remark = nv_remark.getContent();
            if (TextUtils.isEmpty(remark)) {
                ToastUtils.showShort(ResUtils.getString(R.string.please_enter)+ ResUtils.getString(R.string.remark));
                return;
            }
            new CustomDialogBuilder(mView)
                    .setTitle(ResUtils.getString(R.string.tab_title_work_order))
                    .setMessage(ResUtils.getString(R.string.spsdk_is_back))
                    .setNegativeButton(R.string.spsdk_cancel, (dialog, which) -> {
                        dialog.dismiss();
                    })
                    .setPositiveButton(R.string.spsdk_confirm, (dialog, which) -> {
                        HandlerVo vo = new HandlerVo();
                        vo.setContent(remark);
                        vo.setIsOver(0);
                        List<String> documentIds = new ArrayList<>();
                        Iterator<Map.Entry<String, String >> iterator = photoMap.entrySet().iterator();
                        while (iterator.hasNext()) {
                            Map.Entry<String, String > entry = iterator.next();
                            documentIds.add(entry.getValue());
                        }
                        if (! documentIds.isEmpty()) {
                            vo.setDocumentIds(documentIds);
                        }
                        it.onClick(vo);
                        dialog.dismiss();
                    }, true)
                    .create().show();
        });
        LogUtils.i(mWorkOrderBean.getApplyId(), SPGlobalManager.getUserVo().getId());
        if ((mWorkOrderBean.getApplyId() + "").equals(SPGlobalManager.getUserVo().getId())) {
            mv_assign.setText(ResUtils.getString(R.string.assign));
            mv_assign.setSolid(ResUtils.getColor(R.color.spsdk_color_blue));
            mv_assign.setOnClickListener(v-> {
                if (sv_selector.getVisibility() == View.GONE) {
                    sv_selector.setVisibility(View.VISIBLE);
                    mv_assign.setText(ResUtils.getString(R.string.confirm));
                    sv_selector.setOnClickListener(view -> {
                        UserVo bean = (UserVo) sv_selector.getTag();
                        selectorPopWindow.show(sv_selector, bean.getUsername());
                    });
                } else {
                    String remark = nv_remark.getContent();
                    if (TextUtils.isEmpty(remark)) {
                        ToastUtils.showShort(ResUtils.getString(R.string.please_enter)+ ResUtils.getString(R.string.remark));
                        return;
                    }
                    UserVo bean = (UserVo) sv_selector.getTag();
                    HandlerVo vo = new HandlerVo();
                    vo.setContent(remark);
                    vo.setIsOver(1);
                    vo.setProcessId(bean.getId());
                    List<String> documentIds = new ArrayList<>();
                    Iterator<Map.Entry<String, String >> iterator = photoMap.entrySet().iterator();
                    while (iterator.hasNext()) {
                        Map.Entry<String, String > entry = iterator.next();
                        documentIds.add(entry.getValue());
                    }
                    if (! documentIds.isEmpty()) {
                        vo.setDocumentIds(documentIds);
                    }
                    it2.onClick(vo);
                }
            });
        } else {
            mv_assign.setText(ResUtils.getString(R.string.go_back));
            mv_assign.setSolid(ResUtils.getColor(R.color.spsdk_color_red));
            mv_assign.setOnClickListener(v-> {
                new CustomDialogBuilder(mView)
                        .setTitle(ResUtils.getString(R.string.tab_title_work_order))
                        .setMessage(ResUtils.getString(R.string.spsdk_is_back))
                        .setNegativeButton(R.string.spsdk_cancel, (dialog, which) -> {
                            dialog.dismiss();
                        })
                        .setPositiveButton(R.string.spsdk_confirm, (dialog, which) -> {
                            String remark = nv_remark.getContent();
                            if (TextUtils.isEmpty(remark)) {
                                ToastUtils.showShort(ResUtils.getString(R.string.please_enter)+ ResUtils.getString(R.string.remark));
                                dialog.dismiss();
                                return;
                            }
                            HandlerVo vo = new HandlerVo();
                            vo.setContent(remark);
                            vo.setIsOver(1);
                            vo.setProcessId(mWorkOrderBean.getApplyId()+"");
                            List<String> documentIds = new ArrayList<>();
                            Iterator<Map.Entry<String, String >> iterator = photoMap.entrySet().iterator();
                            while (iterator.hasNext()) {
                                Map.Entry<String, String > entry = iterator.next();
                                documentIds.add(entry.getValue());
                            }
                            if (! documentIds.isEmpty()) {
                                vo.setDocumentIds(documentIds);
                            }
                            it2.onClick(vo);
                            dialog.dismiss();
                        }, true)
                        .create().show();

//                String remark = nv_remark.getContent();
//                if (TextUtils.isEmpty(remark)) {
//                    ToastUtils.showShort(ResUtils.getString(R.string.please_enter)+ ResUtils.getString(R.string.remark));
//                    return;
//                }
//                HandlerVo vo = new HandlerVo();
//                vo.setContent(remark);
//                vo.setIsOver(1);
//                vo.setProcessId(mWorkOrderBean.getApplyId()+"");
//                List<String> documentIds = new ArrayList<>();
//                Iterator<Map.Entry<String, String >> iterator = photoMap.entrySet().iterator();
//                while (iterator.hasNext()) {
//                    Map.Entry<String, String > entry = iterator.next();
//                    documentIds.add(entry.getValue());
//                }
//                if (! documentIds.isEmpty()) {
//                    vo.setDocumentIds(documentIds);
//                }
//                it2.onClick(vo);
            });
        }
        return this;
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
                        ToastUtils.showShort(R.string.spsdk_camera_storage_permission);
                    }
                }).request();
    }

    private void selectPicture() {
        if (ll_images.getChildCount() == Constant.MAX_SELECT_NUM) {
            ToastUtils.showShort(R.string.photo_max6);
            return;
        }
        PictureSelector.create(mView)
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

                            DeleteImageView deleteImageView = new DeleteImageView(mView);
                            deleteImageView.setDeleteTag(photoPath);
                            deleteImageView.setDeleteClick(v -> {
                                new CustomDialogBuilder(mView).setTitle(ResUtils.getString(R.string.is_delete_photo))
                                        .setNegativeButton(null)
                                        .setPositiveButton(R.string.confirm, (dialog, which) -> {
                                            if (photoMap.containsKey(photoPath)) {
                                                photoMap.remove(photoPath);
                                                ll_images.removeView(deleteImageView);
                                            }
                                            dialog.dismiss();
                                        }, true).create().show();
                            });

                            Glide.with(mView).load(new File(photoPath)).into(deleteImageView.getImageView());
                            ll_images.addView(deleteImageView);

                            ContentResolver resolver = mView.getContentResolver();

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
                                vo.setType(7);
                                vo.setExtension("jpeg");
                                vo.setBase64(imgBase64);
                                files.getDocumentForms().add(vo);
                            } catch (IOException e) {
                                e.printStackTrace();
                                ToastUtils.showShort(R.string.picture_cancel);
                            }
                        }

                        if (files.getDocumentForms().size() > 0) {
                            HttpService.get()
                                    .documentMore(files)
                                    .lift(new HttpResultOperator<>())
                                    .map(strings -> Pair.create(photoPaths, strings))
                                    .compose(SchedulerUtils.io_main_single())
                                    .lift(new ProgressOperator<>(mView, -1))
                                    .subscribe(it -> {
                                        LogUtils.json(it);
                                        List<String> localPath = it.first;
                                        List<String> urlPath = it.second;
                                        for (int i = 0; i < localPath.size(); i++) {
                                            photoMap.put(localPath.get(i), urlPath.get(i));
                                        }
                                    });
                        }
                    }

                    @Override
                    public void onCancel() {
                        ToastUtils.showShort(ResUtils.getString(R.string.picture_cancel));
                    }

                });
    }
}
