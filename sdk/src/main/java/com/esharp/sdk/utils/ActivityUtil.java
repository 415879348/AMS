package com.esharp.sdk.utils;

import android.app.Activity;
import android.app.Service;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class ActivityUtil {

    public static void hideSoftInput(Activity context) {
        InputMethodManager manager = (InputMethodManager)context.getSystemService(Service.INPUT_METHOD_SERVICE);
        if (manager.isActive()) {
            manager.hideSoftInputFromWindow(context.getWindow().getDecorView().getWindowToken(), 0);
        }
    }

    public static void hideSoftInput(View view) {
        InputMethodManager manager = (InputMethodManager) view.getContext().getSystemService(Service.INPUT_METHOD_SERVICE);
        if (manager.isActive()) {
            manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}
