package com.esharp.ams.ui.fragment;

import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.esharp.ams.R;
import com.esharp.ams.contract.MainActContract;
import com.esharp.ams.contract.ProfileContract;
import com.esharp.ams.presenter.ProfilePresenter;
import com.esharp.ams.ui.LoginTMSActivity;
import com.esharp.sdk.SPGlobalManager;
import com.esharp.sdk.base.BaseMvpFragment;
import com.esharp.sdk.bean.response.AssetAlertBean;
import com.esharp.sdk.bean.response.UserVo;
import com.esharp.sdk.widget.MyTextView;
import com.esharp.sdk.widget.SPShowView;

import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;

public class ProfileFragment extends BaseMvpFragment<ProfileContract.Presenter, MainActContract.IHost> implements ProfileContract.View {

    @Override
    protected Pair<Integer, ProfileContract.Presenter> layoutAndPresenter() {
        return Pair.create(R.layout.fragment_profile, new ProfilePresenter(this));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    ImageView iv_icon_user;
    TextView tv_user_name;
    ImageView iv_edit;
    TextView tv_manage;
    SPShowView sv_username;
    SPShowView sv_phone;
    MyTextView mv_logout;

    @Override
    protected void init(View view) {

        iv_icon_user = view.findViewById(R.id.iv_icon_user);
        tv_user_name = view.findViewById(R.id.tv_user_name);
        iv_edit = view.findViewById(R.id.iv_edit);
        tv_manage = view.findViewById(R.id.tv_manage);
        sv_username = view.findViewById(R.id.sv_username);
        sv_phone = view.findViewById(R.id.sv_phone);
        mv_logout = view.findViewById(R.id.mv_logout);
        mv_logout.setOnClickListener(v -> mPresenter.logout());

        mPresenter.auth();
    }

    @Override
    public void authSus(UserVo it) {
        LogUtils.json(it);
        SPGlobalManager.setUserVo(it);

//        Set<String> accountSet = new HashSet<>();
//        accountSet.add("alias_all");
//        LogUtils.json(accountSet);
//        调用 JPush 接口来设置别名。
//        JPushInterface.setTags(mContext, 0, accountSet);
        JPushInterface.setAlias(mContext, 0, "alias_all");
        tv_user_name.setText(it.getUsername());
        sv_phone.setDetail(it.getPhone());
        sv_username.setDetail(it.getUsername());

        mPresenter.jpTest();
    }

    @Override
    public void jpTest(AssetAlertBean it) {
        LogUtils.json(it);
    }

    @Override
    public void onLogoutSuccess(Boolean it) {
        if (it) {
            SPGlobalManager.logout(LoginTMSActivity.class);
        }
    }
}
