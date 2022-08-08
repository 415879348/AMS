package com.esharp.sdk.activity.apply.overtime;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.esharp.sdk.R;
import com.esharp.sdk.activity.apply.ApplyContract;
import com.esharp.sdk.activity.apply.IApplyView;
import com.esharp.sdk.base.BaseMvpFragment;
import com.esharp.sdk.bean.request.RequestApplyVo;
import com.esharp.sdk.bean.response.ApplyRecordVo;
import com.esharp.sdk.bean.response.RecordDetailVo;
import com.esharp.sdk.utils.DateTimeUtils;
import com.esharp.sdk.widget.DateTimeSelector;
import com.esharp.sdk.widget.SPEditView;
import com.esharp.sdk.widget.SPNoteView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class OTFragment extends BaseMvpFragment<OTContract.Presenter, ApplyContract.IHost> implements OTContract.View , IApplyView {

    public final static String TAG = OTFragment.class.getSimpleName();

    @Override
    protected Pair<Integer, OTContract.Presenter> layoutAndPresenter() {
        return Pair.create(R.layout.spsdk_fragment_overtime, new OTPresenter(this));
    }

    public static Fragment getInstance(ApplyRecordVo applyRecordVo) {
        OTFragment fragment = new OTFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("ApplyRecordVo", applyRecordVo);
        fragment.setArguments(bundle);
        return fragment;
    }

    private SPEditView ev_start_time = null;
    private SPEditView ev_end_time = null;
    private SPEditView ev_ot_duration = null;
    private SPNoteView nv_ot_reason = null;

    @Override
    protected void init(View view) {
        ev_start_time = view.findViewById(R.id.ev_start_time);
        ev_end_time = view.findViewById(R.id.ev_end_time);
        ev_ot_duration = view.findViewById(R.id.ev_ot_duration);
        nv_ot_reason = view.findViewById(R.id.nv_ot_reason);

        ev_start_time.setRightClick(v -> {

            new DateTimeSelector(v.getContext(), datetime -> {
                LogUtils.json(datetime);
                ev_start_time.setContent(datetime);
            });

        });

        ev_end_time.setRightClick(v -> {

            new DateTimeSelector(v.getContext(), datetime -> {
                LogUtils.json(datetime);
                ev_end_time.setContent(datetime);
            });

        });

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle == null) return;
        if (bundle.containsKey("ApplyRecordVo")) {
            ApplyRecordVo vo = (ApplyRecordVo) bundle.getSerializable("ApplyRecordVo");
            LogUtils.json(vo);
            if (vo != null) {
                ev_start_time.setContent(DateTimeUtils.millis2Date(vo.getStartTime()));
                ev_end_time.setContent(DateTimeUtils.millis2Date(vo.getEndTime()));
                ev_ot_duration.setContent(vo.getOtHour().toString());
                // 因为ApplyRecordVo的数据不够全，通过接口获取更完整的数据RecordDetailVo
                mPresenter.getOTRecordDetail(vo.getApplyId());
            }
        }
    }

    public <T extends Object> T getData() {
        RequestApplyVo it = new RequestApplyVo();

        it.setOtStartTime(DateTimeUtils.parseToLong(ev_start_time.getContent()));
        it.setOtEndTime(DateTimeUtils.parseToLong(ev_end_time.getContent()));
        if (! TextUtils.isEmpty(ev_ot_duration.getContent())) {
            it.setOtHour(Float.parseFloat(ev_ot_duration.getContent()));
        }
        it.setReason(nv_ot_reason.getContent());
        return (T) it;
    }

    /**
     * 主要获取审批人和抄送人的数据在ApplyActivity显示
     * @param it
     */
    @Override
    public void getOTRecordDetailSuccess(RecordDetailVo it) {
        LogUtils.json(it);
        if (ObjectUtils.isNotEmpty(it)) {
            nv_ot_reason.setContent(it.getReason());
            mHostView.getOtRecordDetailsAllSuccess(it);
        }
    }
}
