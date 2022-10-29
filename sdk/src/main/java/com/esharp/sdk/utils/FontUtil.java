package com.esharp.sdk.utils;

import android.app.Activity;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

import com.blankj.utilcode.util.LogUtils;

import java.net.URLDecoder;

import androidx.fragment.app.Fragment;

/**
 * 功能描述：
 *
 * @author (作者) someone
 * 创建时间： 2021/7/12
 */
public class FontUtil {

    public static void initFontScale(Activity activity) {
        Configuration configuration = activity.getResources().getConfiguration();

        //0.85 小, 1 标准大小, 1.15 大，1.3 超大 ，1.45 特大
        configuration.fontScale = (float) 1;

        DisplayMetrics metrics = new DisplayMetrics();

        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        metrics.scaledDensity = configuration.fontScale * metrics.density;

        activity.getBaseContext().getResources().updateConfiguration(configuration, metrics);
    }

}
