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
import com.continental.sdk.widget.SPShowTextView;

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
        return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_done2, parent, false));
    }

    static class BaseViewHolder extends ViewHolder<WorkOrderBean, OnClickCallback<WorkOrderBean>> {

        private final SPShowTextView tv_work, tv_type, tv_time, tv_operator;

        BaseViewHolder(View itemView) {
            super(itemView);
            tv_work = itemView.findViewById(com.continental.ams.R.id.tv_work);
            tv_type = itemView.findViewById(com.continental.ams.R.id.tv_type);
            tv_time = itemView.findViewById(com.continental.ams.R.id.tv_time);
            tv_operator = itemView.findViewById(com.continental.ams.R.id.tv_operator);
        }

        @Override
        protected void bindData(WorkOrderBean it, OnClickCallback<WorkOrderBean> onItemOperate) {
//            if (getBindingAdapterPosition() % 2 == 1) {
//                itemView.setBackground(ResUtils.getDrawable(R.color.spsdk_color_gray3));
//            } else {
//                itemView.setBackground(ResUtils.getDrawable(R.color.white));
//            }
            tv_work.setDetail(it.getTitle());
            //工单类型
            tv_type.setDetail(it.getType());

            if (it.getOverTime() != null) {
                tv_time.setDetail(DateTimeUtils.millis2Date(it.getOverTime()));
            } else {
                tv_time.setDetail("");
            }

            tv_operator.setDetail(it.getHandlerName());

            itemView.setOnClickListener(v -> {
                if (ClickUtil.isFastDoubleClick()) {
                    return;
                }
                onItemOperate.onClick(it);
            });
        }
    }

}
