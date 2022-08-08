package com.esharp.sdk.base;

import android.util.Pair;

/**
 * 功能描述：Mvp Activity基类
 *
 * @author (作者) someone
 * 创建时间： 2021/7/9
 */
public abstract class BaseMvpActivity<P extends IBasePresenter> extends BaseActivity {
    protected P mPresenter;

    @Override
    protected int layout() {
        Pair<Integer, P> pair = layoutAndPresenter();
        mPresenter = pair.second;
        return pair.first;
    }

    protected abstract Pair<Integer, P> layoutAndPresenter();
}
