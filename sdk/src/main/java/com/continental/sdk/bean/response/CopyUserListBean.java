package com.continental.sdk.bean.response;

import java.io.Serializable;

public class CopyUserListBean implements Serializable {
    /**
     * copyUserId : 1736
     * copyUserName : 陳福強
     * headPortrait : images/101fddaa51514b83a8815ae89141c8ee/user/2021-11-17/1637130242555.jpg
     */

    private Long copyUserId;
    private Long status;
    private String copyUserName;
    private String headPortrait;

    public Long getCopyUserId() {
        return copyUserId;
    }

    public void setCopyUserId(Long copyUserId) {
        this.copyUserId = copyUserId;
    }

    public String getCopyUserName() {
        return copyUserName;
    }

    public void setCopyUserName(String copyUserName) {
        this.copyUserName = copyUserName;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }
}