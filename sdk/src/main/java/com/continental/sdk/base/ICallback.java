package com.continental.sdk.base;

/**
 * 功能描述：单参回调接口
 *
 * @author (作者) someone
 * 创建时间： 2021/7/10
 */
@FunctionalInterface
public interface ICallback<T> {
    void exec(T t);
}
