package com.continental.sdk;

import java.io.Serializable;

/**
 * 功能描述：
 *
 * @author (作者) fengchuanfang
 * 创建时间： 2021/12/21
 */
public class SPParams implements Serializable {

    private String clientId, clientSecret,memberId, phoneNumber, mallId;

    public SPParams(String clientId, String clientSecret, String memberId, String phoneNumber, String mallId) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.memberId = memberId;
        this.phoneNumber = phoneNumber;
        this.mallId = mallId;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMallId() {
        return mallId;
    }
}
