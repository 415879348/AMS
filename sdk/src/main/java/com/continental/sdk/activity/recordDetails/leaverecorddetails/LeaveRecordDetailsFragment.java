package com.continental.sdk.activity.recordDetails.leaverecorddetails;

import android.os.Bundle;
import android.util.Pair;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.continental.sdk.R;
import com.continental.sdk.activity.recordDetails.RecordDetailsActivity;
import com.continental.sdk.activity.recordDetails.RecordDetailsContract;
import com.continental.sdk.base.BaseMvpFragment;
import com.continental.sdk.bean.response.ApplyRecordVo;
import com.continental.sdk.bean.response.RecordDetailVo;
import com.continental.sdk.utils.DateTimeUtils;
import com.continental.sdk.utils.ResUtils;
import com.continental.sdk.widget.SPShowTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class LeaveRecordDetailsFragment extends BaseMvpFragment<LeaveRecordDetailsContract.Presenter, RecordDetailsContract.IHost> implements LeaveRecordDetailsContract.View {
    @Override
    protected Pair<Integer, LeaveRecordDetailsContract.Presenter> layoutAndPresenter() {
        return Pair.create(R.layout.spsdk_fragment_record_details_leave, new LeaveRecordDetailsPresenter(this));
    }

    public final static String TAG = LeaveRecordDetailsFragment.class.getSimpleName();

    public static Fragment getInstance(ApplyRecordVo item) {
        Fragment fragment = new LeaveRecordDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(RecordDetailsActivity.APPLY_RECORD, item);
        fragment.setArguments(bundle);
        return fragment;
    }

    private SPShowTextView stv_userName, stv_department, stv_leaveTypeName, stv_start_time, stv_end_time, stv_leave_duration, stv_leave_reason, stv_commit_time;

    @Override
    protected void init(View view) {
        stv_userName = view.findViewById(R.id.stv_userName);
        stv_department = view.findViewById(R.id.stv_department);
        stv_leaveTypeName = view.findViewById(R.id.stv_leaveTypeName);
        stv_start_time = view.findViewById(R.id.stv_start_time);
        stv_end_time = view.findViewById(R.id.stv_end_time);
        stv_leave_duration = view.findViewById(R.id.stv_leave_duration);
        stv_leave_reason = view.findViewById(R.id.stv_leave_reason);
        stv_commit_time = view.findViewById(R.id.stv_commit_time);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle == null) return;
        ApplyRecordVo vo = (ApplyRecordVo) bundle.getSerializable(RecordDetailsActivity.APPLY_RECORD);
        LogUtils.json(vo);
        mPresenter.getLeaveRecordDetailAll(vo.getApplyId());
    }

    @Override
    public void getLeaveRecordDetailAllSuccess(RecordDetailVo it) {
        LogUtils.json(it);
        stv_userName.setDetail(it.getUserName());
        stv_department.setDetail(it.getDepartmentName());
        stv_leaveTypeName.setDetail(it.getLeaveTypeName());
        stv_start_time.setDetail(DateTimeUtils.millis2Date(it.getStartTime()));
        stv_end_time.setDetail(DateTimeUtils.millis2Date(it.getEndTime()));
        stv_leave_duration.setDetail(String.format(ResUtils.getString(R.string.spsdk_placeholder_hours), it.getLeaveHour()));
        stv_leave_reason.setDetail(it.getReason());
        stv_commit_time.setDetail(DateTimeUtils.millis2Date(it.getCreateTime()));

        mHostView.getLeaveRecordDetailAllSuccess(it);
    }
}
