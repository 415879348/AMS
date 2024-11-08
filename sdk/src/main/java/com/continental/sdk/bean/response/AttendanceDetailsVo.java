package com.continental.sdk.bean.response;

public class AttendanceDetailsVo {

    /**
     * userId : 1
     * userName : test
     * recordId : 1427
     * recordType : 101
     * status : 1
     * signType : 1
     * departmentName : R&D
     * imageUrl : images/a6baa2bdb9134615ac3735a5b96fb37f/2021-08-25/309/1629874232079.jpg
     * temperature : 36
     * signTime : 1629874231000 //签到时间
     * location : GZ
     */

    private int userId;
    private String userName;
    private int recordId;
    private int recordType;
    private int status;
    private int signType;
    private String departmentName;
    private String imageUrl;
    private float temperature;
    private long signTime;
    private String location;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getRecordType() {
        return recordType;
    }

    public void setRecordType(int recordType) {
        this.recordType = recordType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSignType() {
        return signType;
    }

    public void setSignType(int signType) {
        this.signType = signType;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public long getSignTime() {
        return signTime;
    }

    public void setSignTime(long signTime) {
        this.signTime = signTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
