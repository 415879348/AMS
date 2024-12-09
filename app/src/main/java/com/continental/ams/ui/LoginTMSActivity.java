package com.continental.ams.ui;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Pair;
import android.widget.EditText;
import com.continental.ams.R;
import com.blankj.utilcode.util.LogUtils;

import com.continental.ams.contract.LoginActContract;
import com.continental.ams.presenter.LoginActPresenter;
import com.continental.sdk.SPConfig;
import com.continental.sdk.SPGlobalManager;
import com.continental.sdk.SPLocal;
import com.continental.sdk.SPSdkUtil;
import com.continental.sdk.base.BaseMvpActivity;
import com.continental.sdk.bean.request.LoginVo;
import com.continental.sdk.bean.response.Token;
import com.continental.sdk.utils.ClickUtil;
import com.continental.sdk.utils.LocalUtils;
import com.continental.sdk.utils.ResUtils;

import androidx.appcompat.app.AppCompatDelegate;

public class LoginTMSActivity extends BaseMvpActivity<LoginActContract.Presenter> implements LoginActContract.View {

    @Override
    protected boolean isShowTitle() {
        return false;
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, LoginTMSActivity.class));
    }

    private EditText et_account;
    private EditText et_password;

    @Override
    protected void init() {
        et_account = findViewById(R.id.et_account);
        et_password = findViewById(R.id.et_password);
        findViewById(R.id.mtv_login).setOnClickListener(v -> {
            mPresenter.login(new LoginVo("esharpAdmin1", "123456"));
            if (ClickUtil.isFastDoubleClick()) {
                return;
            }
            String account = et_account.getText().toString();
            if (TextUtils.isEmpty(account)) {
                showToast(ResUtils.getString(R.string.please_enter_account));
                return;
            }
            String password = et_password.getText().toString();
            if (TextUtils.isEmpty(password)) {
                showToast(ResUtils.getString(R.string.please_enter_password));
                return;
            }
            mPresenter.login(new LoginVo(account, password));
        });
    }

    @Override
    public void onLoginSuc(String it) {

        SPGlobalManager.refreshToken(new Token(it));
        LogUtils.json(SPGlobalManager.getToken());

        SPConfig sPConfig = SPConfig.newBuild()
                .setNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                .build();

        SPSdkUtil.getInstance(this).setConfig(sPConfig).start();
        MainActivity.startActivity(LoginTMSActivity.this);
        finish();
    }

    @Override
    protected Pair<Integer, LoginActContract.Presenter> layoutAndPresenter() {
        return Pair.create(R.layout.activity_login, new LoginActPresenter(this));
    }

}