package com.esharp.sdk.activity.recordDetails.attendanceRecordDetails;

import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.esharp.sdk.R;
import com.esharp.sdk.activity.recordDetails.RecordDetailsActivity;
import com.esharp.sdk.activity.recordDetails.RecordDetailsContract;
import com.esharp.sdk.base.BaseMvpFragment;
import com.esharp.sdk.bean.response.ApplyRecordVo;
import com.esharp.sdk.bean.response.AttendanceDetailsVo;
import com.esharp.sdk.http.GlideUtils;
import com.esharp.sdk.utils.DateTimeUtils;
import com.esharp.sdk.utils.ResUtils;
import com.esharp.sdk.widget.SPIconTextView;
import com.esharp.sdk.widget.SPShowTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AttendanceRecordDetailsFragment extends BaseMvpFragment<AttendanceRecordDetailsContract.Presenter, RecordDetailsContract.IHost> implements AttendanceRecordDetailsContract.View {
    @Override
    protected Pair<Integer, AttendanceRecordDetailsContract.Presenter> layoutAndPresenter() {
        return Pair.create(R.layout.spsdk_fragment_record_details_attendance, new AttendanceRecordDetailsPresenter(this));
    }

    private final static String RECORD_VO = "RecordVo";

    public final static String TAG = AttendanceRecordDetailsFragment.class.getSimpleName();

    private SPShowTextView tv_staff, tv_department, tv_date, tv_temperature;
    private ImageView iv_icon;
    private SPIconTextView itv_address;

    public static Fragment getInstance(ApplyRecordVo item) {
        Fragment fragment = new AttendanceRecordDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(RecordDetailsActivity.ATTENDANCE_RECORD, item);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void init(View view) {
        tv_staff = view.findViewById(R.id.tv_staff);
        tv_department = view.findViewById(R.id.tv_department);
        tv_date = view.findViewById(R.id.tv_date);
        tv_temperature = view.findViewById(R.id.tv_temperature);
        iv_icon = view.findViewById(R.id.iv_icon);
        itv_address = view.findViewById(R.id.itv_address);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle == null) return;
        ApplyRecordVo vo = (ApplyRecordVo) bundle.getSerializable(RECORD_VO);
        LogUtils.json(vo);
        if (ObjectUtils.isNotEmpty(vo.getRecordId())) {
            mPresenter.attendanceDetails(vo.getRecordId());
        }
    }

    @Override
    public void getAttendanceDetailSuccess(AttendanceDetailsVo it) {
        LogUtils.json(it);

        if (ObjectUtils.isNotEmpty(it)) {
            tv_staff.setDetail(it.getUserName());
            tv_department.setDetail(it.getDepartmentName());
            tv_date.setDetail(DateTimeUtils.millis2Date(it.getSignTime()));
            tv_temperature.setDetail(it.getTemperature() + "℃");
            tv_temperature.setDetailColour(ResUtils.getColor(R.color.spsdk_color_green_light));
            itv_address.setTitle(it.getLocation());
            GlideUtils.showImage(iv_icon, it.getImageUrl());
        }

    }
}
