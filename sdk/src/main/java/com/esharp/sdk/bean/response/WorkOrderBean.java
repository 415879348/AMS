package com.esharp.sdk.bean.response;

import java.io.Serializable;

public class WorkOrderBean implements Serializable {

    /**
     * address :
     * applyId : 0
     * applyName :
     * applyTime :
     * companyId :
     * companyName :
     * content :
     * createTime :
     * deviceId : 0
     * deviceName :
     * handlerId : 0
     * handlerName : "qang", //处理人名称
     * id : 0
     * level : 0
     * lowerOrder : 0
     * orderNo :
     * origin : 0
     * overTime :
     * remark :
     * step : 步驟 -1:撤銷 0:結束 1:申請 2:審批 3:處理
     * superOrder : 0
     * title :
     * type : 類型 1:維修工單 2:定時保養
     */

    private String address;
    private Long applyId;
    private String applyName;
    private Long applyTime;
    private String companyId;
    private String companyName;
    private String content;
    private String createTime;
    private Integer deviceId;
    private String deviceName;
    private Integer handlerId;
    private String handlerName;
    private Long id;
    private Integer level;
    private Integer lowerOrder;
    private String orderNo;
    private Integer origin;
    private Long overTime;
    private String remark;
    private Integer step;
    private Integer superOrder;
    private String title;

    private Integer type;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }

    public Long getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Long applyTime) {
        this.applyTime = applyTime;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Integer getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(Integer handlerId) {
        this.handlerId = handlerId;
    }

    public String getHandlerName() {
        return handlerName;
    }

    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getLowerOrder() {
        return lowerOrder;
    }

    public void setLowerOrder(Integer lowerOrder) {
        this.lowerOrder = lowerOrder;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getOrigin() {
        return origin;
    }

    public void setOrigin(Integer origin) {
        this.origin = origin;
    }

    public Long getOverTime() {
        return overTime;
    }

    public void setOverTime(Long overTime) {
        this.overTime = overTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public Integer getSuperOrder() {
        return superOrder;
    }

    public void setSuperOrder(Integer superOrder) {
        this.superOrder = superOrder;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
