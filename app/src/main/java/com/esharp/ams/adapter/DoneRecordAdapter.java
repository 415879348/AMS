package com.esharp.ams.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.esharp.ams.R;
import com.esharp.sdk.base.BaseAdapter;
import com.esharp.sdk.bean.response.DeviceBean;
import com.esharp.sdk.bean.response.WorkOrderBean;
import com.esharp.sdk.utils.DateTimeUtils;
import com.esharp.sdk.utils.ResUtils;
import com.esharp.sdk.widget.OnClickCallback;

import androidx.annotation.NonNull;

/**
 * 功能描述：
 *
 * @author (作者) someone
 * 创建时间： 2021/8/7
 */
public class DoneRecordAdapter extends BaseAdapter<WorkOrderBean, OnClickCallback<WorkOrderBean>> {

    public DoneRecordAdapter(OnClickCallback<WorkOrderBean> onItemOperate) {
        super(onItemOperate);
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @NonNull
    @Override
    public ViewHolder<WorkOrderBean, OnClickCallback<WorkOrderBean>> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_done, parent, false));
    }

    static class BaseViewHolder extends ViewHolder<WorkOrderBean, OnClickCallback<WorkOrderBean>> {

        private final TextView tv_job_name, tv_job_type, tv_completion_time, tv_completion_by;

        BaseViewHolder(View itemView) {
            super(itemView);
            tv_job_name = itemView.findViewById(com.esharp.ams.R.id.tv_job_name);
            tv_job_type = itemView.findViewById(com.esharp.ams.R.id.tv_job_type);
            tv_completion_time = itemView.findViewById(com.esharp.ams.R.id.tv_completion_time);
            tv_completion_by = itemView.findViewById(com.esharp.ams.R.id.tv_completion_by);
        }

        @Override
        protected void bindData(WorkOrderBean it, OnClickCallback<WorkOrderBean> onItemOperate) {
            if (getBindingAdapterPosition() % 2 == 1) {
                itemView.setBackground(ResUtils.getDrawable(R.color.spsdk_color_gray3));
            } else {
                itemView.setBackground(ResUtils.getDrawable(R.color.white));
            }

            tv_job_name.setText(it.getTitle());

            //工单类型 1：维修工单 2：定期保养
            if (it.getType() == 1) {
                tv_job_type.setText(ResUtils.getString(com.esharp.ams.R.string.repair_work_order));
            } else {
                tv_job_type.setText(ResUtils.getString(com.esharp.ams.R.string.regular_maintenance));
            }

            tv_completion_time.setText(DateTimeUtils.millis2Date(it.getOverTime()));

            tv_completion_by.setText(it.getHandlerName());

            itemView.setOnClickListener(v -> onItemOperate.onClick(it));
        }
    }

}
