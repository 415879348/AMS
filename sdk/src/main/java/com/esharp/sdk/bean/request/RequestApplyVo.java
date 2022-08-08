package com.esharp.sdk.bean.request;

import java.util.List;

public class RequestApplyVo {

    /**
     * repairTime : 1634117075000
     * temperature : 1.3
     * reason : 出差开会
     * deviceAddressId : 50
     * otStartTime : 1634117075000
     * otEndTime : 1634117075000
     * otHour : 1.5
     * location : 广州
     * holidayTypeId : 13
     * startTime : 1635698802000
     * endTime : 1635785202000
     * totalMin : 480
     * ordinaryFlowNodes : {"approvalCondition":2,"approvalUserIds":[1736,1735],"copyUserIds":[1736,1735]}
     */

    private long applyId;
    private long repairTime;
    private float temperature;
    private String reason;
    private int deviceAddressId;
    private long otStartTime;
    private long otEndTime;
    private float otHour;
    private String location;
    private int holidayTypeId;
    private long startTime;
    private long endTime;
    private float leaveHour;
    private OrdinaryFlowNodesBean ordinaryFlowNodes;

    public long getApplyId() {
        return applyId;
    }

    public void setApplyId(long applyId) {
        this.applyId = applyId;
    }

    public long getRepairTime() {
        return repairTime;
    }

    public void setRepairTime(long repairTime) {
        this.repairTime = repairTime;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getDeviceAddressId() {
        return deviceAddressId;
    }

    public void setDeviceAddressId(int deviceAddressId) {
        this.deviceAddressId = deviceAddressId;
    }

    public long getOtStartTime() {
        return otStartTime;
    }

    public void setOtStartTime(long otStartTime) {
        this.otStartTime = otStartTime;
    }

    public long getOtEndTime() {
        return otEndTime;
    }

    public void setOtEndTime(long otEndTime) {
        this.otEndTime = otEndTime;
    }

    public float getOtHour() {
        return otHour;
    }

    public void setOtHour(float otHour) {
        this.otHour = otHour;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getHolidayTypeId() {
        return holidayTypeId;
    }

    public void setHolidayTypeId(int holidayTypeId) {
        this.holidayTypeId = holidayTypeId;
    }

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

    public float getLeaveHour() {
        return leaveHour;
    }

    public void setLeaveHour(float leaveHour) {
        this.leaveHour = leaveHour;
    }

    public OrdinaryFlowNodesBean getOrdinaryFlowNodes() {
        return ordinaryFlowNodes;
    }

    public void setOrdinaryFlowNodes(OrdinaryFlowNodesBean ordinaryFlowNodes) {
        this.ordinaryFlowNodes = ordinaryFlowNodes;
    }

    public static class OrdinaryFlowNodesBean {
        /**
         * approvalCondition : 2
         * approvalUserIds : [1736,1735]
         * copyUserIds : [1736,1735]
         */

        private int approvalCondition;
        private List<Long> approvalUserIds;
        private List<Long> copyUserIds;

        public int getApprovalCondition() {
            return approvalCondition;
        }

        public void setApprovalCondition(int approvalCondition) {
            this.approvalCondition = approvalCondition;
        }

        public List<Long> getApprovalUserIds() {
            return approvalUserIds;
        }

        public void setApprovalUserIds(List<Long> approvalUserIds) {
            this.approvalUserIds = approvalUserIds;
        }

        public List<Long> getCopyUserIds() {
            return copyUserIds;
        }

        public void setCopyUserIds(List<Long> copyUserIds) {
            this.copyUserIds = copyUserIds;
        }
    }
}
