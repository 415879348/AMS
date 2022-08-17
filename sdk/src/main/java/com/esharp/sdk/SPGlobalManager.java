package com.esharp.sdk;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;

import com.esharp.sdk.bean.response.TokenVo;
import com.esharp.sdk.utils.LocalUtils;
import com.google.gson.Gson;

import java.util.LinkedList;
import java.util.List;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;

/**
 * 功能描述：
 *
 * @author (作者) someone
 * 创建时间： 2021/7/12
 */
public class SPGlobalManager {
    @SuppressLint("StaticFieldLeak")
    private static Context sContext;
    private static SharedPreferences sPreferences;
    private final static List<Activity> activityStack = new LinkedList<>();
    private static TokenVo mToken;
    private static Integer sNightMode;
    private static SPLocal slocal;
    private static Integer tint;
    private static String title;
    private static Integer bottomRes;

    static void init(Context context) {
        sContext = context;
        sPreferences = context.getSharedPreferences(Constant.SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    static void start(Context context, TokenVo token, SPConfig SPConfig) {
        mToken = token;
        sNightMode = SPConfig.getNightMode();
        tint = SPConfig.getTint();
        title = SPConfig.getTitle();
        bottomRes = SPConfig.getBottomRes();
        sPreferences
                .edit()
                .putString(Constant.Authorization, new Gson().toJson(token))
                .putInt(Constant.KEY_MODE_NIGHT, sNightMode)
                .putString(Constant.KEY_TITLE, title)
                .putInt(Constant.KEY_TINT, tint)
                .putInt(Constant.KEY_BOTTOM_RES, bottomRes)
                .apply();
        if (SPConfig.getLangCode() != null) {
            setLanguage(LocalUtils.getLocal(sContext, SPConfig.getLangCode()));
        }
//        context.startActivity(new Intent(context, MainActivity.class));
    }



    public static TokenVo getToken() {
        if (mToken == null) {
            mToken = new Gson().fromJson(sPreferences.getString(Constant.Authorization, null), TokenVo.class);
        }
        return mToken;
    }

    public static void setNightMode(@AppCompatDelegate.NightMode int nightMode) {
        sNightMode = nightMode;
        sPreferences
                .edit()
                .putInt(Constant.KEY_MODE_NIGHT, nightMode)
                .apply();
    }

    public static int getNightMode() {
        if (sNightMode == null) {
            sNightMode = sPreferences.getInt(Constant.KEY_MODE_NIGHT, AppCompatDelegate.MODE_NIGHT_NO);
        }
        return sNightMode;
    }

    public static SPLocal getLanguage() {
        if (slocal == null) {
            slocal = LocalUtils.getLocal(sContext, sPreferences.getString(Constant.KEY_LANGUAGE, null));
        }
        return slocal;
    }

    public static void setLanguage(SPLocal local) {
        SPGlobalManager.slocal = local;
        sPreferences.edit().putString(Constant.KEY_LANGUAGE, local.getCode()).apply();
    }

    public static String getTitle() {
        if (title == null) {
            title = sPreferences.getString(Constant.KEY_TITLE, "");
        }
        return title;
    }

    public static int getTint() {
        if (tint == null) {
            tint = sPreferences.getInt(Constant.KEY_TINT, ContextCompat.getColor(sContext, R.color.spsdk_second_color));
        }
        return tint;
    }

    public static int getBottomRes() {
        if (bottomRes == null) {
            bottomRes = sPreferences.getInt(Constant.KEY_BOTTOM_RES, 0);
        }
        return bottomRes;
    }

    public static void addActivity(Activity activity) {
        activityStack.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activityStack.remove(activity);
    }

    public static Activity getLastActivity() {
        return activityStack.size() > 0 ? activityStack.get(activityStack.size() - 1) : null;
    }

//    public static void backToMain() {
//        for (Activity activity : activityStack) {
//            if (activity instanceof MainActivity) {
//                break;
//            }
//            activity.finish();
//        }
//    }

    public static void restart() {
        Activity last = getLastActivity();
        PackageManager packageManager;
        Intent intent;
        if (last != null && (packageManager = last.getPackageManager()) != null &&
                (intent = packageManager.getLaunchIntentForPackage(last.getPackageName())) != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            for (Activity activity : activityStack) {
                activity.finish();
            }
            last.startActivity(intent);
        }
    }

    public static void logout(Class<?> cls) {
        sPreferences.edit().remove(Constant.Authorization).apply();
        mToken = null;
        Activity last = getLastActivity();
        for (Activity activity : activityStack) {
            activity.finish();
        }
        if (last != null)
            last.startActivity(new Intent(last, cls));
    }

    public static void logout() {
        sPreferences.edit().remove(Constant.Authorization).apply();
        mToken = null;
    }
}
