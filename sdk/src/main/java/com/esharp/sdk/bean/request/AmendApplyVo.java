package com.esharp.sdk.bean.request;

import java.util.List;

public class AmendApplyVo {


    /**
     * repairTime : 1634117075000
     * temperature : 1
     * reason : 出差开会
     * location : 广州塔
     * ordinaryFlowNodes : {"approvalCondition":2,"approvalUserIds":[1736,1735],"copyUserIds":[1736,1735]}
     */

    private long repairTime;
    private float temperature;
    private String reason;
    private String location;
    private OrdinaryFlowNodesBean ordinaryFlowNodes;

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
         * approvalCondition : 2  //条件：0.没条件，1.and，2.or
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
