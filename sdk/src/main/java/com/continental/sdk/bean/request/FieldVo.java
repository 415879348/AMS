package com.continental.sdk.bean.request;

import java.io.Serializable;

public class FieldVo implements Serializable {

    /**
     * {
     *     "id": 8, //主键
     *     "code": "refrigerator_max_temperature", //补充字段编码
     *     "type": 1, //类型 0：整数 1：小数 2：字符串 3：日期 4：单选 5：下拉
     *     "dict": null, //下拉字典类型 type为5时用
     *     "length": 5, //长度
     *     "place": 1, //小数位
     *     "nullable": 1, //是否空 0：可空 1：非空
     *     "regular": null, //正则
     *     "active": 0, //删除标记 0：正常 1：删除
     *     "features": 1, //装置类型：0：其他 1：雪柜 2：大华设备 3：IOT网关 4：云帆瑞达跌倒雷达 5：云帆瑞达睡眠雷达
     *     "deviceFieldValue": {
     *         "id": 1018, //值主键
     *         "fieldId": 8, //补充字段主键
     *         "deviceId": 173, //资产主键
     *         "value": "32" //补充字段值
     *     } //补充字段值
     * }
     */
    private String id;
    private String code;
    private Integer type;
    private Object dict;
    private Integer length;
    private Integer place;
    private Integer nullable;
    private String regular;
    private String fieldName;
    private Integer active;
    private Integer features;

    private DeviceFieldValueBean deviceFieldValue;

    public static class DeviceFieldValueBean {
        private String id;
        private String fieldId;
        private String deviceId;
        private String value;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFieldId() {
            return fieldId;
        }

        public void setFieldId(String fieldId) {
            this.fieldId = fieldId;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Object getDict() {
        return dict;
    }

    public void setDict(Object dict) {
        this.dict = dict;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    public Integer getNullable() {
        return nullable;
    }

    public void setNullable(Integer nullable) {
        this.nullable = nullable;
    }

    public String getRegular() {
        return regular;
    }

    public void setRegular(String regular) {
        this.regular = regular;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Integer getFeatures() {
        return features;
    }

    public void setFeatures(Integer features) {
        this.features = features;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public DeviceFieldValueBean getDeviceFieldValue() {
        return deviceFieldValue;
    }

    public void setDeviceFieldValue(DeviceFieldValueBean deviceFieldValue) {
        this.deviceFieldValue = deviceFieldValue;
    }
}
