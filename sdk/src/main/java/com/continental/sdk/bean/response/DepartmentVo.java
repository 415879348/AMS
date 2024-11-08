package com.continental.sdk.bean.response;

import com.continental.sdk.dialog.ListPopWindow;

import java.util.List;

public class DepartmentVo extends ListPopWindow.Item {

    /**
     * departmentId : 144
     * departmentName : 廣州開發部
     * userBaseList : [{"userId":1643,"userName":"adamhuang"}]
     */

    private long departmentId;
    private String departmentName;
    private List<UserBaseBean> userBaseList;

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<UserBaseBean> getUserBaseList() {
        return userBaseList;
    }

    public void setUserBaseList(List<UserBaseBean> userBaseList) {
        this.userBaseList = userBaseList;
    }

    @Override
    public void setChecked(String checkedCode) {
        this.checked = (this.departmentId + "").equals(checkedCode);
    }

    @Override
    public String text() {
        return departmentName;
    }

    public static class UserBaseBean extends ListPopWindow.Item {
        /**
         * userId : 1643
         * userName : adamhuang
         */

        private long userId;
        private String userName;

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        @Override
        public void setChecked(String checkedCode) {
            this.checked = (this.userId + "").equals(checkedCode);
        }

        @Override
        public String text() {
            return userName;
        }
    }

}
