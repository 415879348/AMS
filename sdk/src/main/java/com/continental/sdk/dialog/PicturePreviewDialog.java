package com.continental.sdk.dialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.batchat.preview.PreviewImage;
import com.batchat.preview.PreviewPictureView;
import com.batchat.preview.PreviewTools;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.continental.sdk.Constant;
import com.continental.sdk.R;
import com.continental.sdk.SPGlobalManager;
import com.continental.sdk.base.BaseActivity;
import com.continental.sdk.bean.response.Token;
import com.continental.sdk.http.GlideUtils;
import com.continental.sdk.http.IHttpURL;
import com.continental.sdk.widget.DragImageView;
import com.continental.sdk.widget.MyImageView;

import java.io.File;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import io.reactivex.rxjava3.annotations.Nullable;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Cancellable;

/**
 * 功能描述：添加申请
 *
 * @author (作者) someone
 * 创建时间： 2021/7/9
 */
public class PicturePreviewDialog extends BaseAlertDialog {
//    private PreviewPictureView<String> picture_temp;
//    private DragImageView picture_temp;
    private RelativeLayout container;

    public PicturePreviewDialog(@NonNull Context context, String imageUrl) {
        super(context, R.layout.spsdk_dialog_picture_preview);
        container = findViewById(R.id.container);
//        picture_temp = findViewById(R.id.picture_temp);

        FrameLayout.LayoutParams linearParams =(FrameLayout.LayoutParams) container.getLayoutParams(); //取控件textView当前的布局参数 linearParams.height = 20;// 控件的高强制设成20

        linearParams.height = ScreenUtils.getScreenHeight() / 2;// 控件的宽强制设成30

        container.setLayoutParams(linearParams); //使设置好的布局参数应用到控件



//        ArrayList<String> list = new ArrayList();
////        list.add(IHttpURL.getImageUrl(imageUrl));
//        list.add("https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%9B%BE%E7%89%87&hs=0&pn=0&spn=0&di=7189064908862914561&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&ie=utf-8&oe=utf-8&cl=2&lm=-1&cs=1204793430%2C3263400171&os=3423103612%2C2074051226&simid=1204793430%2C3263400171&adpicid=0&lpn=0&ln=30&fr=ala&fm=&sme=&cg=&bdtype=0&oriquery=%E5%9B%BE%E7%89%87&objurl=https%3A%2F%2Fup.deskcity.org%2Fpic_source%2F2f%2Ff4%2F42%2F2ff442798331f6cc6005098766304e39.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3B1jfhvtpy_z%26e3B562AzdH3F15ogs5w1AzdH3Fdd9m0m-a-axa_z%26e3Bip4s&gsm=&islist=&querylist=&dyTabStr=MCwzLDYsMSw0LDUsMiw3LDgsOQ%3D%3D");
//        // 根据单个图片来进行适配，推荐使用这种方法，因为在日常开发中，特别是聊天页面，RecyclerView不可能全是图片
//        if (context instanceof Activity) {
//            Activity activity = (Activity) context;
//            PreviewTools.INSTANCE.startImagePreview(activity, list, picture_temp, 0);
//        }

//        Token token = SPGlobalManager.getToken();
//        Object load = new GlideUrl(IHttpURL.getImageUrl(imageUrl), new LazyHeaders.Builder()
//                .addHeader(Constant.Authorization, token == null ? Constant.TOKEN_HEAD_BEARER : Constant.TOKEN_HEAD_BEARER + token.getToken())
//                .build());
//        Glide.with(context).load(load).listener(new RequestListener<>() {
//            @Override
//            public boolean onLoadFailed(@androidx.annotation.Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                return false;
//            }
//
//            @Override
//            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                picture_temp.setImageBitmap(ConvertUtils.drawable2Bitmap(resource));
//                return false;
//            }
//        });
//        GlideUtils.showImage(picture_temp, imageUrl);

        GlideUtils.downLoadDrawable(context, imageUrl, new SingleEmitter<>() {
            @Override
            public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull Drawable drawable) {
//                picture_temp.setDrawable(drawable);

                DragImageView mView = new DragImageView(context);

                mView.setDrawable(drawable);
                container.addView(mView);
            }

            @Override
            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable t) {

            }

            @Override
            public void setDisposable(@Nullable Disposable d) {

            }

            @Override
            public void setCancellable(@Nullable Cancellable c) {

            }

            @Override
            public boolean isDisposed() {
                return false;
            }

            @Override
            public boolean tryOnError(@io.reactivex.rxjava3.annotations.NonNull Throwable t) {
                return false;
            }
        });

        container.setOnClickListener(v -> {
            dismiss();
        });
        setCanceledOnTouchOutside(true);
    }

}
