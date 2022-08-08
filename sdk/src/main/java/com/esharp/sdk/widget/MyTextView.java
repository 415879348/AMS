package com.esharp.sdk.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.coorchice.library.SuperTextView;
import com.esharp.sdk.R;

public class MyTextView extends SuperTextView {
    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setAutoAdjust(true);
        addAdjuster(new RippleAdjuster(R.color.spsdk_transparent_55));
    }

}
