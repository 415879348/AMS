package com.esharp.sdk.activity.recordDetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.esharp.sdk.Constant;
import com.esharp.sdk.R;
import com.esharp.sdk.Tag;
import com.esharp.sdk.activity.approverSelected.ApproverSelectedActivity;
import com.esharp.sdk.activity.copyuserselected.CopyUserSelectedActivity;
import com.esharp.sdk.activity.recordDetails.amendRecordDetails.AmendRecordDetailsFragment;
import com.esharp.sdk.activity.recordDetails.attendanceRecordDetails.AttendanceRecordDetailsFragment;
import com.esharp.sdk.activity.recordDetails.leaverecorddetails.LeaveRecordDetailsFragment;
import com.esharp.sdk.activity.recordDetails.otrecorddetails.OtRecordDetailsFragment;
import com.esharp.sdk.base.BaseActivity;
import com.esharp.sdk.base.BaseMvpActivity;
import com.esharp.sdk.bean.response.ApplyRecordVo;
import com.esharp.sdk.bean.response.ApprovalUserBean;
import com.esharp.sdk.bean.response.CopyUserListBean;
import com.esharp.sdk.bean.response.RecordDetailVo;
import com.esharp.sdk.bean.response.RecordVo;
import com.esharp.sdk.http.GlideUtils;
import com.esharp.sdk.utils.DateTimeUtils;
import com.esharp.sdk.utils.ResUtils;
import com.esharp.sdk.utils.StatusUtils;
import com.esharp.sdk.widget.SPTitleView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.fragment.app.FragmentManager;

public class RecordDetailsActivity extends BaseMvpActivity<RecordDetailsContract.Presenter> implements RecordDetailsContract.IHost {

    @Override
    public BaseActivity getHost() {
        return this;
    }

    @Override
    protected Pair<Integer, RecordDetailsContract.Presenter> layoutAndPresenter() {
        return Pair.create(R.layout.spsdk_activity_record_detail, new RecordDetailsPresenter(this));
    }

    /**
     * 考勤专用
     * @param context
     * @param it
     */
    public static void startActivityFromAttendance(Context context, ApplyRecordVo it) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.TAG, Tag.ATTENDANCE);
        bundle.putSerializable(APPLY_RECORD, it);
        Intent intent = new Intent(context, RecordDetailsActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static void startActivity(Context context, ApplyRecordVo it) {
        Bundle bundle = new Bundle();

        switch (it.getApplyType().intValue()) {
            case Constant.APPLY_TYPE_AMEND: {
                bundle.putSerializable(Constant.TAG, Tag.AMEND);
            }
            break;
            case Constant.APPLY_TYPE_LEAVE: {
                bundle.putSerializable(Constant.TAG, Tag.LEAVE);
            }
            break;
            case Constant.APPLY_TYPE_OVERTIME: {
                bundle.putSerializable(Constant.TAG, Tag.OVERTIME);
            }
            break;
        }
        bundle.putSerializable(APPLY_RECORD, it);
        Intent intent = new Intent(context, RecordDetailsActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    private FragmentManager fragmentManager = null;
    public static final String APPLY_RECORD = "ApplyRecordVo";
    public static final String ATTENDANCE_RECORD = "RecordVo";
    ApplyRecordVo applyRecordVo = null;
    RecordVo recordVo = null;
    private TextView approval_user, approval_time;
    private FrameLayout fl_status;
    private LinearLayout ll_bottom, ll_approval_list, ll_copy_user_list;

    @Override
    protected void init() {
        Tag tag = (Tag) Objects.requireNonNull(getIntent().getExtras()).getSerializable(Constant.TAG);

        fragmentManager = getSupportFragmentManager();

        applyRecordVo = (ApplyRecordVo) getIntent().getSerializableExtra(APPLY_RECORD);
        LogUtils.json(applyRecordVo);

        ll_bottom = findViewById(R.id.ll_bottom);

        ll_approval_list = findViewById(R.id.ll_approval_list);

        ll_copy_user_list = findViewById(R.id.ll_copy_user_list);

        switch (tag) {
            case ATTENDANCE:
                ((SPTitleView)findViewById(R.id.title_view)).setTitle(ResUtils.getString(R.string.spsdk_attendance_record_detail));
                ll_bottom.setVisibility(View.GONE);
                fragmentManager.beginTransaction()
                        .add(R.id.container,
                                AttendanceRecordDetailsFragment.getInstance(applyRecordVo),
                                AttendanceRecordDetailsFragment.TAG)
                        .addToBackStack(AttendanceRecordDetailsFragment.TAG)
                        .commit();
                break;

            case AMEND:
                ((SPTitleView)findViewById(R.id.title_view)).setTitle(ResUtils.getString(R.string.spsdk_amend_record_detail));
                fragmentManager.beginTransaction()
                        .add(R.id.container,
                                AmendRecordDetailsFragment.getInstance(applyRecordVo),
                                AmendRecordDetailsFragment.TAG)
                        .addToBackStack(AmendRecordDetailsFragment.TAG)
                        .commit();
                break;

            case LEAVE:
                ((SPTitleView)findViewById(R.id.title_view)).setTitle(ResUtils.getString(R.string.spsdk_leave_record_detail));
                fragmentManager.beginTransaction()
                        .add(R.id.container,
                                LeaveRecordDetailsFragment.getInstance(applyRecordVo),
                                LeaveRecordDetailsFragment.TAG)
                        .addToBackStack(LeaveRecordDetailsFragment.TAG)
                        .commit();
                break;

            case OVERTIME:
                ((SPTitleView)findViewById(R.id.title_view)).setTitle(ResUtils.getString(R.string.spsdk_overtime_record_detail));
                fragmentManager.beginTransaction()
                        .add(R.id.container,
                                OtRecordDetailsFragment.getInstance(applyRecordVo),
                                OtRecordDetailsFragment.TAG)
                        .addToBackStack(OtRecordDetailsFragment.TAG)
                        .commit();
                break;
        }
    }

    @Override
    public void getAmendRecordDetailSuccess(RecordDetailVo it) {
        LogUtils.json(it);
        iniFlow(it);
    }

    @Override
    public void getLeaveRecordDetailAllSuccess(RecordDetailVo it) {
        LogUtils.json(it);
        iniFlow(it);
    }

    @Override
    public void getOtRecordDetailsAllSuss(RecordDetailVo it) {
        LogUtils.json(it);
        iniFlow(it);
    }

    private void iniFlow(RecordDetailVo it) {
        LogUtils.json(it);
        List<ApprovalUserBean> approvalUserList = it.getOrdinaryFlowDetails().getApprovalUserList();
        List<CopyUserListBean> copyUserList = it.getOrdinaryFlowDetails().getCopyUserList();

        // 审批人列表
        LogUtils.json(approvalUserList);

        // 抄送人列表
        LogUtils.json(copyUserList);

        // 显示审批人的头像
        for (int i = 0; i < approvalUserList.size(); i++) {
            ApprovalUserBean approvalUser = approvalUserList.get(i);
            LogUtils.json(approvalUser);
            View view = LayoutInflater.from(RecordDetailsActivity.this).inflate(R.layout.spsdk_view_head, null, false);
            ImageView icon = view.findViewById(R.id.iv_icon);
            TextView title = view.findViewById(R.id.title);
            title.setText(approvalUser.getApprovalUserName());
            GlideUtils.showImage(icon, approvalUser.getHeadPortrait());
            ll_approval_list.addView(view);
            if (i == 2) {
                break;
            }
        }

        // 显示查看全部
        if (approvalUserList.size() != 0) {
            View viewAll = LayoutInflater.from(RecordDetailsActivity.this).inflate(R.layout.spsdk_view_head, null, false);
            ImageView viewAllIcon = viewAll.findViewById(R.id.iv_icon);
            TextView viewAllTitle = viewAll.findViewById(R.id.title);
            viewAllIcon.setImageDrawable(ResUtils.getDrawable(R.drawable.spsdk_shape_rec_blue));
            viewAllTitle.setText(ResUtils.getString(R.string.spsdk_view_all));
            ll_approval_list.addView(viewAll);
            viewAll.setOnClickListener(v -> {
                ApproverSelectedActivity.startActivity(RecordDetailsActivity.this, new ArrayList<>(approvalUserList));
            });
        }

        for (int i = 0; i < copyUserList.size(); i++) {
            CopyUserListBean copyUserBean = copyUserList.get(i);
            LogUtils.json(copyUserBean);
            View view = LayoutInflater.from(RecordDetailsActivity.this).inflate(R.layout.spsdk_view_head, null, false);
            ImageView icon = view.findViewById(R.id.iv_icon);
            TextView title = view.findViewById(R.id.title);
            title.setText(copyUserBean.getCopyUserName());
            GlideUtils.showImage(icon, copyUserBean.getHeadPortrait());
            ll_copy_user_list.addView(view);
            if (i == 2) {
                break;
            }
        }

        if (copyUserList.size() != 0) {
            View viewAll = LayoutInflater.from(RecordDetailsActivity.this).inflate(R.layout.spsdk_view_head, null, false);
            ImageView viewAllIcon = viewAll.findViewById(R.id.iv_icon);
            TextView viewAllTitle = viewAll.findViewById(R.id.title);
            viewAllIcon.setImageDrawable(ResUtils.getDrawable(R.drawable.spsdk_shape_rec_blue));
            viewAllTitle.setText(ResUtils.getString(R.string.spsdk_view_all));
            ll_copy_user_list.addView(viewAll);

            viewAll.setOnClickListener(v -> {
                CopyUserSelectedActivity.startActivity(RecordDetailsActivity.this, new ArrayList<>(copyUserList));
            });
        }

        if (applyRecordVo != null) {
            approval_time = findViewById(R.id.approval_time);
            fl_status = findViewById(R.id.fl_status);
            if (it.getStatus() == Constant.APPLY_STATUS_PASSED) {
                approval_time.setText(DateTimeUtils.millis2Date(approvalUserList.get(approvalUserList.size() - 1).getApprovalTime()));
            } else {
                approval_time.setVisibility(View.INVISIBLE);
            }
            fl_status.addView(StatusUtils.getApplyStatusView(this, it.getStatus()));
        }
    }
}
