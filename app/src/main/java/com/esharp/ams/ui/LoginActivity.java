package com.esharp.ams.ui;

import android.util.Pair;
import android.widget.EditText;

import com.esharp.ams.R;
import com.esharp.ams.contract.LoginActContract;
import com.esharp.ams.presenter.LoginActPresenter;
import com.esharp.sdk.SPConfig;
import com.esharp.sdk.SPSdkUtil;
import com.esharp.sdk.base.BaseActivity;
import com.esharp.sdk.base.BaseMvpActivity;
import com.esharp.sdk.bean.request.LoginVo;
import com.esharp.sdk.bean.response.TokenVo;

public class LoginActivity extends BaseMvpActivity<LoginActContract.Presenter> implements LoginActContract.View {

    private EditText et_account;
    private EditText et_password;

    @Override
    protected void init() {
        et_account = findViewById(R.id.et_account);
        et_password = findViewById(R.id.et_password);

        findViewById(R.id.mtv_login).setOnClickListener(v ->
//                mPresenter.login(new LoginVo(et_account.getText().toString(), et_password.getText().toString()))
                mPresenter.login(new LoginVo("test", "test@123"))
        );
    }

    @Override
    public void onLoginSuc(String it) {
        SPSdkUtil.getInstance(this)
                .setToken(new TokenVo(it))
                .setConfig(new SPConfig())
                .start();
        MainActivity.startActivity(LoginActivity.this);
    }

    @Override
    protected Pair<Integer, LoginActContract.Presenter> layoutAndPresenter() {
        return Pair.create(R.layout.activity_login, new LoginActPresenter(this));
    }

    @Override
    public BaseActivity getHost() {
        return null;
    }

    @Override
    protected boolean isShowTitle() {
        return false;
    }
}