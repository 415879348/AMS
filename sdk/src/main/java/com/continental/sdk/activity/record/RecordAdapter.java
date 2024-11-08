package com.continental.sdk.activity.record;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.continental.sdk.Constant;
import com.continental.sdk.R;
import com.continental.sdk.Tag;
import com.continental.sdk.base.BaseAdapter;
import com.continental.sdk.bean.response.ApplyRecordVo;
import com.continental.sdk.http.GlideUtils;
import com.continental.sdk.utils.DateTimeUtils;
import com.continental.sdk.utils.ResUtils;
import com.continental.sdk.utils.StatusUtils;
import com.continental.sdk.widget.SPShowTextView;

import androidx.annotation.NonNull;

/**
 * 功能描述：
 *
 * @author (作者) someone
 * 创建时间： 2021/8/7
 */
public class RecordAdapter extends BaseAdapter<ApplyRecordVo, RecordAdapter.Listener> {

    private Tag tag;

    public RecordAdapter(Tag tag, Listener onItemOperate) {
        super(onItemOperate);
        this.tag = tag;
    }

    @Override
    public void refreshItem(ApplyRecordVo item) {
        for (int i = 0; i < getItemCount(); i++) {
            if (mList.get(i).getApplyId().equals(item.getApplyId())) {
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
    public ViewHolder<ApplyRecordVo, Listener> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (tag) {
            case ATTENDANCE:
                return new AttendanceViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.spsdk_item_record_attendance_sub, parent, false));
            case AMEND:
                return new AmendViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.spsdk_item_record_amend_sub, parent, false));
            case LEAVE:
                return new LeaveViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.spsdk_item_record_leave_sub, parent, false));
            case OVERTIME:
                return new OverTimeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.spsdk_item_record_overtime_sub, parent, false));
        }

        return new AttendanceViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.spsdk_item_record_attendance_sub, parent, false));
    }

    static class BaseHolder extends ViewHolder<ApplyRecordVo, Listener> {
        private final FrameLayout fl_status;
        private final LinearLayout ll_revoke_modify;
        protected TextView tv_revoke, tv_modify;

        public BaseHolder(View itemView) {
            super(itemView);
            fl_status = itemView.findViewById(R.id.fl_status);
            ll_revoke_modify = itemView.findViewById(R.id.ll_revoke_modify);
            tv_revoke = itemView.findViewById(R.id.tv_revoke);
            tv_modify = itemView.findViewById(R.id.tv_modify);
        }

        @Override
        protected void bindData(ApplyRecordVo item, Listener onItemOperate) {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) itemView.getLayoutParams();
            params.topMargin = getAbsoluteAdapterPosition() == 0 ? SizeUtils.dp2px(30) : 0;
            itemView.setLayoutParams(params);
            fl_status.removeAllViews();

            fl_status.addView(StatusUtils.getApplyStatusView(mContext, item.getStatus().intValue()));

            itemView.setOnClickListener(v -> onItemOperate.onItemClick(item));
            tv_revoke.setOnClickListener(v -> onItemOperate.onRevocation(item));

            if (Constant.APPLY_STATUS_WAITING_HANDLE ==  item.getStatus().intValue()) {
                ll_revoke_modify.setVisibility(View.VISIBLE);
            } else {
                ll_revoke_modify.setVisibility(View.GONE);
            }
        }
    }

    static class AttendanceViewHolder extends  ViewHolder<ApplyRecordVo, Listener> {
        private final SPShowTextView stv_signTime;
        private final SPShowTextView stv_temperature;
        private final ImageView iv_icon;

        AttendanceViewHolder(View itemView) {
            super(itemView);
            stv_signTime = itemView.findViewById(R.id.stv_signTime);
            stv_temperature = itemView.findViewById(R.id.stv_temperature);
            iv_icon = itemView.findViewById(R.id.iv_icon);

        }

        @Override
        protected void bindData(ApplyRecordVo item, Listener onItemOperate) {
            LogUtils.json(item);
            stv_signTime.setDetail(DateTimeUtils.millis2Date(item.getSignTime()));
            if (item.getTemperature() != null) {
                stv_temperature.setDetail(item.getTemperature());
            }
            GlideUtils.showImage(iv_icon, item.getImageUrl());

            itemView.setOnClickListener(v -> onItemOperate.onItemClick(item));

        }
    }

    static class AmendViewHolder extends BaseHolder {
        private final SPShowTextView stv_repair_time, stv_temperature, stv_commit_time;

        AmendViewHolder(View itemView) {
            super(itemView);
            stv_repair_time = itemView.findViewById(R.id.stv_repair_time);
            stv_temperature = itemView.findViewById(R.id.stv_temperature);
            stv_commit_time = itemView.findViewById(R.id.stv_commit_time);
        }

        @Override
        protected void bindData(ApplyRecordVo item, Listener onItemOperate) {
            super.bindData(item, onItemOperate);
            LogUtils.json(item);

            stv_repair_time.setDetail(DateTimeUtils.millis2Date(item.getRepairTime()));
            stv_temperature.setDetail(item.getTemperature() + " ℃");
            stv_temperature.setDetailColour(ResUtils.getColor(R.color.spsdk_color_green_light));
            stv_commit_time.setDetail(DateTimeUtils.millis2Date(item.getSubmitTime()));

            tv_modify.setOnClickListener(v -> {
                onItemOperate.onModify(item);
            });
        }
    }

    static class LeaveViewHolder extends BaseHolder {
        private final SPShowTextView stv_leave_type, stv_start_time, stv_leave_duration, stv_end_time, stv_commit_time;

        LeaveViewHolder(View itemView) {
            super(itemView);
            stv_leave_type = itemView.findViewById(R.id.stv_leave_type);
            stv_start_time = itemView.findViewById(R.id.stv_start_time);
            stv_leave_duration = itemView.findViewById(R.id.stv_leave_duration);
            stv_end_time = itemView.findViewById(R.id.stv_end_time);
            stv_commit_time = itemView.findViewById(R.id.stv_commit_time);
        }

        @Override
        protected void bindData(ApplyRecordVo item, Listener onItemOperate) {
            super.bindData(item, onItemOperate);
            LogUtils.json(item);

            stv_leave_type.setDetail(item.getLeaveTypeName());
            stv_start_time.setDetail(DateTimeUtils.millis2Date(item.getStartTime()));
            stv_leave_duration.setDetail(item.getLeaveHour());
            stv_end_time.setDetail(DateTimeUtils.millis2Date(item.getEndTime()));
            stv_commit_time.setDetail(DateTimeUtils.millis2Date(item.getSubmitTime()));

            tv_modify.setOnClickListener(v -> {
                onItemOperate.onModify(item);
            });
        }
    }



    static class OverTimeViewHolder extends BaseHolder {
        private final SPShowTextView stv_start_time, stv_end_time, stv_type, stv_ot_duration, stv_commit_time;

        OverTimeViewHolder(View itemView) {
            super(itemView);
            stv_type = itemView.findViewById(R.id.stv_type);
            stv_start_time = itemView.findViewById(R.id.stv_start_time);
            stv_end_time = itemView.findViewById(R.id.stv_end_time);
            stv_ot_duration = itemView.findViewById(R.id.stv_ot_duration);
            stv_commit_time = itemView.findViewById(R.id.stv_commit_time);
        }

        @Override
        protected void bindData(ApplyRecordVo item, Listener onItemOperate) {
            super.bindData(item, onItemOperate);
            LogUtils.json(item);

            stv_type.setDetail(item.getReason());
            stv_start_time.setDetail(DateTimeUtils.millis2Date(item.getStartTime()));
            stv_end_time.setDetail(DateTimeUtils.millis2Date(item.getEndTime()));
            stv_ot_duration.setDetail(item.getOtHour());
            stv_commit_time.setDetail(DateTimeUtils.millis2Date(item.getSubmitTime()));
        }
    }

    interface Listener {
        void onItemClick(ApplyRecordVo item);
        void onModify(ApplyRecordVo item);
        void onRevocation(ApplyRecordVo item);
    }

}
