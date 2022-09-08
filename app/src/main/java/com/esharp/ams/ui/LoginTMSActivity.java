package com.esharp.ams.ui;

import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import android.widget.EditText;

import com.blankj.utilcode.util.LogUtils;
import com.esharp.ams.R;
import com.esharp.ams.contract.LoginActContract;
import com.esharp.ams.presenter.LoginActPresenter;
import com.esharp.sdk.SPConfig;
import com.esharp.sdk.SPGlobalManager;
import com.esharp.sdk.SPLocal;
import com.esharp.sdk.SPSdkUtil;
import com.esharp.sdk.base.BaseMvpActivity;
import com.esharp.sdk.bean.request.LoginVo;
import com.esharp.sdk.bean.response.Token;
import com.esharp.sdk.utils.LocalUtils;

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

        findViewById(R.id.mtv_login).setOnClickListener(v ->
                mPresenter.login(new LoginVo(et_account.getText().toString(), et_password.getText().toString()))
//                mPresenter.login(new LoginVo("test", "123456"))
        );
    }

    @Override
    public void onLoginSuc(String it) {

        SPGlobalManager.refreshToken(new Token(it));
        LogUtils.json(SPGlobalManager.getToken());

//        SPLocal spLocal = LocalUtils.getSystemLocal(LoginTMSActivity.this);
        SPConfig sPConfig = SPConfig.newBuild()
                .setNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//                .setLangCode(SPGlobalManager.getLanguage().getCode())
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