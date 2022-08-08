package com.esharp.sdk.activity.login;

import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.esharp.sdk.R;
import com.esharp.sdk.base.BaseMvpActivity;
import com.esharp.sdk.bean.response.TokenVo;
import com.esharp.sdk.widget.MyTextView;

public class LoginActivity extends BaseMvpActivity<LoginContract.Presenter> implements LoginContract.View {

    @Override
    protected Pair<Integer, LoginContract.Presenter> layoutAndPresenter() {
        return Pair.create(R.layout.spsdk_activity_login, new LoginPresenter(this));
    }

    private TextView tv_tel_code;
    private EditText et_verification_code;
    private MyTextView mv_get_verification_code;
    private MyTextView mv_verification_login;
    private MyTextView mv_change_user_login;

    private EditText et_username;
    private EditText et_password;
    private MyTextView mv_user_login;
    private MyTextView mv_forget_password;

    private LinearLayout ll_phone;
    private LinearLayout ll_user;

    @Override
    protected void init() {

        ll_phone = findViewById(R.id.ll_phone);
        ll_user = findViewById(R.id.ll_user);
        ll_phone.setVisibility(View.VISIBLE);
        ll_user.setVisibility(View.GONE);

        // 验证码登录
        tv_tel_code = findViewById(R.id.tv_tel_code);
        et_verification_code = findViewById(R.id.et_verification_code);
        mv_get_verification_code = findViewById(R.id.mv_get_verification_code);
        mv_verification_login = findViewById(R.id.mv_verification_login);
        mv_change_user_login = findViewById(R.id.mv_change_user_login);

        // 用户名登录
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        mv_user_login = findViewById(R.id.mv_user_login);
        mv_forget_password = findViewById(R.id.mv_forget_password);

//        mv_verification_login.setOnClickListener(v -> mPresenter.login("10000@esharp", "123456"));

        mPresenter.login("10086@esharp", "123456");
//        mPresenter.login("A10002@kae", "123456");
//        mPresenter.login("10000@esharp", "123456");
    }


    @Override
    protected boolean isShowTitle() {
        return false;
    }

    @Override
    public void onLoginSuccess(TokenVo it) {
        LogUtils.json(it);
//        SPSdkUtil.getInstance(this)
//                .setToken(it)
//                .setConfig(new SPConfig())
//                .start();
//        MainActivity.startActivity(LoginActivity.this);
//        ApproverListActivity.startActivity(LoginActivity.this);
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }
}
