package com.esharp.sdk.bean.request;

import java.util.List;

public class OverTimeApplyVo {

    /**
     * otStartTime : 1634117075000
     * otEndTime : 1634117075000
     * otHour : 1.5
     * reason : 赶项目
     * location : 广州
     * ordinaryFlowNodes : {"approvalCondition":1,"approvalUserIds":[1731,1735],"copyUserIds":[1562,1563]}
     */

    private long otStartTime;
    private long otEndTime;
    private float otHour;
    private String reason;
    private String location;
    private OrdinaryFlowNodesBean ordinaryFlowNodes;

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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
