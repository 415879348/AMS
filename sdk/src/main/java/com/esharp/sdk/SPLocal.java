package com.esharp.sdk;

import com.esharp.sdk.dialog.ListPopWindow;

import java.util.Locale;

/**
 * 功能描述：
 *
 * @author (作者) someone
 * 创建时间： 2021/9/8
 */
public class SPLocal extends ListPopWindow.Item {
    private final String code;
    private final String name;
    private final Locale locale;

    public SPLocal(String code, String name, Locale locale) {
        this.code = code;
        this.name = name;
        this.locale = locale;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Locale getLocale() {
        return locale;
    }

    @Override
    public void setChecked(String checkedCode) {
        this.checked = this.code.equals(checkedCode);
    }

    @Override
    public String text() {
        return name;
    }
}
