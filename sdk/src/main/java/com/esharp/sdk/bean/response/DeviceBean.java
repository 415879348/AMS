package com.esharp.sdk.bean.response;

import com.esharp.sdk.dialog.ListPopWindow;

import java.io.Serializable;
import java.util.List;

public class DeviceBean extends ListPopWindow.Item implements Serializable {

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
    private boolean isCheck;
    private String address;
    private Integer addressId;
    private String brand;
    private String brandId;
    private String brandModel;
    private String brandModelId;
    private String color;
    private String companyId;
    private String companyName;
    private Integer createId;
    private Long createTime;
    private String deviceName;
    private String deviceNumber;
    private String deviceType;
    private String deviceTypeId;
    private String errorMessage;
    private Integer features;
    private String height;
    private String hostCode;
    private String id;
    private Integer lat;
    private String length;
    private Integer lon;
    private Integer maintain;
    private String parentId;
    private String parentName;
    private Integer personId;
    private String production;
    private Long productionDate;
    private String remark;
    private String location;
    private Integer status;
    private Integer updateId;
    private Long updateTime;
    private Long warrantyDate;
    private String weight;
    private String width;
    private List<DeviceBean> children;
    private List<UrlsBean> urls;
    private List<String> documentIds;

    @Override
    public void setChecked(String checkedCode) {
        this.checked = this.id.equals(checkedCode);
    }

    @Override
    public String text() {
        return deviceName;
    }

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
        private String id;
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

        public String getId() {
            return id;
        }

        public void setId(String id) {
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

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrandModel() {
        return brandModel;
    }

    public void setBrandModel(String brandModel) {
        this.brandModel = brandModel;
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

    public String getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setDeviceTypeId(String deviceTypeId) {
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

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getHostCode() {
        return hostCode;
    }

    public void setHostCode(String hostCode) {
        this.hostCode = hostCode;
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

    public Long getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Long productionDate) {
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

    public Long getWarrantyDate() {
        return warrantyDate;
    }

    public void setWarrantyDate(Long warrantyDate) {
        this.warrantyDate = warrantyDate;
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

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean checked) {
        isCheck = checked;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getDocumentIds() {
        return documentIds;
    }

    public void setDocumentIds(List<String> documentIds) {
        this.documentIds = documentIds;
    }
}
