package com.esharp.ams.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.esharp.ams.R;
import com.esharp.sdk.base.BaseAdapter;
import com.esharp.sdk.bean.response.WorkOrderBean;
import com.esharp.sdk.utils.ResUtils;

import androidx.annotation.NonNull;

/**
 * 功能描述：
 *
 * @author (作者) someone
 * 创建时间： 2021/8/7
 */
public class BacklogRecordAdapter extends BaseAdapter<WorkOrderBean, BacklogRecordAdapter.Listener> {

    public BacklogRecordAdapter(Listener onItemOperate) {
        super(onItemOperate);
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @NonNull
    @Override
    public ViewHolder<WorkOrderBean, Listener> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_backlog, parent, false));
    }

    static class BaseViewHolder extends ViewHolder<WorkOrderBean, Listener> {
        private final TextView tv_job_name, tv_job_type, tv_progress, mtv_handle;

        BaseViewHolder(View itemView) {
            super(itemView);
            tv_job_name = itemView.findViewById(R.id.tv_job_name);
            tv_job_type = itemView.findViewById(R.id.tv_job_type);
            tv_progress = itemView.findViewById(R.id.tv_progress);
            mtv_handle = itemView.findViewById(R.id.mtv_handle);
        }

        @Override
        protected void bindData(WorkOrderBean it, Listener onItemOperate) {
//            LogUtils.i(getAbsoluteAdapterPosition(), getPosition(), getBindingAdapterPosition(), getLayoutPosition(), getAdapterPosition());
            if (getBindingAdapterPosition() % 2 == 1) {
                itemView.setBackground(ResUtils.getDrawable(R.color.spsdk_color_gray3));
            } else {
                itemView.setBackground(ResUtils.getDrawable(R.color.white));
            }

            tv_job_name.setText(it.getTitle());
            if (it.getType() == 0) {
                tv_job_type.setText(ResUtils.getString(R.string.repair_work_order));
            } else {
                tv_job_type.setText(ResUtils.getString(R.string.regular_maintenance));
            }

//            -1:撤銷 0:結束 1:申請 2:審批 3:處理
            switch (it.getStep()) {
                case -1 :
                    tv_progress.setText(ResUtils.getString(R.string.revocation));
                    break;
                case 0 :
                    tv_progress.setText(ResUtils.getString(R.string.end));
                    break;
                case 1 :
                    tv_progress.setText(ResUtils.getString(R.string.apply));
                    break;
                case 2 :
                    tv_progress.setText(ResUtils.getString(R.string.approval));
                    break;
                case 3 :
                    tv_progress.setText(ResUtils.getString(R.string.processing));
                    break;
            }

            mtv_handle.setOnClickListener(v -> onItemOperate.onItemClick(it));
        }
    }

    public interface Listener {
        void onItemClick(WorkOrderBean item);
    }
}
