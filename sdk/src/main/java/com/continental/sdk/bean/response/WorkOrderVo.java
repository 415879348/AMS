package com.continental.sdk.bean.response;

import java.io.Serializable;
import java.util.List;


public class WorkOrderVo implements Serializable {


    /**
     * current : 0
     * hitCount : true
     * pages : 0
     * records : [{"address":"","applyId":0,"applyName":"","applyTime":"","companyId":"","companyName":"","content":"","createTime":"","deviceId":0,"deviceName":"","handlerId":0,"id":0,"level":0,"lowerOrder":0,"orderNo":"","origin":0,"overTime":"","remark":"","step":0,"superOrder":0,"title":"","type":0,"workOrderOperatorGroupVos":[{"condition":0,"id":0,"orderId":0,"type":0,"workOrderOperatorVos":[{"groupId":0,"id":0,"result":0,"resultTime":"","sort":0,"userId":0,"username":""}]}],"workOrderTraceVos":[{"createTime":"","handlerId":0,"handlerName":"","id":0,"orderId":0,"remark":"","state":0,"step":0}]}]
     * searchCount : true
     * size : 0
     * total : 0
     */

    private Integer current;
    private Boolean hitCount;
    private Integer pages;
    private Boolean searchCount;
    private Integer size;
    private Integer total;
    private List<WorkOrderBean> records;

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Boolean getHitCount() {
        return hitCount;
    }

    public void setHitCount(Boolean hitCount) {
        this.hitCount = hitCount;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Boolean getSearchCount() {
        return searchCount;
    }

    public void setSearchCount(Boolean searchCount) {
        this.searchCount = searchCount;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<WorkOrderBean> getRecords() {
        return records;
    }

    public void setRecords(List<WorkOrderBean> records) {
        this.records = records;
    }
}