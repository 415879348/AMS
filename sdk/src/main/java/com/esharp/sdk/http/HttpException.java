package com.esharp.sdk.http;

/**
 * 功能描述：自定义网络请求异常类，处理所有非成功状态
 *
 * @author (作者) someone
 * 创建时间： 2021/7/10
 */
public class HttpException extends Exception {

    private final String code;

    public HttpException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
