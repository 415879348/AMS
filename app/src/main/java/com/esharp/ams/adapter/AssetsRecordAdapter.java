package com.esharp.ams.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.esharp.ams.R;
import com.esharp.sdk.base.BaseAdapter;
import com.esharp.sdk.bean.response.DeviceBean;
import com.esharp.sdk.utils.ResUtils;
import com.esharp.sdk.widget.OnClickCallback;

import androidx.annotation.NonNull;

/**
 * 功能描述：
 *
 * @author (作者) someone
 * 创建时间： 2021/8/7
 */
public class AssetsRecordAdapter extends BaseAdapter<DeviceBean, OnClickCallback<DeviceBean>> {

    public AssetsRecordAdapter(OnClickCallback<DeviceBean> onItemOperate) {
        super(onItemOperate);
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @NonNull
    @Override
    public ViewHolder<DeviceBean, OnClickCallback<DeviceBean>> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_assets, parent, false));
    }

    static class BaseViewHolder extends ViewHolder<DeviceBean, OnClickCallback<DeviceBean>> {

        private final TextView tv_asset_number, tv_asset_name, tv_location, tv_state;

        public BaseViewHolder(View itemView) {
            super(itemView);
            tv_asset_number = itemView.findViewById(R.id.tv_asset_number);
            tv_asset_name = itemView.findViewById(R.id.tv_asset_name);
            tv_location = itemView.findViewById(R.id.tv_location);
            tv_state = itemView.findViewById(R.id.tv_state);
        }

        @Override
        protected void bindData(DeviceBean item, OnClickCallback<DeviceBean> onItemOperate) {
            if (getBindingAdapterPosition() % 2 == 1) {
                itemView.setBackground(ResUtils.getDrawable(R.color.spsdk_color_gray3));
            } else {
                itemView.setBackground(ResUtils.getDrawable(R.color.white));
            }

            tv_asset_number.setText(item.getDeviceNumber());
            tv_asset_name.setText(item.getDeviceName());
            tv_location.setText(item.getAddress());

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

            itemView.setOnClickListener(v -> onItemOperate.onClick(item));

        }


    }

//    public interface Listener {
//        void onItemClick(DeviceBean item);
//    }

}
