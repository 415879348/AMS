package com.esharp.sdk.dialog;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.blankj.utilcode.util.ScreenUtils;
import com.esharp.sdk.R;
import com.esharp.sdk.http.GlideUtils;
import androidx.annotation.NonNull;

/**
 * 功能描述：添加申请
 *
 * @author (作者) someone
 * 创建时间： 2021/7/9
 */
public class PicturePreviewDialog extends BaseAlertDialog {
    private ImageView picture_temp;
    private RelativeLayout container;

    public PicturePreviewDialog(@NonNull Context context, String imageUrl) {
        super(context, R.layout.spsdk_dialog_picture_preview);
        container = findViewById(R.id.container);
        picture_temp = findViewById(R.id.picture_temp);
        FrameLayout.LayoutParams linearParams =(FrameLayout.LayoutParams) container.getLayoutParams(); //取控件textView当前的布局参数 linearParams.height = 20;// 控件的高强制设成20

        linearParams.height = ScreenUtils.getScreenHeight() / 2;// 控件的宽强制设成30

        container.setLayoutParams(linearParams); //使设置好的布局参数应用到控件

        GlideUtils.showImage(picture_temp, imageUrl);

        container.setOnClickListener(v -> {
            dismiss();
        });
        setCanceledOnTouchOutside(true);
    }

}
