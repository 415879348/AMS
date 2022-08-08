package com.esharp.sdk.bean.response;

import java.util.List;

public class ApplyVo {

    /**
     * size : 10
     * current : 1
     * total : 5
     * pages : 1
     * records : [{"applyId":90,"applyType":1,"status":0,"leaveTypeName":"事假","leaveHour":0,"startTime":1633920274000,"endTime":1633920294000,"submitTime":1634635371000},{"applyId":91,"applyType":1,"status":0,"leaveTypeName":"事假","leaveHour":0,"startTime":1633920274000,"endTime":1633920294000,"submitTime":1634895633000},{"applyId":1,"applyType":4,"status":1,"temperature":1,"repairTime":1634117075000,"submitTime":1634545383000},{"applyId":2,"applyType":4,"status":0,"temperature":1,"repairTime":1634117075000,"submitTime":1635325778000},{"applyId":1,"applyType":5,"status":0,"startTime":1634117075000,"endTime":1634117075000,"submitTime":1634613840000,"reason":"赶项目","leaveHour":0}]
     */

    private int size;
    private int current;
    private int total;
    private int pages;
    private List<ApplyRecordVo> records;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<ApplyRecordVo> getRecords() {
        return records;
    }

    public void setRecords(List<ApplyRecordVo> records) {
        this.records = records;
    }

    public static class RecordsBean {
        /**
         * applyId : 90
         * applyType : 1
         * status : 0
         * leaveTypeName : 事假
         * leaveHour : 0
         * startTime : 1633920274000
         * endTime : 1633920294000
         * submitTime : 1634635371000
         * temperature : 1
         * repairTime : 1634117075000
         * reason : 赶项目
         */

        private Long userId;
        private String userName;
        private int applyId;
        private int applyType;
        private int status;
        private String leaveTypeName = "";
        private int leaveHour;
        private long startTime;
        private long endTime;
        private long submitTime;
        private Long signTime;
        private float temperature;
        private long repairTime;
        private String reason;
        private String imageUrl;

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

        public String getLeaveTypeName() {
            return leaveTypeName;
        }

        public void setLeaveTypeName(String leaveTypeName) {
            this.leaveTypeName = leaveTypeName;
        }

        public int getLeaveHour() {
            return leaveHour;
        }

        public void setLeaveHour(int leaveHour) {
            this.leaveHour = leaveHour;
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

        public long getSubmitTime() {
            return submitTime;
        }

        public void setSubmitTime(long submitTime) {
            this.submitTime = submitTime;
        }

        public float getTemperature() {
            return temperature;
        }

        public void setTemperature(float temperature) {
            this.temperature = temperature;
        }

        public long getRepairTime() {
            return repairTime;
        }

        public void setRepairTime(long repairTime) {
            this.repairTime = repairTime;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public Long getSignTime() {
            return signTime;
        }

        public void setSignTime(Long signTime) {
            this.signTime = signTime;
        }
    }
}
