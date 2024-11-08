package com.continental.sdk.base;

import androidx.fragment.app.FragmentManager;

/**
 * 功能描述：Fragment与其宿主Activity通信的基础接口
 *
 * @author (作者) someone
 * 创建时间： 2021/7/9
 */
public interface IHostView extends IBaseView {

    BaseActivity getHost();

    FragmentManager getSupportFragmentManager();

}
