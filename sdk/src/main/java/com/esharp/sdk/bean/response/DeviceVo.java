package com.esharp.sdk.bean.response;

import java.io.Serializable;
import java.util.List;

public class DeviceVo implements Serializable {


    /**
     * current : 0
     * hitCount : true
     * pages : 0
     * records : [{"active":0,"address":"","addressId":0,"brand":"","brandId":0,"brandModel":"","brandModelId":0,"children":[{"active":0,"address":"","addressId":0,"brand":"","brandId":0,"brandModel":"","brandModelId":0,"children":[{}],"color":"","companyId":"","companyName":"","createId":0,"createTime":"","deviceName":"","deviceNumber":"","deviceType":"","deviceTypeId":0,"errorMessage":"","features":0,"height":0,"hostCode":"","id":0,"lat":0,"length":0,"lon":0,"maintain":0,"parentId":0,"parentName":"","personId":0,"production":"","productionDate":"","remark":"","status":0,"updateId":0,"updateTime":"","urls":[{"createTime":"","extension":"","id":0,"joinId":0,"type":0,"url":""}],"warrantyDate":"","weight":0,"width":0}],"color":"","companyId":"","companyName":"","createId":0,"createTime":"","deviceName":"","deviceNumber":"","deviceType":"","deviceTypeId":0,"errorMessage":"","features":0,"height":0,"hostCode":"","id":0,"lat":0,"length":0,"lon":0,"maintain":0,"parentId":0,"parentName":"","personId":0,"production":"","productionDate":"","remark":"","status":0,"updateId":0,"updateTime":"","urls":[{"createTime":"","extension":"","id":0,"joinId":0,"type":0,"url":""}],"warrantyDate":"","weight":0,"width":0}]
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
    private List<DeviceBean> records;

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

    public List<DeviceBean> getRecords() {
        return records;
    }

    public void setRecords(List<DeviceBean> records) {
        this.records = records;
    }
}
