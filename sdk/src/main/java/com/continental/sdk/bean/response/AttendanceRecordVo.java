package com.continental.sdk.bean.response;

import java.util.List;

public class AttendanceRecordVo {

    /**
     * size : 10
     * current : 1
     * total : 49
     * pages : 5
     * records : [{"recordId":1427,"recordType":101,"imageUrl":"images/a6baa2bdb9134615ac3735a5b96fb37f/2021-08-25/309/1629874232079.jpg","temperature":36,"signTime":1629874231000}]
     */

    private int size;
    private int current;
    private int total;
    private int pages;
    private List<RecordsBean> records;

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

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    public static class RecordsBean {
        /**
         * recordId : 1427
         * recordType : 101
         * imageUrl : images/a6baa2bdb9134615ac3735a5b96fb37f/2021-08-25/309/1629874232079.jpg
         * temperature : 36
         * signTime : 1629874231000
         */
        private Long userId;
        private String userName;
        private Long recordId;
        private Long recordType;
        private String imageUrl;
        private Float temperature;
        private Long signTime;

        public Long getRecordId() {
            return recordId;
        }

        public void setRecordId(Long recordId) {
            this.recordId = recordId;
        }

        public Long getRecordType() {
            return recordType;
        }

        public void setRecordType(Long recordType) {
            this.recordType = recordType;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public Float getTemperature() {
            return temperature;
        }

        public void setTemperature(Float temperature) {
            this.temperature = temperature;
        }

        public long getSignTime() {
            return signTime;
        }

        public void setSignTime(long signTime) {
            this.signTime = signTime;
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
    }
}
