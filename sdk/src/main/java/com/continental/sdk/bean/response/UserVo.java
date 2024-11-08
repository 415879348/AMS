package com.continental.sdk.bean.response;

import com.continental.sdk.dialog.ListPopWindow;

import java.io.Serializable;
import java.util.List;

public class UserVo extends ListPopWindow.Item implements Serializable {

    /**
     * id : 主鍵
     * companyId : 公司ID
     * username : 用戶名
     * userNo : 賬號
     * phone : 電話號碼
     * email : 郵箱
     * image : 圖片地址
     * manage : 0:普通 1:管理員
     * active : 0:正常 1:刪除
     * createId : 創建人
     * createTime : 創建時間
     * updateId : 修改人
     * updateTime : 修改時間
     */
    private String id;
    private String companyId;
    private String username;
    private String userNo;
    private String phone;
    private String email;
    private String image;
    private int manage;
    private int active;
    private String createId;
    private String createTime;
    private String updateId;
    private String updateTime;
    private List<String> companyIds;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getManage() {
        return manage;
    }

    public void setManage(int manage) {
        this.manage = manage;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<String> getCompanyIds() {
        return companyIds;
    }

    public void setCompanyIds(List<String> companyIds) {
        this.companyIds = companyIds;
    }

    @Override
    public void setChecked(String checkedCode) {
        this.checked = this.id.equals(checkedCode);
    }

    @Override
    public String text() {
        return username;
    }
}
