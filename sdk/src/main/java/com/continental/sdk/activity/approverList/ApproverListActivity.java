package com.continental.sdk.activity.approverList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.continental.sdk.Constant;
import com.continental.sdk.R;
import com.continental.sdk.base.BaseActivity;
import com.continental.sdk.base.BaseMvpActivity;
import com.continental.sdk.bean.response.StaffVo;
import com.continental.sdk.utils.RecyclerViewDivider;
import com.continental.sdk.utils.ResUtils;
import com.continental.sdk.widget.MyTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.activity.result.ActivityResultLauncher;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ApproverListActivity extends BaseMvpActivity<ApproverListContract.Presenter> implements ApproverListContract.View {
    @Override
    protected Pair<Integer, ApproverListContract.Presenter> layoutAndPresenter() {
        return Pair.create(R.layout.spsdk_activity_approver_list, new ApproverListPresenter(this));
    }

    public static final String REVIEWER_SELECTED = "reviewer_selected";
    public static final String COPY_USER_SELECTED = "copy_user_selected";
    public static final String APPROVAL_CONDITION = "approval_condition";

    public static void startActivity(BaseActivity activity, ActivityResultLauncher<Intent> mLauncher, Bundle bundle) {
        Intent intent = new Intent(activity, ApproverListActivity.class);
        intent.putExtras(bundle);
        mLauncher.launch(intent);
    }

    private ApproverListTag tag = null;

    public enum ApproverListTag {
        REVIEWER, COPY_USER
    }

    private CheckBox cb_checkbox = null;
    private RecyclerView mRecyclerView = null;
    private TextView tv_selected = null;
    private TextView tv_most_select = null;
    private RadioGroup radioGroup = null;
    private MyTextView mv_confirm = null;
    private ReviewerAdapter mAdapter;

    List<StaffVo> mList = null;

    @Override
    @SuppressWarnings("unchecked")
    protected void init() {

        tag = (ApproverListTag) Objects.requireNonNull(getIntent().getExtras()).getSerializable(Constant.TAG);

        mList = (List<StaffVo>) Objects.requireNonNull(getIntent().getExtras()).getSerializable(Constant.DATA);

        initReviewer();

        switch (tag) {
            case REVIEWER:

                break;

            case COPY_USER:
                initCopyUser();
                break;
        }


        mPresenter.userList(mList);
    }

    private void initCopyUser() {
        radioGroup.setVisibility(View.GONE);
    }

    private void initReviewer() {
        cb_checkbox = findViewById(R.id.cb_checkbox);
        tv_selected = findViewById(R.id.tv_selected);
        tv_most_select = findViewById(R.id.tv_most_select);
        radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rb_and) {
                System.out.println("===and===");
            } else if (checkedId == R.id.rb_or) {
                System.out.println("===or===");
            }
            LogUtils.i("===onCheckedChanged(RadioGroup group="+group+", int checkedId="+checkedId+")==");
        });

        mv_confirm = findViewById(R.id.mv_confirm);
        mRecyclerView = findViewById(R.id.recyclerView);

        mRecyclerView.addItemDecoration(new RecyclerViewDivider(
                ApproverListActivity.this,
                LinearLayoutManager.HORIZONTAL, 1,
                ResUtils.getColor(R.color.spsdk_color_gray2)));

        tv_selected.setText(String.format(ResUtils.getString(R.string.spsdk_select_people), 0));
        tv_most_select.setText(String.format(ResUtils.getString(R.string.spsdk_most_select_people), 10));
        mv_confirm.setText(String.format(ResUtils.getString(R.string.spsdk_confirm_people), 0, 10));

        cb_checkbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                mAdapter.selectAll();
                mAdapter.notifyDataSetChanged();
                tv_selected.setText(String.format(ResUtils.getString(R.string.spsdk_select_people), mAdapter.getSelectedData().size()));
                mv_confirm.setText(String.format(ResUtils.getString(R.string.spsdk_confirm_people), mAdapter.getSelectedData().size(), 10));
            } else {
                mAdapter.unSelectAll();
                mAdapter.notifyDataSetChanged();
                tv_selected.setText(String.format(ResUtils.getString(R.string.spsdk_select_people), 0));
                mv_confirm.setText(String.format(ResUtils.getString(R.string.spsdk_confirm_people), 0, 10));
            }
        });

        mRecyclerView.setAdapter(mAdapter = new ReviewerAdapter(new ReviewerAdapter.Listener() {
            @Override
            public void onMoreThan() {
                ToastUtils.showShort(String.format(ResUtils.getString(R.string.spsdk_most_select_people), 10));
            }

            @Override
            public void selected(int count) {
                LogUtils.json(count);
                tv_selected.setText(String.format(ResUtils.getString(R.string.spsdk_select_people), count));
                mv_confirm.setText(String.format(ResUtils.getString(R.string.spsdk_confirm_people), count, 10));
            }
        }));

        mv_confirm.setOnClickListener(v -> {
            Intent intent = new Intent();


            if (tag == ApproverListTag.REVIEWER) {
                intent.putExtra(REVIEWER_SELECTED, new ArrayList<>(mAdapter.getSelectedData()));

                if (radioGroup.getCheckedRadioButtonId() == R.id.rb_and) {
                    LogUtils.i("===and===");
                    intent.putExtra(APPROVAL_CONDITION, 1);
                } else if (radioGroup.getCheckedRadioButtonId() == R.id.rb_or) {
                    LogUtils.i("===or===");
                    intent.putExtra(APPROVAL_CONDITION, 2);
                } else {
                    LogUtils.i("===没条件===");
                    intent.putExtra(APPROVAL_CONDITION, 0);
                }
            } else {
                intent.putExtra(COPY_USER_SELECTED, new ArrayList<>(mAdapter.getSelectedData()));
            }

            setResult(Activity.RESULT_OK, intent);
            finish();
        });

    }

    @Override
    public void getUserListSuccess(List<StaffVo> it) {
        LogUtils.json(it);
        if (it == null) {
            return;
        }

        if (mAdapter.getItemCount() == 0) {
            mAdapter.refreshAllItems(it);
        } else {
            mAdapter.insertItem(it, 0);
        }
    }

}
