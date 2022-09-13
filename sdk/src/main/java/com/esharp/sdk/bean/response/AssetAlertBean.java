package com.esharp.sdk.bean.response;

import java.io.Serializable;

public class AssetAlertBean implements Serializable {
        private String id;
        private String companyId;
        private String deviceName;
        private String deviceNumber;
        private String deviceId;

        /**
         * 1是异常信息，=2时是定时检查
         */
        private Integer type;

        /**
         * 异常类型
         * 1.超温警报
         * 2.电量过低
         * 3.工单超时
         */
        private Integer errorType;
        private String joinId;
        private Long createTime;
        private Integer status;
        private String handlerId;
        private Long handlerTime;
        private String remark;
        private Long date;
        private String message;

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getCompanyId() {
                return companyId;
        }

        public void setCompanyId(String companyId) {
                this.companyId = companyId;
        }

        public String getDeviceName() {
                return deviceName;
        }

        public void setDeviceName(String deviceName) {
                this.deviceName = deviceName;
        }

        public String getDeviceNumber() {
                return deviceNumber;
        }

        public void setDeviceNumber(String deviceNumber) {
                this.deviceNumber = deviceNumber;
        }

        public String getDeviceId() {
                return deviceId;
        }

        public void setDeviceId(String deviceId) {
                this.deviceId = deviceId;
        }

        public Integer getType() {
                return type;
        }

        public void setType(Integer type) {
                this.type = type;
        }

        public Integer getErrorType() {
                return errorType;
        }

        public void setErrorType(Integer errorType) {
                this.errorType = errorType;
        }

        public String getJoinId() {
                return joinId;
        }

        public void setJoinId(String joinId) {
                this.joinId = joinId;
        }

        public Long getCreateTime() {
                return createTime;
        }

        public void setCreateTime(Long createTime) {
                this.createTime = createTime;
        }

        public Integer getStatus() {
                return status;
        }

        public void setStatus(Integer status) {
                this.status = status;
        }

        public String getHandlerId() {
                return handlerId;
        }

        public void setHandlerId(String handlerId) {
                this.handlerId = handlerId;
        }

        public Long getHandlerTime() {
                return handlerTime;
        }

        public void setHandlerTime(Long handlerTime) {
                this.handlerTime = handlerTime;
        }

        public String getRemark() {
                return remark;
        }

        public void setRemark(String remark) {
                this.remark = remark;
        }

        public Long getDate() {
                return date;
        }

        public void setDate(Long date) {
                this.date = date;
        }

        public String getMessage() {
                return message;
        }

        public void setMessage(String message) {
                this.message = message;
        }
}