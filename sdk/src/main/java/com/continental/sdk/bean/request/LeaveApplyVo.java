package com.continental.sdk.bean.request;

import java.util.List;

public class LeaveApplyVo {

    /**
     * holidayTypeId : 13
     * startTime : 1635698802000
     * endTime : 1635785202000
     * totalMin : 480
     * reason : 出去玩
     * ordinaryFlowNodes : {"approvalCondition":1,"approvalUserIds":[1731,1735],"copyUserIds":[1562,1563]}
     */

    private int holidayTypeId;
    private long startTime;
    private long endTime;
    private float totalMin;
    private String reason;
    private OrdinaryFlowNodesBean ordinaryFlowNodes;

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

    public float getTotalMin() {
        return totalMin;
    }

    public void setTotalMin(float totalMin) {
        this.totalMin = totalMin;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public OrdinaryFlowNodesBean getOrdinaryFlowNodes() {
        return ordinaryFlowNodes;
    }

    public void setOrdinaryFlowNodes(OrdinaryFlowNodesBean ordinaryFlowNodes) {
        this.ordinaryFlowNodes = ordinaryFlowNodes;
    }

    public static class OrdinaryFlowNodesBean {
        /**
         * approvalCondition : 1
         * approvalUserIds : [1731,1735]
         * copyUserIds : [1562,1563]
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
