package com.esharp.sdk.base;

import android.util.Pair;

/**
 * 功能描述：MVP fragment 基类
 *
 * @author (作者) someone
 * 创建时间： 2021/7/10
 */
public abstract class BaseMvpFragment<P extends IBasePresenter, H extends IHostView> extends BaseFragment<H> implements IBaseView {
    protected P mPresenter;

    @Override
    protected int layout() {
        Pair<Integer, P> pair = layoutAndPresenter();
        mPresenter = pair.second;
        return pair.first;
    }

    protected abstract Pair<Integer, P> layoutAndPresenter();
}
