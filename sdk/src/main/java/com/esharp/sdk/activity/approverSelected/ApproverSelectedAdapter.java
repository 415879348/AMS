package com.esharp.sdk.activity.approverSelected;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.esharp.sdk.R;
import com.esharp.sdk.base.BaseAdapter;
import com.esharp.sdk.bean.response.ApprovalUserBean;
import com.esharp.sdk.http.GlideUtils;
import com.esharp.sdk.utils.DateTimeUtils;
import com.esharp.sdk.utils.StatusUtils;

import androidx.annotation.NonNull;

/**
 * 功能描述：
 *
 * @author (作者) someone
 * 创建时间： 2021/8/7
 */
public class ApproverSelectedAdapter extends BaseAdapter<ApprovalUserBean, ApproverSelectedAdapter.Listener> {

    public ApproverSelectedAdapter(Listener onItemOperate) {
        super(onItemOperate);
    }

    @Override
    public void refreshItem(ApprovalUserBean item) {
        for (int i = 0; i < getItemCount(); i++) {
            if (mList.get(i).getApprovalUserId().equals(item.getApprovalUserId())) {
                notifyItemChanged(i);
                return;
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @NonNull
    @Override
    public ViewHolder<ApprovalUserBean, Listener> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // recordType //100警報，101考勤 (目前只有考勤类型)
        return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.spsdk_item_approve_selected, parent, false));
    }

    static class BaseViewHolder extends ViewHolder<ApprovalUserBean, Listener> {
        private final TextView tv_name, tv_department, tv_date;
        private final ImageView iv_icon;
        private FrameLayout fl_status;

        BaseViewHolder(View itemView) {
            super(itemView);
            iv_icon = itemView.findViewById(R.id.iv_icon);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_department = itemView.findViewById(R.id.tv_department);
            tv_date = itemView.findViewById(R.id.tv_date);
            fl_status = itemView.findViewById(R.id.fl_status);
        }

        @Override
        protected void bindData(ApprovalUserBean item, Listener onItemOperate) {
            LogUtils.json(item);
            GlideUtils.showImage(iv_icon, item.getHeadPortrait());
            tv_name.setText(item.getApprovalUserName());
            tv_department.setText(item.getDepartmentName());
            if (ObjectUtils.isNotEmpty(item.getApprovalTime())) {
                tv_date.setText(DateTimeUtils.millis2Date(item.getApprovalTime()));
            }
            fl_status.removeAllViews();
            fl_status.addView(StatusUtils.getApplyStatusView(mContext, item.getStatus().intValue()));

            itemView.setOnClickListener(v -> onItemOperate.onItemClick(item));
        }
    }

    interface Listener {
        void onItemClick(ApprovalUserBean item);
    }
}
