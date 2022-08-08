package com.esharp.sdk.bean.response;

import java.io.Serializable;
import java.util.List;

public class DeviceBean implements Serializable {

    /**
     * active : 0
     * address :
     * addressId : 0
     * brand :
     * brandId : 0
     * brandModel :
     * brandModelId : 0
     * children : [{"active":0,"address":"","addressId":0,"brand":"","brandId":0,"brandModel":"","brandModelId":0,"children":[{}],"color":"","companyId":"","companyName":"","createId":0,"createTime":"","deviceName":"","deviceNumber":"","deviceType":"","deviceTypeId":0,"errorMessage":"","features":0,"height":0,"hostCode":"","id":0,"lat":0,"length":0,"lon":0,"maintain":0,"parentId":0,"parentName":"","personId":0,"production":"","productionDate":"","remark":"","status":0,"updateId":0,"updateTime":"","urls":[{"createTime":"","extension":"","id":0,"joinId":0,"type":0,"url":""}],"warrantyDate":"","weight":0,"width":0}]
     * color :
     * companyId :
     * companyName :
     * createId : 0
     * createTime :
     * deviceName :
     * deviceNumber :
     * deviceType :
     * deviceTypeId : 0
     * errorMessage :
     * features : 0
     * height : 0
     * hostCode :
     * id : 0
     * lat : 0
     * length : 0
     * lon : 0
     * maintain : 0
     * parentId : 0
     * parentName :
     * personId : 0
     * production :
     * productionDate :
     * remark :
     * status : 裝置狀態 0:正常 1:準備中 2:異常 3:初始化失敗，需要手動操作
     * updateId : 0
     * updateTime :
     * urls : [{"createTime":"","extension":"","id":0,"joinId":0,"type":0,"url":""}]
     * warrantyDate :
     * weight : 0
     * width : 0
     */

    private Integer active;
    private String address;
    private Integer addressId;
    private String brand;
    private Integer brandId;
    private String brandModel;
    private Integer brandModelId;
    private String color;
    private String companyId;
    private String companyName;
    private Integer createId;
    private Long createTime;
    private String deviceName;
    private String deviceNumber;
    private String deviceType;
    private Integer deviceTypeId;
    private String errorMessage;
    private Integer features;
    private int height;
    private String hostCode;
    private Integer id;
    private Integer lat;
    private int length;
    private Integer lon;
    private Integer maintain;
    private Integer parentId;
    private String parentName;
    private Integer personId;
    private String production;
    private String productionDate;
    private String remark;
    private Integer status;
    private Integer updateId;
    private Long updateTime;
    private String warrantyDate;
    private Integer weight;
    private int width;
    private List<DeviceBean> children;
    private List<UrlsBean> urls;

    public static class UrlsBean implements Serializable {
        /**
         * createTime :
         * extension :
         * id : 0
         * joinId : 0
         * type : 0
         * url :
         */
        private String createTime;
        private String extension;
        private Integer id;
        private Integer joinId;
        private Integer type;
        private String url;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getExtension() {
            return extension;
        }

        public void setExtension(String extension) {
            this.extension = extension;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getJoinId() {
            return joinId;
        }

        public void setJoinId(Integer joinId) {
            this.joinId = joinId;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandModel() {
        return brandModel;
    }

    public void setBrandModel(String brandModel) {
        this.brandModel = brandModel;
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

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
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

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public Integer getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setDeviceTypeId(Integer deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Integer getFeatures() {
        return features;
    }

    public void setFeatures(Integer features) {
        this.features = features;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getHostCode() {
        return hostCode;
    }

    public void setHostCode(String hostCode) {
        this.hostCode = hostCode;
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

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
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

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getWarrantyDate() {
        return warrantyDate;
    }

    public void setWarrantyDate(String warrantyDate) {
        this.warrantyDate = warrantyDate;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public List<DeviceBean> getChildren() {
        return children;
    }

    public void setChildren(List<DeviceBean> children) {
        this.children = children;
    }

    public List<UrlsBean> getUrls() {
        return urls;
    }

    public void setUrls(List<UrlsBean> urls) {
        this.urls = urls;
    }
}
