package com.esharp.sdk.base;

/**
 * 功能描述：MVP P层基类
 *
 * @author (作者) someone
 * 创建时间： 2021/7/10
 */
public class BasePresenter<V extends IBaseView> implements IBasePresenter {
    protected final V mView;

    public BasePresenter(V mView) {
        this.mView = mView;
    }
}
