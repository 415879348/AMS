package com.continental.sdk.bean.response;

import java.io.Serializable;

public class RecordVo implements Serializable {


    /**
     * recordId : 1475
     * recordType : 101
     * imageUrl : images/a6baa2bdb9134615ac3735a5b96fb37f/2021-08-31/309/1630394350976.jpg
     * temperature : 36.1
     * signTime : 1630394350000
     */

    private String userId;
    private String userName;
    private int recordId;
    private int recordType;
    private String imageUrl;
    private float temperature;
    private long signTime;

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public long getSignTime() {
        return signTime;
    }

    public void setSignTime(long signTime) {
        this.signTime = signTime;
    }


}
