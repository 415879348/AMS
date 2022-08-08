package com.esharp.sdk.bean.request;

public class ChangePasswordVo {

    /**
     * password : 12345678
     * newPassword : 12345678
     * confirmPassword : 12345678
     */

    private String password;
    private String newPassword;
    private String confirmPassword;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
