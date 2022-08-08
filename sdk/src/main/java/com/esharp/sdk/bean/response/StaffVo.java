package com.esharp.sdk.bean.response;

import java.io.Serializable;
import java.util.Objects;

public class StaffVo implements Serializable {

    /**
     * userId : 1562
     * departmentName : 開發部
     * username : feng
     * imageUrl : images/a6baa2bdb9134615ac3735a5b96fb37f/user/2021-09-23/1632378751458.jpg
     */

    private long userId;
    private String departmentName;
    private String username;
    private String imageUrl;
    private boolean check = false;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StaffVo staffVo = (StaffVo) o;
        return getUserId() == staffVo.getUserId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId());
    }
}
