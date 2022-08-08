package com.esharp.sdk.activity.recordDetails.amendRecordDetails;

import android.os.Bundle;
import android.util.Pair;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.esharp.sdk.R;
import com.esharp.sdk.activity.recordDetails.RecordDetailsActivity;
import com.esharp.sdk.activity.recordDetails.RecordDetailsContract;
import com.esharp.sdk.base.BaseMvpFragment;
import com.esharp.sdk.bean.response.ApplyRecordVo;
import com.esharp.sdk.bean.response.RecordDetailVo;
import com.esharp.sdk.utils.DateTimeUtils;
import com.esharp.sdk.utils.ResUtils;
import com.esharp.sdk.widget.SPIconTextView;
import com.esharp.sdk.widget.SPShowTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AmendRecordDetailsFragment extends BaseMvpFragment<AmendRecordDetailsContract.Presenter, RecordDetailsContract.IHost> implements AmendRecordDetailsContract.View {
    @Override
    protected Pair<Integer, AmendRecordDetailsContract.Presenter> layoutAndPresenter() {
        return Pair.create(R.layout.spsdk_fragment_record_details_repair, new AmendRecordDetailsPresenter(this));
    }

    public final static String TAG = AmendRecordDetailsFragment.class.getSimpleName();

    public static Fragment getInstance(ApplyRecordVo item) {
        Fragment fragment = new AmendRecordDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(RecordDetailsActivity.APPLY_RECORD, item);
        fragment.setArguments(bundle);
        return fragment;
    }

    private SPShowTextView stv_userName, stv_department, stv_amend_time, stv_temperature, stv_commit_time;
    private SPIconTextView itv_address;

    @Override
    protected void init(View view) {
        stv_userName = view.findViewById(R.id.stv_userName);
        stv_department = view.findViewById(R.id.stv_department);
        stv_amend_time = view.findViewById(R.id.stv_amend_time);
        stv_temperature = view.findViewById(R.id.stv_temperature);
        itv_address = view.findViewById(R.id.itv_address);
        stv_commit_time = view.findViewById(R.id.stv_commit_time);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle == null) return;
        ApplyRecordVo vo = (ApplyRecordVo) bundle.getSerializable(RecordDetailsActivity.APPLY_RECORD);
        LogUtils.json(vo);
        if (ObjectUtils.isNotEmpty(vo.getApplyId())) {
            mPresenter.getAmendRecordDetail(vo.getApplyId());
        }
    }

    @Override
    public void getAmendRecordDetailSuccess(RecordDetailVo it) {
        LogUtils.json(it);

        if (ObjectUtils.isNotEmpty(it)) {
            stv_userName.setDetail(it.getUserName());
            stv_department.setDetail(it.getDepartmentName());
            stv_amend_time.setDetail(DateTimeUtils.millis2Date(it.getRepairTime()));
            stv_temperature.setDetail(it.getTemperature()+"℃");
            stv_temperature.setDetailColour(ResUtils.getColor(R.color.spsdk_color_green_light));
            itv_address.setTitle(it.getDeviceAddressName());
            stv_commit_time.setDetail(DateTimeUtils.millis2Date(it.getCreateTime()));

            mHostView.getAmendRecordDetailSuccess(it);
        }

    }

}
