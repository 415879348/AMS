package com.continental.sdk.bean.response;

import java.io.Serializable;

public class DeviceFieldValueBean implements Serializable {

        /**
         * "code": "", //补充字段编号
         * "deviceId": 0,
         * "fieldId": 0, //补充字段表主键
         * "id": 0,
         * "value": "" //补充字段值
         */
        private String code;
        private Integer deviceId;
        private String fieldId;
        private Integer id;
        private String value;

        public String getCode() {
                return code;
        }

        public void setCode(String code) {
                this.code = code;
        }

        public Integer getDeviceId() {
                return deviceId;
        }

        public void setDeviceId(Integer deviceId) {
                this.deviceId = deviceId;
        }

        public String getFieldId() {
                return fieldId;
        }

        public void setFieldId(String fieldId) {
                this.fieldId = fieldId;
        }

        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public String getValue() {
                return value;
        }

        public void setValue(String value) {
                this.value = value;
        }
}