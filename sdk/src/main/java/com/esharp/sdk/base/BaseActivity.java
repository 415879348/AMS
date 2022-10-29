package com.esharp.sdk.base;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.esharp.sdk.R;
import com.esharp.sdk.SPGlobalManager;
import com.esharp.sdk.dialog.IBaseProgress;
import com.esharp.sdk.dialog.ProgressDialog;
import com.esharp.sdk.http.HttpException;
import com.esharp.sdk.utils.ActivityUtil;
import com.esharp.sdk.utils.FontUtil;
import com.esharp.sdk.utils.LocalUtils;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * 功能描述：Activity基类
 *
 * @author (作者) soevone
 * 创建时间： 2021/7/9
 */
public abstract class BaseActivity extends AppCompatActivity implements IBaseView {
    private IBaseProgress baseProgress;
    private CompositeDisposable disposable;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.spsdk_anim_right_in, R.anim.spsdk_anim_left_out);
        LocalUtils.initLocal(this);
        SPGlobalManager.addActivity(this);
        AppCompatDelegate.setDefaultNightMode(SPGlobalManager.getNightMode());
        FontUtil.initFontScale(this);
        if (isShowTitle()) {
            ViewGroup contentParent = findViewById(android.R.id.content);
            View contentView = LayoutInflater.from(this)
                    .inflate(getBaseLayout(), contentParent, false);
            final int layoutId = layout();
            LinearLayout ll_activity = contentView.findViewById(R.id.ll_activity);
            if (layoutId > 0) {
                LayoutInflater.from(this).inflate(layoutId, ll_activity);
            }
            setContentView(contentView);
        } else {
            setContentView(layout());
        }
        setStatusBar();
        init();
    }

    protected abstract @LayoutRes
    int layout();

    protected abstract void init();

    protected int getBaseLayout() {
        return R.layout.spsdk_activity_base;
    }

    protected boolean isShowTitle() {
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dismissProgress();
        getDisposable().clear();
        SPGlobalManager.removeActivity(this);
    }

    private Boolean setStatusBar()  {
        // 6.0 以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            return true;
        }
        // 5.0 以上
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            return true;
        }
        // 4.4 - 5.0
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().getAttributes().flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | getWindow().getAttributes().flags);
            return true;
        }
        return false;
    }

    public void showProgress(int resId) {
        this.showProgress(resId == -1 ? "" : this.getString(resId));
    }

    public void showProgress(@Nullable final String msg) {
        this.runOnUiThread(() -> {
            if (baseProgress == null) baseProgress = new ProgressDialog(BaseActivity.this);
            baseProgress.setText(msg == null ? "" : msg);
            if (!baseProgress.isShowing()) baseProgress.show();
        });
    }

    public void dismissProgress() {
        if (baseProgress == null) return;
        this.runOnUiThread(() -> baseProgress.dismiss());
    }

    @Override
    public void onAuthenticationFailed(HttpException e) {
        this.runOnUiThread(() -> ToastUtils.showShort(e.getMessage()));
    }

    public void showToast(@Nullable final String msg) {
        if (msg == null || msg.length() == 0) return;
        this.runOnUiThread(() -> ToastUtils.showShort(msg));
    }

    public void showToast(@StringRes int resId) {
        this.showToast(this.getString(resId));
    }

    protected CompositeDisposable getDisposable() {
        if (disposable == null) disposable = new CompositeDisposable();
        return disposable;
    }

    @Override
    public void addStream(@NonNull Disposable disposable) {
        getDisposable().add(disposable);
    }

    @Override
    public void removeStream(@Nullable Disposable disposable) {
        if (disposable == null) return;
        getDisposable().remove(disposable);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LocalUtils.initLocal(this);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LocalUtils.initLocal(this);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.spsdk_anim_left_in, R.anim.spsdk_anim_right_out);

    }

    enum KeyBoardTouch {
        ALWAYS,
        ABOVE,
        BELOW,
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                ActivityUtil.hideSoftInput(this);
            }
        }
        return super.dispatchTouchEvent(ev);
                
    }

    private boolean isShouldHideKeyboard(View v, MotionEvent ev) {
        if (v instanceof EditText && ev != null) {
            int[] local = new int[]{0, 0};
            v.getLocationInWindow(local);

            switch (shouldAlwaysHideKeyboard()){

                case ALWAYS: return !(ev.getRawX() > local[0] && ev.getRawX() < (local[0] + v.getMeasuredWidth()) && ev.getRawY() > local[1] && ev.getRawY() < (local[1] + v.getMeasuredHeight()));
                case ABOVE: return ev.getRawY() < (local[1] - 10);
                case BELOW: return ev.getRawY() > (local[1] + v.getMeasuredHeight() + 10);
            }
        }
        return false;
    }

    protected KeyBoardTouch shouldAlwaysHideKeyboard() {
        return KeyBoardTouch.ALWAYS;
    }

//    @Override
//    public Resources getResources() {
//
//        Resources resources = super.getResources();
//
//        Configuration configuration = new Configuration();
//
//        configuration.setToDefaults();
//
//        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
//
//        return resources;
//
//    }

}
