package com.esharp.sdk.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * 功能描述：
 *
 * @author (作者) fengchuanfang
 * 创建时间： 2021/8/13
 */
public class FileUtils {

    public static String getFileCache(Context context) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            File externalFile = context.getExternalCacheDir();
            if (externalFile != null) return externalFile.getAbsolutePath();
        }
        return context.getCacheDir().getAbsolutePath();
    }

    public static String getFileDir(Context context, String type) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            File externalFile = context.getExternalFilesDir(type);
            if (externalFile != null) return externalFile.getAbsolutePath();
        }
        return context.getFilesDir().getAbsolutePath();
    }

    public static String saveImage(Context context, Bitmap bitmap) {
        File file = new File(getFileDir(context, Environment.DIRECTORY_PICTURES));
        if (!file.isDirectory()) file.mkdir();
        String fileName = file.getAbsolutePath() + File.separator + System.currentTimeMillis() + ".jpg";
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fos);
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }

    public static String saveShareImage(Context context, Bitmap bitmap) {
        String fileName = saveImage(context, bitmap);
        String uri = null;
        try {
            uri = MediaStore.Images.Media.insertImage(context.getContentResolver(), fileName, fileName.substring(fileName.lastIndexOf(File.separator)), null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri == null ? Uri.parse("file://" + fileName) : Uri.parse(uri)));
        return fileName;
    }

    public static String getPhotoPathFromLocal(LocalMedia media) {
        String path;
        if (media.isCut() && !media.isCompressed()) {
            path = media.getCutPath();// 裁剪过
        } else if (media.isCompressed() || (media.isCut() && media.isCompressed())) {
            path = media.getCompressPath();// 压缩过,或者裁剪同时压缩过,以最终压缩过图片为准
        } else {
            path = media.getPath();// 原图
        }
        return PictureMimeType.isContent(path) && !media.isCut() && !media.isCompressed() ? (TextUtils.isEmpty(media.getAndroidQToPath()) ? media.getRealPath() : media.getAndroidQToPath()) : path;
    }

}
