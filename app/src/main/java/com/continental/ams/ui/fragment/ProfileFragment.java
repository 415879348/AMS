package com.continental.ams.ui.fragment;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.blankj.utilcode.util.LogUtils;
import com.continental.ams.contract.MainActContract;
import com.continental.ams.contract.ProfileContract;
import com.continental.ams.presenter.ProfilePresenter;
import com.continental.ams.ui.LoginTMSActivity;
import com.continental.sdk.Constant;
import com.continental.sdk.SPGlobalManager;
import com.continental.sdk.base.BaseMvpFragment;
import com.continental.sdk.bean.response.AssetAlertBean;
import com.continental.sdk.bean.response.UserVo;
import com.continental.sdk.utils.ClickUtil;
import com.continental.sdk.widget.MyTextView;
import com.continental.sdk.widget.SPShowView;
import com.continental.ams.R;
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
    TextView tv_version;
    MyTextView mv_logout;

    @Override
    protected void init(View view) {

        iv_icon_user = view.findViewById(R.id.iv_icon_user);
        tv_user_name = view.findViewById(R.id.tv_user_name);
        iv_edit = view.findViewById(R.id.iv_edit);
        tv_manage = view.findViewById(R.id.tv_manage);
        sv_username = view.findViewById(R.id.sv_username);
        sv_phone = view.findViewById(R.id.sv_phone);
        tv_version = view.findViewById(R.id.tv_version);
        mv_logout = view.findViewById(R.id.mv_logout);
        mv_logout.setOnClickListener(v -> {
            if (ClickUtil.isFastDoubleClick()) {
                return;
            }
            mPresenter.logout();
        });

        setUserInfo(SPGlobalManager.getUserVo());
        tv_version.setText(getVersionName());
        mPresenter.auth();
    }

    @Override
    public void authSus(UserVo it) {
        LogUtils.json(it);
        SPGlobalManager.setUserVo(it);
        setUserInfo(it);

//        Set<String> accountSet = new HashSet<>();
//        accountSet.add("alias_all");
//        LogUtils.json(accountSet);
//        调用 JPush 接口来设置别名。
//        JPushInterface.setTags(mContext, 0, accountSet);

//        new Handler().postDelayed(() -> JPushInterface.setAlias(mContext, 0, "alias_all"), 7000);
//        mPresenter.jpTest();
    }

    private void setUserInfo(UserVo it) {
        if (it != null) {
            tv_user_name.setText(it.getUsername());
            sv_phone.setDetail(it.getPhone());
            sv_username.setDetail(it.getUsername());
        }
    }

    private String getVersionName(){
        PackageInfo packageInfo = null;
        try {
            packageInfo = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "NameNotFoundException";
        }
        return Constant.VERSION + packageInfo.versionCode+"";
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
