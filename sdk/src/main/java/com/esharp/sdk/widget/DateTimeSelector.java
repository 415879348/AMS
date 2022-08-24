package com.esharp.sdk.widget;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;

import com.blankj.utilcode.util.LogUtils;
import com.esharp.sdk.utils.DateTimeUtils;

import java.util.Calendar;

public class DateTimeSelector {

    public DateTimeListener mListener = null;

    DatePickerDialog datePickerDialog = null;

    public interface DateTimeListener {
        void onDateTimeSelected(String datetime);
    }

    public DateTimeSelector(Context context, DateTimeListener listener) {
        mListener = listener;
        Calendar calendar = Calendar.getInstance();     //  获取当前时间    —   年、月、日
        int year = calendar.get(Calendar.YEAR);         //  得到当前年
        int month = calendar.get(Calendar.MONTH);       //  得到当前月
        final int day = calendar.get(Calendar.DAY_OF_MONTH);  //  得到当前日

        //  日期选择对话框
        datePickerDialog = new DatePickerDialog(context, AlertDialog.THEME_DEVICE_DEFAULT_DARK, (view1, year1, month1, dayOfMonth) -> {
            //  这个方法是得到选择后的 年，月，日，分别对应着三个参数 — year、month、dayOfMonth
            LogUtils.i(year1 +"年"+ month1 +"月"+dayOfMonth+"日");
            String yyyyMMdd = year1 + getFullDate(month1+1, dayOfMonth);
            LogUtils.i(yyyyMMdd);
            mListener.onDateTimeSelected(DateTimeUtils.format(yyyyMMdd, DateTimeUtils.yyyyMMdd, DateTimeUtils.yyyy_MM_dd));
//            showTimePickerDialog(context, yyyyMMdd);
        }, year, month, day);
        //  弹出日历对话框时，默认显示 年，月，日
        datePickerDialog.show();
    }

    public DateTimeSelector(Context context, DateTimeListener listener, Long maxDate, Long minDate) {
        mListener = listener;
        Calendar calendar = Calendar.getInstance();     //  获取当前时间    —   年、月、日
        int year = calendar.get(Calendar.YEAR);         //  得到当前年
        int month = calendar.get(Calendar.MONTH);       //  得到当前月
        final int day = calendar.get(Calendar.DAY_OF_MONTH);  //  得到当前日

        //  日期选择对话框
        datePickerDialog = new DatePickerDialog(context, AlertDialog.THEME_DEVICE_DEFAULT_DARK, (view1, year1, month1, dayOfMonth) -> {
            //  这个方法是得到选择后的 年，月，日，分别对应着三个参数 — year、month、dayOfMonth
            LogUtils.i(year1 +"年"+ month1 +"月"+dayOfMonth+"日");
            String yyyyMMdd = year1 + getFullDate(month1+1, dayOfMonth);
            LogUtils.i(yyyyMMdd);
            mListener.onDateTimeSelected(DateTimeUtils.format(yyyyMMdd, DateTimeUtils.yyyyMMdd, DateTimeUtils.yyyy_MM_dd));
        }, year, month, day);

        if (maxDate != null && maxDate != 0) {
            datePickerDialog.getDatePicker().setMaxDate(maxDate);
        }

        if (minDate != null && minDate != 0) {
            datePickerDialog.getDatePicker().setMinDate(minDate);
        }

        //  弹出日历对话框时，默认显示 年，月，日
        datePickerDialog.show();
    }

    private void showTimePickerDialog(Context context, String yyyyMMdd) {

        //  时间选择对话框
        new TimePickerDialog(context, AlertDialog.THEME_HOLO_DARK, (view2, hourOfDay, minute) -> {
            LogUtils.i(hourOfDay +"时"+ minute +"分");
            LogUtils.i(yyyyMMdd + getFullDate(hourOfDay, minute));
            if (mListener != null) {
                mListener.onDateTimeSelected(DateTimeUtils.format(yyyyMMdd + getFullDate(hourOfDay, minute), DateTimeUtils.yyyyMMdd, DateTimeUtils.yyyy_MM_dd));
            }
        }, 0, 0, true).show();
    }

    private String getFullDate(int time1, int time2) {
        StringBuilder buffer = new StringBuilder();

        if (time1 < 10) buffer.append("0");
        buffer.append(time1);

        if (time2 < 10) buffer.append("0");
        buffer.append(time2);

        return buffer.toString();
    }

}
