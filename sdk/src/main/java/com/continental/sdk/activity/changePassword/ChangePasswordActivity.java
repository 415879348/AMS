package com.continental.sdk.activity.changePassword;

import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import android.widget.EditText;

import com.blankj.utilcode.util.LogUtils;
import com.continental.sdk.R;
import com.continental.sdk.base.BaseMvpActivity;

public class ChangePasswordActivity extends BaseMvpActivity<ChangePasswordContract.Presenter> implements ChangePasswordContract.View {

    @Override
    protected Pair<Integer, ChangePasswordContract.Presenter> layoutAndPresenter() {
        return Pair.create(R.layout.spsdk_activity_change_password, new ChangePasswordPresenter(this));
    }

    private EditText et_original_password;
    private EditText et_new_password;
    private EditText et_confirm_password;
    private EditText mv_confirm;

    @Override
    protected void init() {
        et_original_password = findViewById(R.id.et_original_password);
        et_new_password = findViewById(R.id.et_new_password);
        et_confirm_password = findViewById(R.id.et_confirm_password);

        findViewById(R.id.mv_confirm).setOnClickListener(v -> {
            mPresenter.changePassword(
                    et_original_password.getText().toString(),
                    et_new_password.getText().toString(),
                    et_confirm_password.getText().toString()
            );
        });
    }

    @Override
    public void onChangePasswordSuccess(boolean isTrue) {
        if (isTrue) {
            ChangePasswordActivity.this.finish();
        }
        LogUtils.json(isTrue);
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, ChangePasswordActivity.class));
    }
}
