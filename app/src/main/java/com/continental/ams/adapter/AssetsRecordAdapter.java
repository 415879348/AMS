package com.continental.ams.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import com.continental.ams.R;
import com.continental.sdk.base.BaseAdapter;
import com.continental.sdk.bean.response.DeviceBean;
import com.continental.sdk.utils.ClickUtil;
import com.continental.sdk.utils.ResUtils;
import com.continental.sdk.widget.OnClickCallback;
import androidx.annotation.NonNull;

/**
 * 功能描述：
 *
 * @author (作者) someone
 * 创建时间： 2021/8/7
 */
public class AssetsRecordAdapter extends BaseAdapter<DeviceBean, AssetsRecordAdapter.Listener> {

    public AssetsRecordAdapter(AssetsRecordAdapter.Listener onItemOperate) {
        super(onItemOperate);
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @NonNull
    @Override
    public ViewHolder<DeviceBean, AssetsRecordAdapter.Listener> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_assets, parent, false));
    }

    static class BaseViewHolder extends ViewHolder<DeviceBean, AssetsRecordAdapter.Listener> {

        private final CheckBox cb;
        private final TextView tv_asset_number, tv_asset_name, tv_location, tv_state;

        public BaseViewHolder(View itemView) {
            super(itemView);
            cb = itemView.findViewById(R.id.cb);
            tv_asset_number = itemView.findViewById(R.id.tv_asset_number);
            tv_asset_name = itemView.findViewById(R.id.tv_asset_name);
            tv_location = itemView.findViewById(R.id.tv_location);
            tv_state = itemView.findViewById(R.id.tv_state);
        }

        @Override
        protected void bindData(DeviceBean item, AssetsRecordAdapter.Listener onItemOperate) {

            cb.setOnCheckedChangeListener((buttonView, isChecked) -> {
                item.setCheck(isChecked);
                onItemOperate.setChecked(isChecked);
            });

            if (getBindingAdapterPosition() % 2 == 1) {
                itemView.setBackground(ResUtils.getDrawable(R.color.spsdk_color_gray3));
            } else {
                itemView.setBackground(ResUtils.getDrawable(R.color.white));
            }

            tv_asset_number.setText(item.getDeviceNumber());
            tv_asset_name.setText(item.getDeviceName());
            tv_location.setText(item.getLocation());

            //     0:正常 1:準備中 2:異常 3:初始化失敗，需要手動操作
            switch (item.getStatus()) {
                case 0 :
                    tv_state.setText(ResUtils.getString(R.string.regular));
                    break;
                case 1 :
                    tv_state.setText(ResUtils.getString(R.string.in_preparation));
                    break;
                case 2 :
                    tv_state.setText(ResUtils.getString(R.string.abnormal));
                    break;
                case 3 :
                    tv_state.setText(ResUtils.getString(R.string.initialization_failed));
                    break;
            }

            itemView.setOnClickListener(v -> {
                if (ClickUtil.isFastDoubleClick()) {
                    return;
                }
                onItemOperate.onClick(item);
            });
        }

    }

    public interface Listener extends OnClickCallback<DeviceBean> {
        void setChecked(Boolean it);
    }

}
