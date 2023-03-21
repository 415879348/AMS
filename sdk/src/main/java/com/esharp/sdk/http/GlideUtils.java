package com.esharp.sdk.http;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.signature.ObjectKey;
import com.esharp.sdk.Constant;
import com.esharp.sdk.R;
import com.esharp.sdk.SPGlobalManager;
import com.esharp.sdk.bean.response.Token;

import java.io.File;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.reactivex.rxjava3.core.SingleEmitter;


/**
 * 功能描述：
 *
 * @author (作者) someone
 * 创建时间： 2021/8/8
 */
public class GlideUtils {

    public static void showImage(ImageView imageView, String subUrl) {
        if (TextUtils.isEmpty(subUrl)) {
            return;
        }
        Token token = SPGlobalManager.getToken();
        LogUtils.i(IHttpURL.getImageUrl(subUrl));
//        LogUtils.i(token == null ? "hrss-token" : token.getToken(), token == null ? "hrss-" : token.getToken() + token.getToken());

        Glide.with(imageView.getContext()).load(
                new GlideUrl(IHttpURL.getImageUrl(subUrl), new LazyHeaders.Builder()
                        .addHeader(Constant.Authorization, token == null ? Constant.TOKEN_HEAD_BEARER : Constant.TOKEN_HEAD_BEARER + token.getToken())
                        .build()))
                .error(R.mipmap.spsdk_pic_error)
                .into(imageView);
    }

    public static void showHeadIcon(ImageView imageView,String subUrl){
        Glide.with(imageView.getContext()).load(IHttpURL.LOGIN_URL.substring(0, IHttpURL.LOGIN_URL.indexOf("/server/")) + subUrl)
                .error(R.mipmap.spsdk_pic_error)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    public static void showQrCode(ImageView imageView, String url) {
        Token token = SPGlobalManager.getToken();
        Glide.with(imageView.getContext()).load(
                new GlideUrl(url, new LazyHeaders.Builder()
                        .addHeader(Constant.Authorization, token == null ? Constant.TOKEN_HEAD_BEARER : Constant.TOKEN_HEAD_BEARER + token.getToken())
                        .build()))
                .error(R.mipmap.spsdk_pic_error)
                .signature(new ObjectKey(System.currentTimeMillis()))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView);
    }

    public static void downLoadFile(Context context, String url, SingleEmitter<File> emitter) {
        Token token = SPGlobalManager.getToken();
        Glide.with(context)
                .asFile()
                .load(new GlideUrl(url, new LazyHeaders.Builder()
                        .addHeader(Constant.Authorization, token == null ? Constant.TOKEN_HEAD_BEARER : Constant.TOKEN_HEAD_BEARER + token.getToken())
                        .build()))
                .error(R.mipmap.spsdk_pic_error)
                .into(new CustomTarget<File>() {
                    @Override
                    public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
                        emitter.onSuccess(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);
                        emitter.onError(new Exception());
                    }
                });
    }

    public static void downLoadImage(Context context, String url, SingleEmitter<Bitmap> emitter) {
        Token token = SPGlobalManager.getToken();
        Glide.with(context)
                .asBitmap()
                .load(new GlideUrl(IHttpURL.getImageUrl(url), new LazyHeaders.Builder()
                        .addHeader(Constant.Authorization, token == null ? Constant.TOKEN_HEAD_BEARER : Constant.TOKEN_HEAD_BEARER + token.getToken())
                        .build()))
                .error(R.mipmap.spsdk_pic_error)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        emitter.onSuccess(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                        emitter.onError(new Exception());
                    }
                });
    }

    public static void downLoadDrawable(Context context, String url, SingleEmitter<Drawable> emitter) {
        Token token = SPGlobalManager.getToken();
        Glide.with(context)
                .asDrawable()
                .load(new GlideUrl(IHttpURL.getImageUrl(url), new LazyHeaders.Builder()
                        .addHeader(Constant.Authorization, token == null ? Constant.TOKEN_HEAD_BEARER : Constant.TOKEN_HEAD_BEARER + token.getToken())
                        .build()))
                .error(R.mipmap.spsdk_pic_error)
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        emitter.onSuccess(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                        emitter.onError(new Exception());
                    }
                });
    }
}
