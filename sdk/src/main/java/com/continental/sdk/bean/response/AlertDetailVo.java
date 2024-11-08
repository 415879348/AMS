package com.continental.sdk.bean.response;

public class AlertDetailVo {

    /**
     * id : 3630
     * status : 0
     * type : 1
     * title : Abnormal Temperature Detected
     * addressee : chun.kit.chan@dksh.com,johnny.lee@dksh.com
     * details : Abnormal Temperature Detected.
     * filePath : images/a6baa2bdb9134615ac3735a5b96fb37f/record/2021-09-01/151/151_20210901170019505_FaceId1501622693986277panorama.jpg
     * userName : Adam鋒
     * departmentName : R&D
     * temperature : 37.3
     * identificationTime : 2021-09-01 17:00:19
     * device : 1217 [ DKSH-HLC ]
     * personnel : Wong Shung Lai [ HLC - DC Operations 配送中心營運 ]
     * userId : 1563
     * companyId : 1
     * departmentId : null
     * organizationId : a6baa2bdb9134615ac3735a5b96fb37f
     * createdTime : 1630486820000
     */

    private int id;
    private int status;
    private int type;
    private String title;
    private String addressee;
    private String details;
    private String filePath;
    private String userName;
    private String departmentName;
    private float temperature;
    private String identificationTime;
    private String device;
    private String personnel;
    private int userId;
    private int companyId;
    private Object departmentId;
    private String organizationId;
    private long createdTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public String getIdentificationTime() {
        return identificationTime;
    }

    public void setIdentificationTime(String identificationTime) {
        this.identificationTime = identificationTime;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getPersonnel() {
        return personnel;
    }

    public void setPersonnel(String personnel) {
        this.personnel = personnel;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public Object getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Object departmentId) {
        this.departmentId = departmentId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }
}
