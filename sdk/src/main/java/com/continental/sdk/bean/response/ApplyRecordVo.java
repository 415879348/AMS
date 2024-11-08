package com.continental.sdk.bean.response;

import java.io.Serializable;

public class ApplyRecordVo implements Serializable {

    /**
     * applyId : 90
     * applyType : 1
     * status : 0
     * leaveTypeName : 事假
     * leaveHour : 0
     * startTime : 1633920274000
     * endTime : 1633920294000
     * submitTime : 1634635371000
     * temperature : 1
     * repairTime : 1634117075000
     * reason : 赶项目
     */

    private String userId;
    private String userName;
    private Long applyId;
    private Long applyType;
    private Long status;
    private String leaveTypeName = "";
    private Float leaveHour;
    private Float otHour;
    private Long startTime;
    private Long endTime;
    private Long submitTime;
    private Float temperature;
    private Long repairTime;
    private String reason;
    private String imageUrl;
    private Long signTime;
    private Long recordId;
    private Long recordType;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    public Long getApplyType() {
        return applyType;
    }

    public void setApplyType(Long applyType) {
        this.applyType = applyType;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getLeaveTypeName() {
        return leaveTypeName;
    }

    public void setLeaveTypeName(String leaveTypeName) {
        this.leaveTypeName = leaveTypeName;
    }

    public Float getLeaveHour() {
        return leaveHour;
    }

    public void setLeaveHour(Float leaveHour) {
        this.leaveHour = leaveHour;
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

    public Long getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Long submitTime) {
        this.submitTime = submitTime;
    }

    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public Long getRepairTime() {
        return repairTime;
    }

    public void setRepairTime(Long repairTime) {
        this.repairTime = repairTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getSignTime() {
        return signTime;
    }

    public void setSignTime(Long signTime) {
        this.signTime = signTime;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public Long getRecordType() {
        return recordType;
    }

    public void setRecordType(Long recordType) {
        this.recordType = recordType;
    }

    public Float getOtHour() {
        return otHour;
    }

    public void setOtHour(Float otHour) {
        this.otHour = otHour;
    }
}
