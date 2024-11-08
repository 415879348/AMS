package com.continental.sdk.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.LocaleList;
import android.text.TextUtils;

import com.blankj.utilcode.util.LogUtils;
import com.continental.sdk.Constant;
import com.continental.sdk.SPGlobalManager;
import com.continental.sdk.SPLocal;
import java.util.Locale;

/**
 * 功能描述：
 *
 * @author (作者) someone
 * 创建时间： 2021/9/9
 */
public class LocalUtils {

    public static void initLocal(Context context) {
//        Locale locale = SPGlobalManager.getLanguage().getLocale();
//        if (!locale.equals(getCurrentLocale(context))) {
//            Locale.setDefault(locale);
//            Locale locale = getCurrentLocale(context);
//            Configuration configuration = context.getResources().getConfiguration();
//            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
//                configuration.setLocales(new LocaleList(locale));
//            } else {
//                configuration.setLocale(locale);
//            }
//            DisplayMetrics _displaymetrics = context.getResources().getDisplayMetrics();
//            context.getResources().updateConfiguration(configuration, _displaymetrics);
//        }

        Locale locale = SPGlobalManager.getLanguage().getLocale();
        LogUtils.json(locale);
        Configuration configuration = context.getResources().getConfiguration();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
            configuration.setLocales(new LocaleList(locale));
        } else {
            configuration.setLocale(locale);
        }
        context.getResources().updateConfiguration(configuration, context.getResources().getDisplayMetrics());
    }

    //获取当前语言
    private static Locale getCurrentLocale(Context context) {
        Locale locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { //7.0有多语言设置获取顶部的语言
            locale = context.getResources().getConfiguration().getLocales().get(0);
        } else {
            locale = context.getResources().getConfiguration().locale;
        }
        return locale;
    }

    //获取系统语言
    public static SPLocal getSystemLocal(Context context) {
        Locale sysLocale = getCurrentLocale(context);
        String language = sysLocale.getLanguage();
        if ("zh".equalsIgnoreCase(language)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { //7.0有多语言设置获取顶部的语言
                String toLanguageTag = sysLocale.toLanguageTag();
                if (toLanguageTag.contains("Hans")) {
                    return Constant.SC;
                } else {
                    return Constant.TC;
                }
            } else {
                String country = sysLocale.getCountry();
                if (TextUtils.isEmpty(country) || country.equalsIgnoreCase("cn")) {
                    return Constant.SC;
                } else {
                    return Constant.TC;
                }
            }
        } else {
            return Constant.EN;
        }
    }

    public static SPLocal getCustomLocal(String code) {
        for (SPLocal local : Constant.language) {
            if (local.getCode().equals(code)) {
                return local;
            }
        }
        return null;
    }

    public static SPLocal getLocal(Context context, String code) {
        if (code == null) return getSystemLocal(context);
        SPLocal spLocal = getCustomLocal(code);
        if (spLocal == null) {
            return getSystemLocal(context);
        } else {
            return spLocal;
        }
    }
}
