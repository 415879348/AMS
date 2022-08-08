package com.esharp.sdk;

import android.graphics.Color;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatDelegate;

/**
 * 功能描述：
 *
 * @author (作者) someone
 * 创建时间： 2021/9/11
 */
public class SPConfig {
    //夜间模式切换逻辑
    private @AppCompatDelegate.NightMode int nightMode = AppCompatDelegate.MODE_NIGHT_NO;//AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;
    //语言
    private String langCode;
    //标题栏和按钮着色
    private @ColorInt
    int tint = Color.parseColor("#93B3AD");
    //页面顶部标题
    private String title;
    //底部图片
    private @DrawableRes
    int bottomRes;



    int getNightMode() {
        return nightMode;
    }

    String getLangCode() {
        return langCode;
    }

    int getTint() {
        return tint;
    }

    String getTitle() {
        return title;
    }

    int getBottomRes() {
        return bottomRes;
    }

    public SPConfig setNightMode(int nightMode) {
        this.nightMode = nightMode;
        return this;
    }

    public SPConfig setLangCode(String langCode) {
        this.langCode = langCode;
        return this;
    }

    public SPConfig setTint(int tint) {
        this.tint = tint;
        return this;
    }

    public SPConfig setTitle(String title) {
        this.title = title;
        return this;
    }

    public SPConfig setBottomRes(int bottomRes) {
        this.bottomRes = bottomRes;
        return this;
    }
}
