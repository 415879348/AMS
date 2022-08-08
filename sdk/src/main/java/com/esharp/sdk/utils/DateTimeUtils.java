package com.esharp.sdk.utils;

import com.blankj.utilcode.util.TimeUtils;
import com.esharp.sdk.SPGlobalManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 功能描述：
 *
 * @author (作者) fengchuanfang
 * 创建时间： 2021/7/20
 */
public final class DateTimeUtils {

    public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    public static final String yyyyMMddHHmm = "yyyyMMddHHmm";
    public static String formatHHmmss(String input) {
        return format(input, "yyyyMMddHHmmss", "dd/MM/yyyy HH:mm:ss");
    }

    public static String formatHHmm_a(String input) {
        return format(input, "yyyyMMddHHmmss", "dd/MM/yyyy HH:mm a");
    }

    public static String format(String input, String inputFormat, String outputFormat) {
        Date date;
        Locale locale = SPGlobalManager.getLanguage().getLocale();
        try {
            date = new SimpleDateFormat(inputFormat, locale).parse(input);
        } catch (Exception e) {
            return "";
        }
        if (date == null) return "";
        return new SimpleDateFormat(outputFormat, locale).format(date);
    }

    public static long getForwardTimeMillis(long currentTimeMillis) {
        return getForwardTimeMillis(currentTimeMillis, 1);
    }

    public static long getForwardTimeMillis(long currentTimeMillis, int step) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(currentTimeMillis));
        c.add(Calendar.MONTH, step);
        Date date = c.getTime();
        return date.getTime();
    }

    public static long parseToLong(String input) {
        Date date = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss, Locale.ROOT);
            dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
            date = dateFormat.parse(input);
        } catch (Exception e) {
            return 0;
        }
        if (date == null) return 0;
        return date.getTime();
    }

    public static long parseToLong(String input, String pattern) {
        Date date = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, Locale.ROOT);
            dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
            date = dateFormat.parse(input);
        } catch (Exception e) {
            return 0;
        }
        if (date == null) return 0;
        return date.getTime();
    }

    public static String millis2Date(long millis) {
        return TimeUtils.millis2String(millis, yyyy_MM_dd_HH_mm_ss);
    }


    public static String format(int seconds) {
        StringBuffer buffer = new StringBuffer();
        int hour = seconds / 3600;
        int minutes = seconds % 3600;
        if (hour < 10) buffer.append("0");
        buffer.append(hour).append(":");
        int minute = minutes / 60;
        if (minute < 10) buffer.append("0");
        buffer.append(minute).append(":");
        int second = minutes % 60;
        if (second < 10) buffer.append("0");
        return buffer.append(second).toString();
    }

    public static String formate_m_H(int minutes) {
        StringBuffer buffer = new StringBuffer();
        int hour = minutes / 60;
        minutes = minutes % 60;
        if (hour < 10) buffer.append("0");
        buffer.append(hour).append(":");
        if (minutes < 10) buffer.append("0");
        return buffer.append(minutes).toString();
    }

    public static String formate_m_s(int seconds) {
        StringBuffer buffer = new StringBuffer();
        int minute = seconds / 60;
        if (minute < 10) buffer.append("0");
        buffer.append(minute).append("'");
        int second = seconds % 60;
        if (second < 10) buffer.append("0");
        return buffer.append(second).append("\"").toString();
    }
}
