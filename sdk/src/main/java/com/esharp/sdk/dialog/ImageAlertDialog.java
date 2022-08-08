package com.esharp.sdk.dialog;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.ScreenUtils;
import com.esharp.sdk.R;
import com.esharp.sdk.widget.SPTabView;

import androidx.annotation.NonNull;

/**
 * 功能描述：添加申请
 *
 * @author (作者) someone
 * 创建时间： 2021/7/9
 */
public class ImageAlertDialog extends BaseAlertDialog {
    private ImageView cancel;
    private RelativeLayout container;
    private SPTabView amend;
    private SPTabView leave;
    private SPTabView overtime;

    public ImageAlertDialog(@NonNull Context context) {
        super(context, R.layout.spsdk_dialog_image);
        cancel = findViewById(R.id.cancel);
        container = findViewById(R.id.container);
        amend = findViewById(R.id.amend);
        leave = findViewById(R.id.leave);
        overtime = findViewById(R.id.overtime);

        FrameLayout.LayoutParams linearParams =(FrameLayout.LayoutParams) container.getLayoutParams(); //取控件textView当前的布局参数 linearParams.height = 20;// 控件的高强制设成20

        linearParams.height = ScreenUtils.getScreenHeight() / 3;// 控件的宽强制设成30

        container.setLayoutParams(linearParams); //使设置好的布局参数应用到控件

        cancel.setOnClickListener(v -> dismiss());
    }

    public BaseAlertDialog setOnClickListener(View.OnClickListener amendClick, View.OnClickListener leaveClick, View.OnClickListener overtimeClick) {
        amend.setOnClickListener(amendClick);
        leave.setOnClickListener(leaveClick);
        overtime.setOnClickListener(overtimeClick);
        return this;
    }

}
