package com.continental.ams.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.continental.sdk.base.BaseAdapter;
import com.continental.sdk.bean.response.WorkOrderBean;
import com.continental.sdk.utils.ClickUtil;
import com.continental.sdk.utils.ResUtils;
import com.continental.ams.R;
import com.continental.sdk.widget.SPShowTextView;

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
        return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_backlog2, parent, false));
    }

    static class BaseViewHolder extends ViewHolder<WorkOrderBean, Listener> {
        private final SPShowTextView tv_work, tv_type, tv_progress;

        BaseViewHolder(View itemView) {
            super(itemView);
            tv_work = itemView.findViewById(R.id.tv_work);
            tv_type = itemView.findViewById(R.id.tv_type);
            tv_progress = itemView.findViewById(R.id.tv_progress);
        }

        @Override
        protected void bindData(WorkOrderBean it, Listener onItemOperate) {
//            LogUtils.i(getAbsoluteAdapterPosition(), getPosition(), getBindingAdapterPosition(), getLayoutPosition(), getAdapterPosition());
//            if (getBindingAdapterPosition() % 2 == 1) {
//                itemView.setBackground(ResUtils.getDrawable(R.color.spsdk_color_gray3));
//            } else {
//                itemView.setBackground(ResUtils.getDrawable(R.color.white));
//            }

            tv_work.setDetail(it.getTitle());
            tv_type.setDetail(it.getType());

//            -1:撤銷 0:結束 1:申請 2:審批 3:處理
            switch (it.getStep()) {
                case -1 :
                    tv_progress.setDetail(ResUtils.getString(R.string.revocation));
                    break;
                case 0 :
                    tv_progress.setDetail(ResUtils.getString(R.string.end));
                    break;
                case 1 :
                    tv_progress.setDetail(ResUtils.getString(R.string.apply));
                    break;
                case 2 :
                    tv_progress.setDetail(ResUtils.getString(R.string.approval));
                    break;
                case 3 :
                    tv_progress.setDetail(ResUtils.getString(R.string.processing));
                    break;
            }

            itemView.setOnClickListener(v -> {
                if (ClickUtil.isFastDoubleClick()) {
                    return;
                }
                onItemOperate.onItemClick(it);
            });
        }
    }

    public interface Listener {
        void onItemClick(WorkOrderBean item);
    }
}
