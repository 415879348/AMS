package com.continental.sdk;

import android.graphics.Color;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatDelegate;

/**
 * 功能描述：
 *
 * @author (作者) fengchuanfang
 * 创建时间： 2021/9/11
 */
public class SPConfig {
    //夜间模式切换逻辑
    private @AppCompatDelegate.NightMode int nightMode = AppCompatDelegate.MODE_NIGHT_NO;//AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;
    //语言
    private String langCode;
    //标题栏和按钮着色
    private @ColorInt int primaryColor = Color.parseColor("#93B3AD");
    //页面顶部标题
    private String title;
    //底部图片
    private @DrawableRes int bottomRes;

    int getNightMode() {
        return nightMode;
    }

    String getLangCode() {
        return langCode;
    }

    int getPrimaryColor() {
        return primaryColor;
    }

    String getTitle() {
        return title;
    }

    int getBottomRes() {
        return bottomRes;
    }

    public static Build newBuild(){
        return new Build();
    }

    public static class Build{
        private final SPConfig mSPConfig;
        public Build(){
            mSPConfig = new SPConfig();
        }

        public Build setNightMode(@AppCompatDelegate.NightMode int nightMode) {
            mSPConfig.nightMode = nightMode;
            return this;
        }

        public Build setLangCode(String langCode) {
            mSPConfig.langCode = langCode;
            return this;
        }

        public Build setPrimaryColor(@ColorInt int primaryColor) {
            //mSPConfig.primaryColor = primaryColor;
            return this;
        }

        public Build setTitle(String title) {
            mSPConfig.title = title;
            return this;
        }

        public Build setBottomRes(@DrawableRes int bottomRes) {
            //mSPConfig.bottomRes = bottomRes;
            return this;
        }

        public SPConfig build(){
            return mSPConfig;
        }
    }
}
