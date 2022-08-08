package com.esharp.sdk.activity.apply;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.esharp.sdk.Constant;
import com.esharp.sdk.R;
import com.esharp.sdk.Tag;
import com.esharp.sdk.activity.apply.amend.AmendFragment;
import com.esharp.sdk.activity.apply.leave.LeaveFragment;
import com.esharp.sdk.activity.apply.overtime.OTFragment;
import com.esharp.sdk.activity.approverList.ApproverListActivity;
import com.esharp.sdk.activity.approverSelected.ApproverSelectedActivity;
import com.esharp.sdk.activity.copyuserselected.CopyUserSelectedActivity;
import com.esharp.sdk.base.BaseActivity;
import com.esharp.sdk.base.BaseMvpActivity;
import com.esharp.sdk.bean.request.RequestApplyVo;
import com.esharp.sdk.bean.response.ApplyRecordVo;
import com.esharp.sdk.bean.response.ApprovalUserBean;
import com.esharp.sdk.bean.response.CopyUserListBean;
import com.esharp.sdk.bean.response.RecordDetailVo;
import com.esharp.sdk.bean.response.StaffVo;
import com.esharp.sdk.http.GlideUtils;
import com.esharp.sdk.utils.DataConvert;
import com.esharp.sdk.utils.ResUtils;
import com.esharp.sdk.widget.SPTitleView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class ApplyActivity extends BaseMvpActivity<ApplyContract.Presenter> implements ApplyContract.IHost {

    @Override
    public BaseActivity getHost() {
        return this;
    }

    @Override
    protected Pair<Integer, ApplyContract.Presenter> layoutAndPresenter() {
        return Pair.create(R.layout.spsdk_activity_apply, new ApplyPresenter(this));
    }

    public enum Mode{
        COMMON, EDIT,
    }

    private FragmentManager fragmentManager = null;
    private Tag tag = null;
    private Mode mode = null;
    private ApplyRecordVo applyRecordVo = null;
    ImageView iv_select_staff1;
    ImageView iv_select_staff2;

    //条件：0.没条件，1.and，2.or
    int approvalCondition = 2;

    RecordDetailVo mRecordDetailVo = null;
    private LinearLayout ll_approval_list, ll_copy_user_list;
    ArrayList<ApprovalUserBean> approvalUserList = new ArrayList<>();
    ArrayList<CopyUserListBean> copyUserList = new ArrayList<>();

    private final ActivityResultLauncher<Intent> mLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {

        Intent intent;

        if (result.getResultCode() == Activity.RESULT_OK
                && (intent = result.getData()) != null
                && intent.hasExtra(ApproverListActivity.REVIEWER_SELECTED)) {

            if (Objects.requireNonNull(intent.getExtras()).getSerializable(ApproverListActivity.REVIEWER_SELECTED) != null) {

                List<StaffVo> list = castList(intent.getExtras().getSerializable(ApproverListActivity.REVIEWER_SELECTED), StaffVo.class);

                LogUtils.json(list);

                if (ObjectUtils.isNotEmpty(list)) {

                    approvalUserList.addAll(DataConvert.staffVoToApprovalUserBean(list));

                    showApprovalList();
                }
            }
        }
    });

    private final ActivityResultLauncher<Intent> mLauncher2 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {

        Intent intent;

        if (result.getResultCode() == Activity.RESULT_OK
                && (intent = result.getData()) != null
                && intent.hasExtra(ApproverListActivity.COPY_USER_SELECTED)) {

            if (Objects.requireNonNull(intent.getExtras()).getSerializable(ApproverListActivity.COPY_USER_SELECTED) != null) {

                List<StaffVo> list = castList(intent.getExtras().getSerializable(ApproverListActivity.COPY_USER_SELECTED), StaffVo.class);

                LogUtils.json(list);

                if (ObjectUtils.isNotEmpty(list)) {

                    copyUserList.addAll(DataConvert.staffVoToCopyUserBean(list));

                    showCopyUserList();
                }
            }
        }
    });

    public static <T> List<T> castList(Object obj, Class<T> clazz) {
        List<T> result = new ArrayList<T>();
        if(obj instanceof List<?>)
        {
            for (Object o : (List<?>) obj)
            {
                result.add(clazz.cast(o));
            }
            return result;
        }
        return null;
    }

    @Override
    protected void init() {
        tag = (Tag) Objects.requireNonNull(getIntent().getExtras()).getSerializable("Tag");

        if (getIntent().getExtras().containsKey("ApplyRecordVo")) {
            applyRecordVo = (ApplyRecordVo) getIntent().getExtras().getSerializable("ApplyRecordVo");
            mode = Mode.EDIT;
        } else {
            mode = Mode.COMMON;
        }
        LogUtils.json(applyRecordVo);

        fragmentManager = getSupportFragmentManager();

        switch (tag) {
            case AMEND:
                ((SPTitleView)findViewById(R.id.title_view)).setTitle(ResUtils.getString(R.string.spsdk_apply_amend));
                fragmentManager.beginTransaction()
                        .add(R.id.container,
                                AmendFragment.getInstance(applyRecordVo),
                                AmendFragment.TAG)
                        .addToBackStack(AmendFragment.TAG)
                        .commit();
                break;

            case LEAVE:
                ((SPTitleView)findViewById(R.id.title_view)).setTitle(ResUtils.getString(R.string.spsdk_apply_leave));
                fragmentManager.beginTransaction()
                        .add(R.id.container,
                                LeaveFragment.getInstance(applyRecordVo),
                                LeaveFragment.TAG)
                        .addToBackStack(LeaveFragment.TAG)
                        .commit();
                break;

            case OVERTIME:
                ((SPTitleView)findViewById(R.id.title_view)).setTitle(ResUtils.getString(R.string.spsdk_apply_overtime));
                fragmentManager.beginTransaction()
                        .add(R.id.container,
                                OTFragment.getInstance(applyRecordVo),
                                OTFragment.TAG)
                        .addToBackStack(OTFragment.TAG)
                        .commit();
                break;
        }

        iv_select_staff1 = findViewById(R.id.iv_select_staff1);
        iv_select_staff2 = findViewById(R.id.iv_select_staff2);

        // 选择审批人
        iv_select_staff1.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable(Constant.TAG, ApproverListActivity.ApproverListTag.REVIEWER);
            bundle.putSerializable(Constant.DATA, DataConvert.approvalUserBeanToStaffVo(approvalUserList));
            ApproverListActivity.startActivity(ApplyActivity.this, mLauncher, bundle);

        });
        // 选择抄送人
        iv_select_staff2.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable(Constant.TAG, ApproverListActivity.ApproverListTag.COPY_USER);
            bundle.putSerializable(Constant.DATA, copyUserList);
            ApproverListActivity.startActivity(ApplyActivity.this, mLauncher2, bundle);

        });

        ll_approval_list = findViewById(R.id.ll_approval_list);

        ll_copy_user_list = findViewById(R.id.ll_copy_user_list);

        findViewById(R.id.mv_commit).setOnClickListener(v -> {

            List<StaffVo> staffList1 = new ArrayList<>();
            List<StaffVo> staffList2 = new ArrayList<>();

            staffList1.addAll(DataConvert.approvalUserBeanToStaffVo(approvalUserList));

            staffList2.addAll(DataConvert.copyUserBeanToStaffVo(copyUserList));

            if (staffList1.size() == 0 || staffList2.size() == 0) {
                LogUtils.i("要选择审核人和抄送人");
                return;
            }

            List<Fragment> fragments = fragmentManager.getFragments();

            IApplyView iApplyView = (IApplyView) fragments.get(0);

            RequestApplyVo requestApplyVo = iApplyView.getData();

            requestApplyVo.setDeviceAddressId(50);

            RequestApplyVo.OrdinaryFlowNodesBean flowNodes = new RequestApplyVo.OrdinaryFlowNodesBean();

            flowNodes.setApprovalCondition(approvalCondition);

            flowNodes.setApprovalUserIds(getSelectedStaffList(staffList1));

            flowNodes.setCopyUserIds(getSelectedStaffList(staffList2));

            requestApplyVo.setOrdinaryFlowNodes(flowNodes);

            LogUtils.json(requestApplyVo);

            switch (mode) {
                case COMMON:
                    switch (tag) {
                        case AMEND:
                            mPresenter.repairOrdinaryFlowCreate(requestApplyVo);
                            break;

                        case LEAVE:
                    mPresenter.leaveOrdinaryFlowCreate(requestApplyVo);
                            break;

                        case OVERTIME:
                    mPresenter.otOrdinaryFlowCreate(requestApplyVo);
                            break;
                    }
                    break;

                case EDIT:
                    requestApplyVo.setApplyId(mRecordDetailVo.getApplyId());
                    switch (tag) {
                        case AMEND:
                            mPresenter.repairOrdinaryFlowUpdate(requestApplyVo);
                            break;

                        case LEAVE:
                    mPresenter.leaveOrdinaryFlowCreate(requestApplyVo);
                            break;

                        case OVERTIME:
                    mPresenter.otOrdinaryFlowCreate(requestApplyVo);
                            break;
                    }
                    break;
            }




        });
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.popBackStackImmediate()) {
            if (fragmentManager.getBackStackEntryCount() == 0) {

            }
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onOrdinaryFlowCreateSuccess(boolean it) {
        LogUtils.json(it);
        if (it) {
            this.finish();
        }

    }

    @Override
    public void onOrdinaryFlowUpdateSuccess(boolean it) {
        LogUtils.json(it);
        if (it) {
            this.finish();
        }
    }

    @Override
    public void getAmendRecordDetailSuccess(RecordDetailVo it) {
        showData(it);
    }

    @Override
    public void getLeaveRecordDetailAllSuccess(RecordDetailVo it) {
        showData(it);
    }

    @Override
    public void getOtRecordDetailsAllSuccess(RecordDetailVo it) {
        showData(it);
    }

    private void showData(RecordDetailVo it) {
        LogUtils.json(it);
        mRecordDetailVo = it;
        approvalUserList.addAll(it.getOrdinaryFlowDetails().getApprovalUserList());
        copyUserList.addAll(it.getOrdinaryFlowDetails().getCopyUserList());

        showApprovalList();
        showCopyUserList();
    }

    /**
     * 审批人头像及查看全部
     */
    private void showApprovalList() {
        // 审批人列表
        LogUtils.json(approvalUserList);

        ll_approval_list.removeAllViews();

        for (int i = 0; i < approvalUserList.size(); i++) {
            ApprovalUserBean approvalUser = approvalUserList.get(i);
            LogUtils.json(approvalUser);

            View view = LayoutInflater.from(this).inflate(R.layout.spsdk_view_head_removable, null, false);
            ImageView icon = view.findViewById(R.id.iv_icon);
            ImageView cancel = view.findViewById(R.id.iv_cancel);
            TextView title = view.findViewById(R.id.title);

            title.setText(approvalUser.getApprovalUserName());
            GlideUtils.showImage(icon, approvalUser.getHeadPortrait());
            cancel.setOnClickListener(v -> {
                approvalUserList.remove(approvalUser);
                showApprovalList();
            });

            ll_approval_list.addView(view);
        }

        if (approvalUserList.size() != 0) {
            View viewAll = LayoutInflater.from(this).inflate(R.layout.spsdk_view_head_removable, null, false);
            ImageView viewAllIcon = viewAll.findViewById(R.id.iv_icon);
            viewAll.findViewById(R.id.iv_cancel).setVisibility(View.GONE);
            TextView viewAllTitle = viewAll.findViewById(R.id.title);
            viewAllIcon.setImageDrawable(ResUtils.getDrawable(R.drawable.spsdk_shape_rec_blue));
            viewAllTitle.setText(ResUtils.getString(R.string.spsdk_view_all));
            viewAll.setOnClickListener(v -> {
                ApproverSelectedActivity.startActivity(this, new ArrayList<>(approvalUserList));
            });

            ll_approval_list.addView(viewAll, 0);

        }
    }

    /**
     * 抄送人头像及查看全部
     */
    private void showCopyUserList() {
        // 抄送人列表
        LogUtils.json(copyUserList);

        ll_copy_user_list.removeAllViews();

        for (int i = 0; i < copyUserList.size(); i++) {
            CopyUserListBean copyUserBean = copyUserList.get(i);
            LogUtils.json(copyUserBean);

            View view = LayoutInflater.from(this).inflate(R.layout.spsdk_view_head_removable, null, false);
            ImageView icon = view.findViewById(R.id.iv_icon);
            ImageView cancel = view.findViewById(R.id.iv_cancel);
            TextView title = view.findViewById(R.id.title);

            title.setText(copyUserBean.getCopyUserName());
            GlideUtils.showImage(icon, copyUserBean.getHeadPortrait());
            cancel.setOnClickListener(v -> {
                copyUserList.remove(copyUserBean);
                showApprovalList();
            });

            ll_copy_user_list.addView(view);
        }

        if (copyUserList.size() != 0) {
            View viewAll = LayoutInflater.from(this).inflate(R.layout.spsdk_view_head_removable, null, false);

            ImageView viewAllIcon = viewAll.findViewById(R.id.iv_icon);
            TextView viewAllTitle = viewAll.findViewById(R.id.title);
            viewAllIcon.setImageDrawable(ResUtils.getDrawable(R.drawable.spsdk_shape_rec_blue));
            viewAllTitle.setText(ResUtils.getString(R.string.spsdk_view_all));

            viewAll.setOnClickListener(v -> {
                CopyUserSelectedActivity.startActivity(this, new ArrayList<>(copyUserList));
            });

            ll_copy_user_list.addView(viewAll, 0);

//            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) viewAll.getLayoutParams();
//            params.topMargin =  SizeUtils.dp2px(15);
//            viewAll.setLayoutParams(params);
        }
    }

    private List<Long> getSelectedStaffList(List<StaffVo> list) {
        List<Long> selectedStaffList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            selectedStaffList.add(list.get(i).getUserId());
        }
        return selectedStaffList;
    }

    public static void startActivity(Context context, Tag tag) {
        Intent intent = new Intent(context, ApplyActivity.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable("Tag", tag);

        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static void startActivity(Context context, Tag tag, ApplyRecordVo vo) {
        Intent intent = new Intent(context, ApplyActivity.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable("Tag", tag);
        bundle.putSerializable("ApplyRecordVo", vo);

        intent.putExtras(bundle);
        context.startActivity(intent);
    }


}
