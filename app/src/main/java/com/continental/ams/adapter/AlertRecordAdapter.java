package com.continental.ams.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.continental.ams.R;

import com.continental.sdk.base.BaseAdapter;
import com.continental.sdk.bean.response.AssetAlertBean;
import com.continental.sdk.utils.ClickUtil;
import com.continental.sdk.utils.DateTimeUtils;
import com.continental.sdk.utils.ResUtils;
import com.continental.sdk.widget.OnClickCallback;
import com.continental.sdk.widget.SPShowTextView;

import androidx.annotation.NonNull;

/**
 * 功能描述：
 *
 * @author (作者) someone
 * 创建时间： 2021/8/7
 */
public class AlertRecordAdapter extends BaseAdapter<AssetAlertBean, OnClickCallback<AssetAlertBean>> {

    public AlertRecordAdapter(OnClickCallback<AssetAlertBean> onItemOperate) {
        super(onItemOperate);
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @NonNull
    @Override
    public ViewHolder<AssetAlertBean, OnClickCallback<AssetAlertBean>> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alert2, parent, false));
    }

    static class BaseViewHolder extends ViewHolder<AssetAlertBean, OnClickCallback<AssetAlertBean>> {

        private final SPShowTextView tv_warning, tv_type, tv_time, tv_assets;

        BaseViewHolder(View itemView) {
            super(itemView);
            tv_warning = itemView.findViewById(R.id.tv_warning);
            tv_type = itemView.findViewById(R.id.tv_type);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_assets = itemView.findViewById(R.id.tv_assets);
        }

        @Override
        protected void bindData(AssetAlertBean it, OnClickCallback<AssetAlertBean> onItemOperate) {
//            if (getBindingAdapterPosition() % 2 == 1) {
//                itemView.setBackground(ResUtils.getDrawable(R.color.spsdk_color_gray3));
//            } else {
//                itemView.setBackground(ResUtils.getDrawable(R.color.white));
//            }

            tv_warning.setDetail(it.getRemark());

            //类型 1：异常记录 2：保养记录
            if (it.getType() == 1) {
                tv_type.setDetail(ResUtils.getString(R.string.record_exception));
            } else {
                tv_type.setDetail(ResUtils.getString(R.string.record_maintain));
            }

            //记录状态 0：未处理 1：已处理
//            if (it.getStatus() == 0) {
//                itemView.setBackground(ResUtils.getDrawable(R.drawable.spsdk_shape_rec_red));
//            }

            tv_time.setDetail(DateTimeUtils.millis2Date(it.getCreateTime(), DateTimeUtils.yyyy_MM_dd_HH_mm_ss));

            tv_assets.setDetail(it.getDeviceName());

            itemView.setOnClickListener(v -> {
                if (ClickUtil.isFastDoubleClick()) {
                    return;
                }
                onItemOperate.onClick(it);
            });
        }
    }

}
