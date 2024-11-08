package com.continental.sdk.bean.response;


import java.io.Serializable;
import java.util.List;

/**
 * 資產提交
 */
public class DeviceInfoForm implements Serializable {

    /**
     * addressId : 0
     * brandId : 0
     * brandModelId : 0
     * childIds : []
     * color :
     * deviceFieldValueForms : [{"code":"","deviceId":0,"fieldId":0,"id":0,"value":""}]
     * deviceName :
     * deviceNumber :
     * deviceTypeId : 0
     * documentIds : []
     * features : 0
     * height : 0
     * id : 0
     * lat : 0
     * length : 0
     * location : ""
     * lon : 0
     * maintain : 0
     * parentId : 0
     * personId : 0
     * prodDate : 0
     * production :
     * remark :
     * warranty : 0
     * weight : 0
     * width : 0
     */

    private Integer addressId;
    private String brandId;
    private String brandModelId;
    private String color;
    private String deviceName;
    private String deviceNumber;
    private String deviceTypeId;
    private Integer features;
    private String height;
    private String id;
    private Integer lat;
    private String length;
    private String location;
    private Integer lon;
    private Integer maintain;
    private String parentId;
    private Integer personId;
    private Long prodDate;
    private String production;
    private String remark;
    private Long warranty;
    private String weight;
    private String width;
    private List<?> childIds;
    private List<DeviceFieldValueBean> deviceFieldValueForms;
    private List<String> documentIds;


    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrandModelId() {
        return brandModelId;
    }

    public void setBrandModelId(String brandModelId) {
        this.brandModelId = brandModelId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public String getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setDeviceTypeId(String deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }

    public Integer getFeatures() {
        return features;
    }

    public void setFeatures(Integer features) {
        this.features = features;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getLat() {
        return lat;
    }

    public void setLat(Integer lat) {
        this.lat = lat;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public Integer getLon() {
        return lon;
    }

    public void setLon(Integer lon) {
        this.lon = lon;
    }

    public Integer getMaintain() {
        return maintain;
    }

    public void setMaintain(Integer maintain) {
        this.maintain = maintain;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Long getProdDate() {
        return prodDate;
    }

    public void setProdDate(Long prodDate) {
        this.prodDate = prodDate;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getWarranty() {
        return warranty;
    }

    public void setWarranty(Long warranty) {
        this.warranty = warranty;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public List<?> getChildIds() {
        return childIds;
    }

    public void setChildIds(List<?> childIds) {
        this.childIds = childIds;
    }

    public List<DeviceFieldValueBean> getDeviceFieldValueForms() {
        return deviceFieldValueForms;
    }

    public void setDeviceFieldValueForms(List<DeviceFieldValueBean> deviceFieldValueForms) {
        this.deviceFieldValueForms = deviceFieldValueForms;
    }

    public List<String> getDocumentIds() {
        return documentIds;
    }

    public void setDocumentIds(List<String> documentIds) {
        this.documentIds = documentIds;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
