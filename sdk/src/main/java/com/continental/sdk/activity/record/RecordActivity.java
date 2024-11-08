package com.continental.sdk.activity.record;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.continental.sdk.Constant;
import com.continental.sdk.R;
import com.continental.sdk.Tag;
import com.continental.sdk.activity.apply.ApplyActivity;
import com.continental.sdk.activity.recordDetails.RecordDetailsActivity;
import com.continental.sdk.base.BaseMvpActivity;
import com.continental.sdk.bean.response.ApplyRecordVo;
import com.continental.sdk.bean.response.ApplyVo;
import com.continental.sdk.utils.DateTimeUtils;
import com.continental.sdk.utils.ResUtils;
import com.continental.sdk.utils.TagUtils;
import com.continental.sdk.widget.SPTitleView;

import java.util.Objects;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

public class RecordActivity extends BaseMvpActivity<RecordContract.Presenter> implements RecordContract.View {

    @Override
    protected Pair<Integer, RecordContract.Presenter> layoutAndPresenter() {
        return Pair.create(R.layout.spsdk_activity_recode, new RecordPresenter(this));
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
        }
        bundle.putSerializable(APPLY_RECORD, it);
        Intent intent = new Intent(context, RecordActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static void startActivityFromAttendance(Context context, ApplyRecordVo it) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.TAG, Tag.ATTENDANCE);
        bundle.putSerializable(APPLY_RECORD, it);
        Intent intent = new Intent(context, RecordActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static final String RECORD_TYPE = "RECORD_TYPE";
    public static final String APPLY_RECORD = "ApplyRecordVo";
    public static final String ATTENDANCE_RECORD = "RecordVo";
//    public static final int RECORD_ATTENDANCE = 1;
//    public static final int RECORD_AMEND = 2;
//    public static final int RECORD_LEAVE = 3;
//    public static final int RECORD_OVERTIME = 4;
    private Tag tag = null;
    ApplyRecordVo applyRecordVo = null;
//    private long startTime = System.currentTimeMillis();
//    private long endTime = DateTimeUtils.getForwardTimeMillis(startTime);
    private RecyclerView recyclerView;
    private RecordAdapter mRecordAdapter;

    @Override
    protected void init() {
        tag = (Tag) Objects.requireNonNull(getIntent().getExtras()).getSerializable(Constant.TAG);

        applyRecordVo = (ApplyRecordVo) getIntent().getSerializableExtra(APPLY_RECORD);
        LogUtils.json(applyRecordVo);

        SPTitleView title_view = findViewById(R.id.title_view);

        AppCompatImageView title_right = title_view.findViewById(R.id.title_right);
        title_right.setImageDrawable(ResUtils.getDrawable(R.mipmap.spsdk_filter));
        title_right.setVisibility(View.VISIBLE);

        recyclerView = findViewById(R.id.recyclerView);

        switch (tag) {
            case ATTENDANCE:
                title_view.setTitle(ResUtils.getString(R.string.spsdk_attendance_record));
                getDefaultAttendanceDataForOneMonth();
                break;
            case AMEND:
                title_view.setTitle(ResUtils.getString(R.string.spsdk_repair_record));
                getDefaultAmendDataForOneMonth();
                break;
            case LEAVE:
                title_view.setTitle(ResUtils.getString(R.string.spsdk_leave_record));
                getDefaultLeaveDataForOneMonth();
                break;
            case OVERTIME:
                title_view.setTitle(ResUtils.getString(R.string.spsdk_overtime_record));
                getDefaultOvertimeDataForOneMonth();
                break;
        }

        recyclerView.setAdapter(mRecordAdapter = new RecordAdapter(tag, new RecordAdapter.Listener() {
            @Override
            public void onItemClick(ApplyRecordVo item) {
                LogUtils.json(item);
                if (item.getRecordType() != null) {
                    RecordDetailsActivity.startActivityFromAttendance(RecordActivity.this, item);
                } else {
                    RecordDetailsActivity.startActivity(RecordActivity.this, item);
                }
            }

            @Override
            public void onModify(ApplyRecordVo item) {
                ApplyActivity.startActivity(RecordActivity.this, TagUtils.getTagFromApplyType(item.getApplyType().intValue()), item);
            }

            @Override
            public void onRevocation(ApplyRecordVo item) {
                switch (tag) {
                    case ATTENDANCE:

                        break;
                    case AMEND:
                        mPresenter.repairOrdinaryFlowRevocation(item.getApplyId());
                        break;
                    case LEAVE:
                        mPresenter.leaveOrdinaryFlowRevocation(item.getApplyId());
                        break;
                    case OVERTIME:
                        mPresenter.oTOrdinaryFlowRevocation(item.getApplyId());
                        break;
                }
            }
        }));

        // 筛选按钮
        title_right.setOnClickListener(v -> new DepartmentStaffSelectorDialog(RecordActivity.this, item -> {
            LogUtils.json(item);

            mRecordAdapter.clearItems();

            switch (tag) {
                case ATTENDANCE:
                    mPresenter.getAttendanceData(item.getStartTime(), item.getEndTime(), 1, Constant.DEFAULT_PAGE_SIZE,
                            item.getDeptIds(), item.getUserIds(), item.getOverTemperature());
                    break;
                case AMEND:
                    mPresenter.getAmendData(item.getStartTime(), item.getEndTime(),1, Constant.DEFAULT_PAGE_SIZE, item.getUserIds());
                    break;
                case LEAVE:
                    mPresenter.getLeaveData(item.getStartTime(), item.getEndTime(), 1, Constant.DEFAULT_PAGE_SIZE, item.getUserIds());
                    break;
                case OVERTIME:
                    mPresenter.getOvertimeData(item.getStartTime(), item.getEndTime(), 1, Constant.DEFAULT_PAGE_SIZE, item.getUserIds());
                    break;
            }

        }).show());

    }

    private void getDefaultAttendanceDataForOneMonth() {
        mPresenter.getAttendanceData(DateTimeUtils.getForwardTimeMillis(System.currentTimeMillis(), -1), System.currentTimeMillis(),  1, Constant.DEFAULT_PAGE_SIZE, "", "", -1);
    }

    private void getDefaultAmendDataForOneMonth() {
        mPresenter.getAmendData(DateTimeUtils.getForwardTimeMillis(System.currentTimeMillis(), -1), System.currentTimeMillis(), 1, Constant.DEFAULT_PAGE_SIZE, "");
    }

    private void getDefaultLeaveDataForOneMonth() {
        mPresenter.getLeaveData(DateTimeUtils.getForwardTimeMillis(System.currentTimeMillis(), -1), System.currentTimeMillis(),  1, Constant.DEFAULT_PAGE_SIZE, "");
    }

    private void getDefaultOvertimeDataForOneMonth() {
        mPresenter.getOvertimeData(DateTimeUtils.getForwardTimeMillis(System.currentTimeMillis(), -1), System.currentTimeMillis(),  1, Constant.DEFAULT_PAGE_SIZE, "");
    }

    private void showData(ApplyVo it) {
        if (it.getRecords() == null || it.getRecords().size() == 0) {
            mRecordAdapter.clearItems();
        } else {
            mRecordAdapter.insertLastItems(it.getRecords());
        }
    }

    @Override
    public void getAttendanceDataSuccess(ApplyVo it) {
        LogUtils.json(it);
        showData(it);
    }

    @Override
    public void getAmendDataSuccess(ApplyVo it) {
        LogUtils.json(it);
        showData(it);
    }

    @Override
    public void getLeaveDataSuccess(ApplyVo it) {
        LogUtils.json(it);
        showData(it);
    }

    @Override
    public void getOvertimeDataSuccess(ApplyVo it) {
        LogUtils.json(it);
        showData(it);
    }

    /**
     * 补签撤销后刷新数据
     * @param isTrue
     */
    @Override
    public void onRepairRevocationSuccess(boolean isTrue) {
        LogUtils.json(isTrue);
        if (isTrue) {
            getDefaultAmendDataForOneMonth();
        }
    }

    /**
     * 请假撤销后刷新数据
     * @param isTrue
     */
    @Override
    public void onLeaveRevocationSuccess(boolean isTrue) {
        LogUtils.json(isTrue);
        if (isTrue) {
            getDefaultLeaveDataForOneMonth();
        }
    }

    /**
     * 加班撤销后刷新数据
     * @param isTrue
     */
    @Override
    public void onOtRevocationSuccess(boolean isTrue) {
        LogUtils.json(isTrue);
        if (isTrue) {
            getDefaultOvertimeDataForOneMonth();
        }
    }
}
