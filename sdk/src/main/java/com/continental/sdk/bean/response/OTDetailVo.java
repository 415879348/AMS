package com.continental.sdk.bean.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OTDetailVo {

    /**
     * applyId : 1
     * status : 0
     * userId : 1563
     * userName : tong
     * departmentName : R&D
     * otStartTime : 1634117075000
     * otEndTime : 1634117075000
     * reason : 赶项目
     * location : 广州
     * organizationId : a6baa2bdb9134615ac3735a5b96fb37f
     * createTime : 1634613840000
     * updateTime : 1634624068000
     * flowNodes : [{"1":{"nodeDetailsId":7,"flowNodeId":0,"applyId":1,"applyType":5,"signType":1,"nodeSort":1,"approvalUserList":[{"1":{"approvalId":5,"status":0,"userId":1564,"userName":"啊強","opinion":null,"sortOrder":1,"approvalTime":null,"createTime":1634613839000,"updateTime":null}}]}},{"2":{"nodeDetailsId":8,"flowNodeId":0,"applyId":1,"applyType":5,"signType":1,"nodeSort":2,"approvalUserList":[{"1":{"approvalId":6,"status":0,"userId":1562,"userName":"feng","opinion":null,"sortOrder":1,"approvalTime":null,"createTime":1634613839000,"updateTime":null}}]}}]
     */

    private int applyId;
    private int status;
    private int userId;
    private String userName;
    private String departmentName;
    private long otStartTime;
    private long otEndTime;
    private String reason;
    private String location;
    private String organizationId;
    private long createTime;
    private long updateTime;
    private List<FlowNodesBean> flowNodes;

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

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
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

    public List<FlowNodesBean> getFlowNodes() {
        return flowNodes;
    }

    public void setFlowNodes(List<FlowNodesBean> flowNodes) {
        this.flowNodes = flowNodes;
    }

    public static class FlowNodesBean {
        /**
         * 1 : {"nodeDetailsId":7,"flowNodeId":0,"applyId":1,"applyType":5,"signType":1,"nodeSort":1,"approvalUserList":[{"1":{"approvalId":5,"status":0,"userId":1564,"userName":"啊強","opinion":null,"sortOrder":1,"approvalTime":null,"createTime":1634613839000,"updateTime":null}}]}
         * 2 : {"nodeDetailsId":8,"flowNodeId":0,"applyId":1,"applyType":5,"signType":1,"nodeSort":2,"approvalUserList":[{"1":{"approvalId":6,"status":0,"userId":1562,"userName":"feng","opinion":null,"sortOrder":1,"approvalTime":null,"createTime":1634613839000,"updateTime":null}}]}
         */

        @SerializedName("1")
        private _$1BeanX _$1;
        @SerializedName("2")
        private _$2Bean _$2;

        public _$1BeanX get_$1() {
            return _$1;
        }

        public void set_$1(_$1BeanX _$1) {
            this._$1 = _$1;
        }

        public _$2Bean get_$2() {
            return _$2;
        }

        public void set_$2(_$2Bean _$2) {
            this._$2 = _$2;
        }

        public static class _$1BeanX {
            /**
             * nodeDetailsId : 7
             * flowNodeId : 0
             * applyId : 1
             * applyType : 5
             * signType : 1
             * nodeSort : 1
             * approvalUserList : [{"1":{"approvalId":5,"status":0,"userId":1564,"userName":"啊強","opinion":null,"sortOrder":1,"approvalTime":null,"createTime":1634613839000,"updateTime":null}}]
             */

            private int nodeDetailsId;
            private int flowNodeId;
            private int applyId;
            private int applyType;
            private int signType;
            private int nodeSort;
            private List<ApprovalUserListBean> approvalUserList;

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

            public List<ApprovalUserListBean> getApprovalUserList() {
                return approvalUserList;
            }

            public void setApprovalUserList(List<ApprovalUserListBean> approvalUserList) {
                this.approvalUserList = approvalUserList;
            }

            public static class ApprovalUserListBean {
                /**
                 * 1 : {"approvalId":5,"status":0,"userId":1564,"userName":"啊強","opinion":null,"sortOrder":1,"approvalTime":null,"createTime":1634613839000,"updateTime":null}
                 */

                @SerializedName("1")
                private _$1Bean _$1;

                public _$1Bean get_$1() {
                    return _$1;
                }

                public void set_$1(_$1Bean _$1) {
                    this._$1 = _$1;
                }

                public static class _$1Bean {
                    /**
                     * approvalId : 5
                     * status : 0
                     * userId : 1564
                     * userName : 啊強
                     * opinion : null
                     * sortOrder : 1
                     * approvalTime : null
                     * createTime : 1634613839000
                     * updateTime : null
                     */

                    private int approvalId;
                    private int status;
                    private int userId;
                    private String userName;
                    private Object opinion;
                    private int sortOrder;
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

                    public int getSortOrder() {
                        return sortOrder;
                    }

                    public void setSortOrder(int sortOrder) {
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

        public static class _$2Bean {
            /**
             * nodeDetailsId : 8
             * flowNodeId : 0
             * applyId : 1
             * applyType : 5
             * signType : 1
             * nodeSort : 2
             * approvalUserList : [{"1":{"approvalId":6,"status":0,"userId":1562,"userName":"feng","opinion":null,"sortOrder":1,"approvalTime":null,"createTime":1634613839000,"updateTime":null}}]
             */

            private int nodeDetailsId;
            private int flowNodeId;
            private int applyId;
            private int applyType;
            private int signType;
            private int nodeSort;
            private List<ApprovalUserListBeanX> approvalUserList;

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

            public List<ApprovalUserListBeanX> getApprovalUserList() {
                return approvalUserList;
            }

            public void setApprovalUserList(List<ApprovalUserListBeanX> approvalUserList) {
                this.approvalUserList = approvalUserList;
            }

            public static class ApprovalUserListBeanX {
                /**
                 * 1 : {"approvalId":6,"status":0,"userId":1562,"userName":"feng","opinion":null,"sortOrder":1,"approvalTime":null,"createTime":1634613839000,"updateTime":null}
                 */

                @SerializedName("1")
                private _$1BeanXX _$1;

                public _$1BeanXX get_$1() {
                    return _$1;
                }

                public void set_$1(_$1BeanXX _$1) {
                    this._$1 = _$1;
                }

                public static class _$1BeanXX {
                    /**
                     * approvalId : 6
                     * status : 0
                     * userId : 1562
                     * userName : feng
                     * opinion : null
                     * sortOrder : 1
                     * approvalTime : null
                     * createTime : 1634613839000
                     * updateTime : null
                     */

                    private int approvalId;
                    private int status;
                    private int userId;
                    private String userName;
                    private Object opinion;
                    private int sortOrder;
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

                    public int getSortOrder() {
                        return sortOrder;
                    }

                    public void setSortOrder(int sortOrder) {
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
    }
}
