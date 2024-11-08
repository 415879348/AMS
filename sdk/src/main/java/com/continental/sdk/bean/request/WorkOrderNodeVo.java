package com.continental.sdk.bean.request;

import com.continental.sdk.bean.response.UrlsBean;

import java.io.Serializable;
import java.util.List;

public class WorkOrderNodeVo implements Serializable {

    /**
     * id : 310
     * orderId : 262
     * userId : 55
     * type : 3
     * startTime : 1674102608000
     * endTime : 1674102666000
     * duration : 58377
     * content : 处理完了
     * result : null
     * status : 1
     * username : null
     * orderNumber : null
     * urls : [{"id":423,"type":7,"joinId":310,"url":"image/7/2023-01-19/1674102647966.jpeg","extension":"jpeg","createTime":1674102648000}]
     */

    private Integer id;
    private Integer orderId;
    private Integer userId;
    private Integer type;
    private Long startTime;
    private Long endTime;
    private Integer duration;
    private String content;
    private Object result;
    private Integer status;
    private Object username;
    private Object orderNumber;
    private List<UrlsBean> urls;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getUsername() {
        return username;
    }

    public void setUsername(Object username) {
        this.username = username;
    }

    public Object getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Object orderNumber) {
        this.orderNumber = orderNumber;
    }

    public List<UrlsBean> getUrls() {
        return urls;
    }

    public void setUrls(List<UrlsBean> urls) {
        this.urls = urls;
    }
}
