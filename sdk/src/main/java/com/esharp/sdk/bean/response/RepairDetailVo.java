package com.esharp.sdk.bean.response;

import java.util.HashMap;
import java.util.List;

public class RepairDetailVo {

    /**
     * applyId : 1
     * status : 0
     * userId : 1563
     * userName : tong
     * departmentName : R&D
     * repairTime : 1634117075000
     * temperature : 1
     * reason : 外出补卡
     * location : 广州XXX
     * createTime : 1634545383000
     * updateTime : 1634546227000
     * flowNodes : [{"1":{"nodeDetailsId":3,"flowNodeId":0,"applyId":1,"applyType":4,"signType":1,"nodeSort":1,"approvalUserList":[{"1":{"approvalId":1,"status":0,"userId":1564,"userName":"啊強","opinion":null,"sortOrder":1,"approvalTime":null,"createTime":1634613871000,"updateTime":null}},{"2":{"approvalId":7,"status":0,"userId":1564,"userName":"啊強","opinion":null,"sortOrder":1.1,"approvalTime":null,"createTime":1634635371000,"updateTime":null}},{"3":{"approvalId":8,"status":0,"userId":1562,"userName":"feng","opinion":null,"sortOrder":1.2,"approvalTime":null,"createTime":1634635371000,"updateTime":null}}]}},{"2":{"nodeDetailsId":4,"flowNodeId":0,"applyId":1,"applyType":4,"signType":1,"nodeSort":2,"approvalUserList":[{"1":{"approvalId":2,"status":0,"userId":1562,"userName":"feng","opinion":null,"sortOrder":1,"approvalTime":null,"createTime":1634613874000,"updateTime":null}}]}},{"3":{"nodeDetailsId":15,"flowNodeId":0,"applyId":1,"applyType":4,"signType":1,"nodeSort":3,"approvalUserList":[]}},{"4":{"nodeDetailsId":16,"flowNodeId":0,"applyId":1,"applyType":4,"signType":1,"nodeSort":4,"approvalUserList":[]}},{"5":{"nodeDetailsId":17,"flowNodeId":0,"applyId":1,"applyType":4,"signType":1,"nodeSort":5,"approvalUserList":[]}},{"6":{"nodeDetailsId":18,"flowNodeId":0,"applyId":1,"applyType":4,"signType":1,"nodeSort":6,"approvalUserList":[]}}]
     */

    private int applyId;
    private int status;
    private int userId;
    private String userName;
    private String departmentName;
    private long repairTime;
    private float temperature;
    private String reason;
    private String location;
    private long createTime;
    private long updateTime;
    private List<HashMap<String, BeanX> >flowNodes;

    public int getApplyId() {
        return applyId;
    }

    public void setApplyId(int applyId) {
        this.applyId = applyId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

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

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public static class BeanX {
            /**
             * nodeDetailsId : 3
             * flowNodeId : 0
             * applyId : 1
             * applyType : 4
             * signType : 1
             * nodeSort : 1
             * approvalUserList : [{"1":{"approvalId":1,"status":0,"userId":1564,"userName":"啊強","opinion":null,"sortOrder":1,"approvalTime":null,"createTime":1634613871000,"updateTime":null}},{"2":{"approvalId":7,"status":0,"userId":1564,"userName":"啊強","opinion":null,"sortOrder":1.1,"approvalTime":null,"createTime":1634635371000,"updateTime":null}},{"3":{"approvalId":8,"status":0,"userId":1562,"userName":"feng","opinion":null,"sortOrder":1.2,"approvalTime":null,"createTime":1634635371000,"updateTime":null}}]
             */

            private int nodeDetailsId;
            private int flowNodeId;
            private int applyId;
            private int applyType;
            private int signType;
            private int nodeSort;
            private List<HashMap<String, BeanY>> approvalUserList;

            public int getNodeDetailsId() {
                return nodeDetailsId;
            }

            public void setNodeDetailsId(int nodeDetailsId) {
                this.nodeDetailsId = nodeDetailsId;
            }

            public int getFlowNodeId() {
                return flowNodeId;
            }

            public void setFlowNodeId(int flowNodeId) {
                this.flowNodeId = flowNodeId;
            }

            public int getApplyId() {
                return applyId;
            }

            public void setApplyId(int applyId) {
                this.applyId = applyId;
            }

            public int getApplyType() {
                return applyType;
            }

            public void setApplyType(int applyType) {
                this.applyType = applyType;
            }

            public int getSignType() {
                return signType;
            }

            public void setSignType(int signType) {
                this.signType = signType;
            }

            public int getNodeSort() {
                return nodeSort;
            }

            public void setNodeSort(int nodeSort) {
                this.nodeSort = nodeSort;
            }

            public static class BeanY {
                    /**
                     * approvalId : 1
                     * status : 0
                     * userId : 1564
                     * userName : 啊強
                     * opinion : null
                     * sortOrder : 1
                     * approvalTime : null
                     * createTime : 1634613871000
                     * updateTime : null
                     */

                    private int approvalId;
                    private int status;
                    private int userId;
                    private String userName;
                    private Object opinion;
                    private float sortOrder;
                    private Object approvalTime;
                    private long createTime;
                    private Object updateTime;

                    public int getApprovalId() {
                        return approvalId;
                    }

                    public void setApprovalId(int approvalId) {
                        this.approvalId = approvalId;
                    }

                    public int getStatus() {
                        return status;
                    }

                    public void setStatus(int status) {
                        this.status = status;
                    }

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

                    public Object getOpinion() {
                        return opinion;
                    }

                    public void setOpinion(Object opinion) {
                        this.opinion = opinion;
                    }

                    public float getSortOrder() {
                        return sortOrder;
                    }

                    public void setSortOrder(float sortOrder) {
                        this.sortOrder = sortOrder;
                    }

                    public Object getApprovalTime() {
                        return approvalTime;
                    }

                    public void setApprovalTime(Object approvalTime) {
                        this.approvalTime = approvalTime;
                    }

                    public long getCreateTime() {
                        return createTime;
                    }

                    public void setCreateTime(long createTime) {
                        this.createTime = createTime;
                    }

                    public Object getUpdateTime() {
                        return updateTime;
                    }

                    public void setUpdateTime(Object updateTime) {
                        this.updateTime = updateTime;
                    }
                }

    }
}
