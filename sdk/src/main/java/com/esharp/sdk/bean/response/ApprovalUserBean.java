package com.esharp.sdk.bean.response;

import java.io.Serializable;

public class ApprovalUserBean implements Serializable {
    /**
     * approvalUserId : 1736
     * approvalStatus : 10
     * approvalUserName : 陳福強
     * headPortrait : images/101fddaa51514b83a8815ae89141c8ee/user/2021-11-17/1637130242555.jpg
     * flowNodeId: 174, //流程节点ID
     * status: 1, //0关闭处理，1正常
     * departmentName: 開發部, //部门名称
     * approvalOpinion: 同意, //审批意见
     * approvalTime: 1637919868000 //审批时间
     *
     */

    private Long approvalUserId;
    private Long approvalStatus;
    private String approvalUserName;
    private String headPortrait;
    private Long flowNodeId;
    private Long status;
    private String departmentName;
    private String approvalOpinion;
    private Long approvalTime;

    public Long getApprovalUserId() {
        return approvalUserId;
    }

    public void setApprovalUserId(Long approvalUserId) {
        this.approvalUserId = approvalUserId;
    }

    public Long getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(Long approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getApprovalUserName() {
        return approvalUserName;
    }

    public void setApprovalUserName(String approvalUserName) {
        this.approvalUserName = approvalUserName;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public Long getFlowNodeId() {
        return flowNodeId;
    }

    public void setFlowNodeId(Long flowNodeId) {
        this.flowNodeId = flowNodeId;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getApprovalOpinion() {
        return approvalOpinion;
    }

    public void setApprovalOpinion(String approvalOpinion) {
        this.approvalOpinion = approvalOpinion;
    }

    public Long getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(Long approvalTime) {
        this.approvalTime = approvalTime;
    }
}