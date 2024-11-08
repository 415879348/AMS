package com.continental.sdk.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Build.VERSION;

import org.jetbrains.annotations.NotNull;

public class ResUtils {
    @NotNull
    private static Resources mRes;

    public static void init(Context context) {
        mRes = context.getResources();
    }

    @NotNull
    public static Resources getRes() {
        return ResUtils.mRes;
    }

    @NotNull
    public static String getString(int strId) {
        return mRes.getString(strId);
    }

    public static int getColor(int colorId) {
        return VERSION.SDK_INT >= 23 ? getRes().getColor(colorId, null) : getRes().getColor(colorId);
    }

    @NotNull
    public static Drawable getDrawable(int drawableId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return mRes.getDrawable(drawableId, null);
        } else {
            return mRes.getDrawable(drawableId);
        }
    }

    @NotNull
    public static Bitmap getBitmap(int bitmapId) {
        Drawable drawable = getDrawable(bitmapId);
        return getBitmap(drawable);
    }

    @NotNull
    public static Bitmap getBitmap(@NotNull Drawable drawable) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap bitmap = null;
        if (drawable.getOpacity() != PixelFormat.OPAQUE) {
            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        } else {
            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        }
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
        return bitmap;
    }

    public static int getDimenInPixel(int dimen) {
        return mRes.getDimensionPixelSize(dimen);
    }

}
