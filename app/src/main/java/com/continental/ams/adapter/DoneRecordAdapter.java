package com.continental.ams.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.continental.ams.R;
import com.continental.sdk.base.BaseAdapter;
import com.continental.sdk.bean.response.WorkOrderBean;
import com.continental.sdk.utils.ClickUtil;
import com.continental.sdk.utils.DateTimeUtils;
import com.continental.sdk.utils.ResUtils;
import com.continental.sdk.widget.OnClickCallback;
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
            tv_job_name = itemView.findViewById(com.continental.ams.R.id.tv_job_name);
            tv_job_type = itemView.findViewById(com.continental.ams.R.id.tv_job_type);
            tv_completion_time = itemView.findViewById(com.continental.ams.R.id.tv_completion_time);
            tv_completion_by = itemView.findViewById(com.continental.ams.R.id.tv_completion_by);
        }

        @Override
        protected void bindData(WorkOrderBean it, OnClickCallback<WorkOrderBean> onItemOperate) {
            if (getBindingAdapterPosition() % 2 == 1) {
                itemView.setBackground(ResUtils.getDrawable(R.color.spsdk_color_gray3));
            } else {
                itemView.setBackground(ResUtils.getDrawable(R.color.white));
            }

            tv_job_name.setText(it.getTitle());

            //工单类型
            tv_job_type.setText(it.getType());

            if (it.getOverTime() != null) {
                tv_completion_time.setText(DateTimeUtils.millis2Date(it.getOverTime()));
            } else {
                tv_completion_time.setText("");
            }

            tv_completion_by.setText(it.getHandlerName());

            itemView.setOnClickListener(v -> {
                if (ClickUtil.isFastDoubleClick()) {
                    return;
                }
                onItemOperate.onClick(it);
            });
        }
    }

}
