package com.esharp.sdk.activity.apply.amend;

import android.os.Bundle;
import android.text.InputType;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.esharp.sdk.R;
import com.esharp.sdk.activity.apply.ApplyContract;
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

/**
 * 功能描述：
 *
 * @author (作者) someone
 * 创建时间： 2021/8/10
 */
public class AmendFragment extends BaseMvpFragment<IAmendContract.IPresenter, ApplyContract.IHost> implements IAmendContract.IView {

    @Override
    protected Pair<Integer, IAmendContract.IPresenter> layoutAndPresenter() {
        return Pair.create(R.layout.spsdk_fragment_amend, new AmendPresenter(this));
    }

    public final static String TAG = AmendFragment.class.getSimpleName();
    private SPEditView ev_date, ev_temperature;
    private SPNoteView nv_reason;

    public static Fragment getInstance(ApplyRecordVo applyRecordVo) {
        Fragment fragment = new AmendFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("ApplyRecordVo", applyRecordVo);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void init(View view) {
        ev_date = view.findViewById(R.id.ev_date);
        ev_temperature = view.findViewById(R.id.ev_temperature);
        nv_reason = view.findViewById(R.id.nv_reason);

        EditText content = ev_temperature.findViewById(R.id.content);
        content.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        LogUtils.i(TimeUtils.millis2String(System.currentTimeMillis()));
        ev_date.setRightClick(v -> {

            new DateTimeSelector(v.getContext(), datetime -> {
                LogUtils.json(datetime);
                ev_date.setContent(datetime);
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
                ev_date.setContent(DateTimeUtils.millis2Date(vo.getRepairTime()));
                ev_temperature.setContent(vo.getTemperature().toString());
                nv_reason.setContent(vo.getReason());
                mPresenter.getAmendRecordDetail(vo.getApplyId());
            }
        }

    }

    public <T extends Object> T getData() {
        RequestApplyVo it = new RequestApplyVo();
        it.setRepairTime(DateTimeUtils.parseToLong(ev_date.getContent()));
        it.setTemperature(Float.parseFloat(ev_temperature.getContent()));
        it.setReason(nv_reason.getContent());
        it.setLocation("");
        return (T) it;
    }

    @Override
    public void getAmendRecordDetailSuccess(RecordDetailVo it) {
        LogUtils.json(it);
        if (ObjectUtils.isNotEmpty(it)) {
            nv_reason.setContent(it.getReason());
            mHostView.getAmendRecordDetailSuccess(it);
        }
    }

}
