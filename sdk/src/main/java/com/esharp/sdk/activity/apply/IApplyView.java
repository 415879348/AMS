package com.esharp.sdk.activity.apply;

import com.esharp.sdk.base.IBaseView;

/**
 * 功能描述：MVP View层基础接口
 *
 * @author (作者) someone
 * 创建时间： 2021/7/9
 */
public interface IApplyView extends IBaseView {
    <T extends Object> T getData();
}
