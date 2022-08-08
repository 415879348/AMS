package com.esharp.sdk.bean.response;

import java.util.List;

public class RecordDetailVo {

    /**
     * applyId : 60
     * applyType : 4
     * status : //0待处理，1已通过，2已拒绝，3撤销
     * userId : 1731
     * userName : 黃鵬鋒
     * departmentName : 開發部
     * repairTime : 1634117075000
     * temperature : 1
     * reason : 出差开会
     * deviceAddressId: 50, //设备地址Id
     * deviceAddressName: 广州办公室, //设备地址名称
     * location : 广州塔
     * createTime : 1637743211000
     * otStartTime : 1634117075000
     * otEndTime : 1634117075000
     * otHour : 1.5
     * organizationId : a6baa2bdb9134615ac3735a5b96fb37f
     * updateTime : null
     * leaveTypeName : 事假
     * startTime : 1636179955000
     * endTime : 1636352755000
     * leaveHour : 8
     * submitTime : 1637720765000
     * ordinaryFlowDetails : {initiator:{userId:1731,userName:黃鵬鋒,applyTime:1637743211000},approvalUserList:[{approvalUserId:1736,approvalStatus:10,approvalUserName:陳福強,headPortrait:images/101fddaa51514b83a8815ae89141c8ee/user/2021-11-17/1637130242555.jpg},{approvalUserId:1735,approvalStatus:10,approvalUserName:曾熾權,headPortrait:images/101fddaa51514b83a8815ae89141c8ee/user/2021-11-17/1637130242555.jpg}],copyUserList:[{copyUserId:1736,copyUserName:陳福強,headPortrait:images/101fddaa51514b83a8815ae89141c8ee/user/2021-11-17/1637130242555.jpg},{copyUserId:1735,copyUserName:曾熾權,headPortrait:images/101fddaa51514b83a8815ae89141c8ee/user/2021-11-17/1637130242555.jpg}]}
     */

    private int applyId;
    private int applyType;
    private int status;
    private int userId;
    private String userName;
    private String departmentName;
    private long repairTime;
    private float temperature;
    private String reason;
    private long deviceAddressId;
    private String deviceAddressName;
    private String location;
    private long createTime;
    private long otStartTime;
    private long otEndTime;
    private float otHour;
    private String organizationId;
    private Object updateTime;
    private String leaveTypeName;
    private long startTime;
    private long endTime;
    private float leaveHour;
    private long submitTime;
    private OrdinaryFlowDetailsBean ordinaryFlowDetails;

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

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public Object getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Object updateTime) {
        this.updateTime = updateTime;
    }

    public String getLeaveTypeName() {
        return leaveTypeName;
    }

    public void setLeaveTypeName(String leaveTypeName) {
        this.leaveTypeName = leaveTypeName;
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

    public long getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(long submitTime) {
        this.submitTime = submitTime;
    }

    public long getDeviceAddressId() {
        return deviceAddressId;
    }

    public void setDeviceAddressId(long deviceAddressId) {
        this.deviceAddressId = deviceAddressId;
    }

    public String getDeviceAddressName() {
        return deviceAddressName;
    }

    public void setDeviceAddressName(String deviceAddressName) {
        this.deviceAddressName = deviceAddressName;
    }

    public OrdinaryFlowDetailsBean getOrdinaryFlowDetails() {
        return ordinaryFlowDetails;
    }

    public void setOrdinaryFlowDetails(OrdinaryFlowDetailsBean ordinaryFlowDetails) {
        this.ordinaryFlowDetails = ordinaryFlowDetails;
    }

    public static class OrdinaryFlowDetailsBean {
        /**
         * initiator : {userId:1731,userName:黃鵬鋒,applyTime:1637743211000}
         * approvalUserList : [{approvalUserId:1736,approvalStatus:10,approvalUserName:陳福強,headPortrait:images/101fddaa51514b83a8815ae89141c8ee/user/2021-11-17/1637130242555.jpg},{approvalUserId:1735,approvalStatus:10,approvalUserName:曾熾權,headPortrait:images/101fddaa51514b83a8815ae89141c8ee/user/2021-11-17/1637130242555.jpg}]
         * copyUserList : [{copyUserId:1736,copyUserName:陳福強,headPortrait:images/101fddaa51514b83a8815ae89141c8ee/user/2021-11-17/1637130242555.jpg},{copyUserId:1735,copyUserName:曾熾權,headPortrait:images/101fddaa51514b83a8815ae89141c8ee/user/2021-11-17/1637130242555.jpg}]
         */

        private InitiatorBean initiator;
        private List<ApprovalUserBean> approvalUserList;
        private List<CopyUserListBean> copyUserList;

        public InitiatorBean getInitiator() {
            return initiator;
        }

        public void setInitiator(InitiatorBean initiator) {
            this.initiator = initiator;
        }

        public List<ApprovalUserBean> getApprovalUserList() {
            return approvalUserList;
        }

        public void setApprovalUserList(List<ApprovalUserBean> approvalUserList) {
            this.approvalUserList = approvalUserList;
        }

        public List<CopyUserListBean> getCopyUserList() {
            return copyUserList;
        }

        public void setCopyUserList(List<CopyUserListBean> copyUserList) {
            this.copyUserList = copyUserList;
        }

        public static class InitiatorBean {
            /**
             * userId : 1731
             * userName : 黃鵬鋒
             * applyTime : 1637743211000
             */

            private int userId;
            private String userName;
            private long applyTime;

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

            public long getApplyTime() {
                return applyTime;
            }

            public void setApplyTime(long applyTime) {
                this.applyTime = applyTime;
            }
        }
    }
}
