package com.esharp.ams.dialog;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.ToggleButton;

import com.esharp.ams.R;
import com.esharp.sdk.bean.response.HandlerVo;
import com.esharp.sdk.bean.response.UserVo;
import com.esharp.sdk.dialog.BaseAlertDialog;
import com.esharp.sdk.utils.ResUtils;
import com.esharp.sdk.widget.OnClickCallback;
import com.esharp.sdk.widget.SPCardEditView;
import com.esharp.sdk.widget.SPSelectorCardView;

import androidx.annotation.NonNull;

/**
 * 功能描述：资产筛选
 *
 * @author (作者) fengchuanfang
 * 创建时间： 2021/7/9
 */
public class FilterAssetDialog extends BaseAlertDialog {
    private final SPSelectorCardView scv_type;
    private final ImageView iv_search;
    private final SPCardEditView cev_name, cev_number, cev_location;

    public FilterAssetDialog(@NonNull Context context) {
        super(context, R.layout.dialog_filter_assets);

        scv_type = findViewById(R.id.scv_type);
        cev_name = findViewById(R.id.cev_name);
        cev_number = findViewById(R.id.cev_number);
        cev_location = findViewById(R.id.cev_location);
        iv_search = findViewById(R.id.iv_search);

//        alertText = findViewById(R.id.dialog_alert_text);
//        TextView alertOk = findViewById(R.id.dialog_alert_ok);
//        View.OnClickListener closeListener = v -> dismiss();
//        close.setOnClickListener(closeListener);
//        alertOk.setOnClickListener(v -> {
//            dismiss();
//            if (onOKAction != null) {
//                onOKAction.act();
//            }
//        });
//        GradientDrawable shape = new GradientDrawable();
//        shape.setShape(GradientDrawable.RECTANGLE);
//        shape.setCornerRadius(DeviceUtils.dp2px(context, 25));
//        shape.setColor(SPGlobalManager.getTint());
//        alertOk.setBackground(shape);
//        close.setImageTintList(ColorStateList.valueOf((SPGlobalManager.getTint())));
    }


    public BaseAlertDialog setOnClickListener(OnClickCallback<HandlerVo> it) {
        iv_search.setOnClickListener(v-> {
            HandlerVo vo = new HandlerVo();
            vo.setContent(nv_remark.getContent());
            vo.setIsOver(0);
            it.onClick(vo);
        });
        return this;
    }

//    public interface FilterListener {
//        void onSearch();
//    }
}
