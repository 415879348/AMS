package com.esharp.sdk.activity.recordDetails.otrecorddetails;

import android.os.Bundle;
import android.util.Pair;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.esharp.sdk.R;
import com.esharp.sdk.activity.recordDetails.RecordDetailsActivity;
import com.esharp.sdk.activity.recordDetails.RecordDetailsContract;
import com.esharp.sdk.base.BaseMvpFragment;
import com.esharp.sdk.bean.response.ApplyRecordVo;
import com.esharp.sdk.bean.response.RecordDetailVo;
import com.esharp.sdk.utils.DateTimeUtils;
import com.esharp.sdk.utils.ResUtils;
import com.esharp.sdk.widget.SPShowTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class OtRecordDetailsFragment extends BaseMvpFragment<OtRecordDetailsContract.Presenter, RecordDetailsContract.IHost> implements OtRecordDetailsContract.View {
    @Override
    protected Pair<Integer, OtRecordDetailsContract.Presenter> layoutAndPresenter() {
        return Pair.create(R.layout.spsdk_fragment_record_details_ot, new OtRecordDetailsPresenter(this));
    }

    public final static String TAG = OtRecordDetailsFragment.class.getSimpleName();

    public static Fragment getInstance(ApplyRecordVo item) {
        Fragment fragment = new OtRecordDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(RecordDetailsActivity.APPLY_RECORD, item);
        fragment.setArguments(bundle);
        return fragment;
    }

    private SPShowTextView stv_userName, stv_department, stv_start_time, stv_end_time, stv_ot_duration, stv_overtime_reason, stv_commit_time;

    @Override
    protected void init(View view) {
        stv_userName = view.findViewById(R.id.stv_userName);
        stv_department = view.findViewById(R.id.stv_department);
        stv_start_time = view.findViewById(R.id.stv_start_time);
        stv_end_time = view.findViewById(R.id.stv_end_time);
        stv_ot_duration = view.findViewById(R.id.stv_ot_duration);
        stv_overtime_reason = view.findViewById(R.id.stv_overtime_reason);
        stv_commit_time = view.findViewById(R.id.stv_commit_time);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle == null) return;
        ApplyRecordVo vo = (ApplyRecordVo) bundle.getSerializable(RecordDetailsActivity.APPLY_RECORD);
        LogUtils.json(vo);
        mPresenter.getOtRecordDetailsAll(vo.getApplyId());
    }

    @Override
    public void getOtRecordDetailsAllSuss(RecordDetailVo it) {
        stv_userName.setDetail(it.getUserName());
        stv_department.setDetail(it.getDepartmentName());
        stv_start_time.setDetail(DateTimeUtils.millis2Date(it.getOtStartTime()));
        stv_end_time.setDetail(DateTimeUtils.millis2Date(it.getOtEndTime()));
        stv_ot_duration.setDetail(String.format(ResUtils.getString(R.string.spsdk_placeholder_hours), it.getOtHour()));
        stv_overtime_reason.setDetail(it.getReason());
        stv_commit_time.setDetail(DateTimeUtils.millis2Date(it.getCreateTime()));

        mHostView.getOtRecordDetailsAllSuss(it);
    }
}
