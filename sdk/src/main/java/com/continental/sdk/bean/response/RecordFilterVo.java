package com.continental.sdk.bean.response;

public class RecordFilterVo {

    /**
     * 前面的时间
     */
    private long startTime;
    /**
     * 后面的时间
     */
    private long endTime;
    private String deptIds;
    private String userIds;
    private int isOverTemperature;

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(String deptIds) {
        this.deptIds = deptIds;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public int getOverTemperature() {
        return isOverTemperature;
    }

    public void setOverTemperature(int isOverTemperature) {
        this.isOverTemperature = isOverTemperature;
    }
}
