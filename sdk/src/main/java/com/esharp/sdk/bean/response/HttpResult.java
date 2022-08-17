package com.esharp.sdk.bean.response;

/**
 * 功能描述：接口返回数据统一处理bean类
 *
 * @author (作者) someone
 * 创建时间： 2021/7/12
 */
public class HttpResult<T> {
    private String code;
    private String message;
    private String requestId;
    private String error;

    private T data;

    public Optional<T> transform() {
        return new Optional<T>(data);
    }

    public String getResultCode() {
        return code;
    }

    public void setResultCode(String code) {
        this.code = code;
    }

    public String getResultMsg() {
        return message;
    }

    public void setResultMsg(String resultMsg) {
        this.message = resultMsg;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
