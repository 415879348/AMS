package com.esharp.sdk.bean.response;


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
    private Integer brandId;
    private Integer brandModelId;
    private String color;
    private String deviceName;
    private String deviceNumber;
    private Integer deviceTypeId;
    private Integer features;
    private Integer height;
    private Integer id;
    private Integer lat;
    private Integer length;
    private Integer lon;
    private Integer maintain;
    private Integer parentId;
    private Integer personId;
    private Integer prodDate;
    private String production;
    private String remark;
    private Integer warranty;
    private Integer weight;
    private Integer width;
    private List<?> childIds;
    private List<DeviceFieldValueBean> deviceFieldValueForms;
    private List<Long> documentIds;


    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getBrandModelId() {
        return brandModelId;
    }

    public void setBrandModelId(Integer brandModelId) {
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

    public Integer getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setDeviceTypeId(Integer deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }

    public Integer getFeatures() {
        return features;
    }

    public void setFeatures(Integer features) {
        this.features = features;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLat() {
        return lat;
    }

    public void setLat(Integer lat) {
        this.lat = lat;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Integer getProdDate() {
        return prodDate;
    }

    public void setProdDate(Integer prodDate) {
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

    public Integer getWarranty() {
        return warranty;
    }

    public void setWarranty(Integer warranty) {
        this.warranty = warranty;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
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

    public List<Long> getDocumentIds() {
        return documentIds;
    }

    public void setDocumentIds(List<Long> documentIds) {
        this.documentIds = documentIds;
    }
}
