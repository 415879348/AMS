package com.continental.sdk.activity.apply.leave;

import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.continental.sdk.R;
import com.continental.sdk.activity.apply.ApplyContract;
import com.continental.sdk.activity.apply.IApplyView;
import com.continental.sdk.base.BaseMvpFragment;
import com.continental.sdk.bean.request.RequestApplyVo;
import com.continental.sdk.bean.response.ApplyRecordVo;
import com.continental.sdk.bean.response.LeaveTypeVo;
import com.continental.sdk.bean.response.RecordDetailVo;
import com.continental.sdk.dialog.ListPopWindow;
import com.continental.sdk.utils.DateTimeUtils;
import com.continental.sdk.widget.DateTimeSelector;
import com.continental.sdk.widget.SPEditView;
import com.continental.sdk.widget.SPNoteView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class LeaveFragment extends BaseMvpFragment<LeaveContract.Presenter, ApplyContract.IHost> implements LeaveContract.View , IApplyView {

    public final static String TAG = LeaveFragment.class.getSimpleName();

    @Override
    protected Pair<Integer, LeaveContract.Presenter> layoutAndPresenter() {
        return Pair.create(R.layout.spsdk_fragment_leave, new LeavePresenter(this));
    }

    public static Fragment getInstance(ApplyRecordVo applyRecordVo) {
        LeaveFragment fragment = new LeaveFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("ApplyRecordVo", applyRecordVo);
        fragment.setArguments(bundle);
        return fragment;
    }

    private SPEditView ev_leave_type = null;
    private View et_leave_content = null;
    private SPEditView ev_start_time = null;
    private SPEditView ev_end_time = null;
    private SPEditView ev_leave_duration = null;
    private SPNoteView ev_leave_reason = null;
    private ImageView iv_leave_type_selector = null;

    private List<LeaveTypeVo> mLeaveTypeVoList = null;
    private ListPopWindow<LeaveTypeVo> listPopWindow = null;

    @Override
    protected void init(View view) {
        ev_leave_type = view.findViewById(R.id.ev_leave_type);
        et_leave_content = ev_leave_type.getContentView();
        ev_start_time = view.findViewById(R.id.ev_start_time);

        ev_end_time = view.findViewById(R.id.ev_end_time);
        ev_leave_duration = view.findViewById(R.id.ev_leave_duration);
        ev_leave_reason = view.findViewById(R.id.nv_leave_reason);
        iv_leave_type_selector = view.findViewById(R.id.iv_leave_type_selector);

        EditText content = ev_leave_duration.findViewById(R.id.content);
        content.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

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



        mPresenter.leaveTypeList();
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
                ev_leave_type.setContent(vo.getLeaveTypeName());

                ev_start_time.setContent(DateTimeUtils.millis2Date(vo.getStartTime()));
                ev_end_time.setContent(DateTimeUtils.millis2Date(vo.getEndTime()));
                ev_leave_duration.setContent(vo.getLeaveHour().toString());

                // 因为ApplyRecordVo的数据不够全，通过接口获取更完整的数据RecordDetailVo
                mPresenter.getLeaveRecordDetail(vo.getApplyId());
            }
        }
    }

    public <T extends Object> T getData() {
        RequestApplyVo it = new RequestApplyVo();
        it.setHolidayTypeId(13);
        if (ObjectUtils.isNotEmpty(mLeaveTypeVoList)) {
            for (int i = 0; i < mLeaveTypeVoList.size(); i++) {
                LeaveTypeVo leaveTypeVo = mLeaveTypeVoList.get(i);
                String name = leaveTypeVo.getName();
                if (name.equals(ev_leave_type.getContent())) {
                    it.setHolidayTypeId(leaveTypeVo.getId());
                }
            }
        }
        it.setStartTime(DateTimeUtils.parseToLong(ev_start_time.getContent()));
        it.setEndTime(DateTimeUtils.parseToLong(ev_end_time.getContent()));
        if (! TextUtils.isEmpty(ev_leave_duration.getContent())) {
            it.setLeaveHour(Float.parseFloat(ev_leave_duration.getContent()));
        }
        it.setReason(ev_leave_reason.getContent());
        return (T) it;
    }

    @Override
    public void getLeaveTypeListSuccess(List<LeaveTypeVo> it) {
        LogUtils.json(it);
        mLeaveTypeVoList = it;

        if (mLeaveTypeVoList.size() > 0) {
            LeaveTypeVo leaveTypeVo = it.get(0);
            ev_leave_type.setTag(leaveTypeVo);

            listPopWindow = new ListPopWindow<>(et_leave_content, getContext(), mLeaveTypeVoList, vo -> {
                LogUtils.json(vo);
                ev_leave_type.setTag(vo);
                ev_leave_type.setContent(vo.getName());
            });

            iv_leave_type_selector.setOnClickListener(v -> {
                LeaveTypeVo bean = (LeaveTypeVo) ev_leave_type.getTag();
                listPopWindow.show(et_leave_content, bean.getId()+"");
            });
        }


    }

    /**
     * 主要获取审批人和抄送人的数据在ApplyActivity显示
     * @param it
     */
    @Override
    public void getLeaveRecordDetailSuccess(RecordDetailVo it) {
        LogUtils.json(it);
        if (ObjectUtils.isNotEmpty(it)) {
            ev_leave_reason.setContent(it.getReason());
            mHostView.getLeaveRecordDetailAllSuccess(it);
        }
    }
}
