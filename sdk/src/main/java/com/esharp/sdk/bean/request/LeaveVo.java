package com.esharp.sdk.bean.request;

import java.util.List;

public class LeaveVo {

    /**
     * holidayTypeId : 13
     * startTime : 1633920274000
     * endTime : 1633920294000
     * totalMin : 480
     * reason : 出去玩
     * flowNodes : [{"signType":1,"nodeSort":1,"userList":[{"userId":1564,"sortOrder":1}]},{"signType":1,"nodeSort":2,"userList":[{"userId":1562,"sortOrder":1}]}]
     */

    private int holidayTypeId;
    private long startTime;
    private long endTime;
    private long totalMin;
    private String reason;
    private List<FlowNodesBean> flowNodes;

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

    public long getTotalMin() {
        return totalMin;
    }

    public void setTotalMin(long totalMin) {
        this.totalMin = totalMin;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<FlowNodesBean> getFlowNodes() {
        return flowNodes;
    }

    public void setFlowNodes(List<FlowNodesBean> flowNodes) {
        this.flowNodes = flowNodes;
    }

    public static class FlowNodesBean {
        /**
         * signType : 1
         * nodeSort : 1
         * userList : [{"userId":1564,"sortOrder":1}]
         */

        private int signType;
        private int nodeSort;
        private List<UserListBean> userList;

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

        public List<UserListBean> getUserList() {
            return userList;
        }

        public void setUserList(List<UserListBean> userList) {
            this.userList = userList;
        }

        public static class UserListBean {
            /**
             * userId : 1564
             * sortOrder : 1
             */

            private int userId;
            private int sortOrder;

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getSortOrder() {
                return sortOrder;
            }

            public void setSortOrder(int sortOrder) {
                this.sortOrder = sortOrder;
            }
        }
    }
}
