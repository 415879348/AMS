package com.esharp.sdk.http;

import com.blankj.utilcode.util.LogUtils;
import com.esharp.sdk.BuildConfig;

/**
 * 功能描述：
 *
 * @author (作者) someone
 * 创建时间： 2021/8/11
 */
public interface IHttpURL {
    String TEST_BASE_URL = "http://182.92.123.13:8067/ams/app/";
    String TEST_IMAGE_URL = "http://182.92.123.13:8067/ams/";
    String TEST_LOGIN_URL = "http://182.92.123.13:8061/server/auth/";

    String RELEASE_BASE_URL = "https://api.kahetech.com/ams/app/";
//    String RELEASE_BASE_URL = "http://192.168.10.27:9999/ams/app/";
    String RELEASE_IMAGE_URL = "https://api.kahetech.com/ams/";
    String LOGIN_URL = TEST_LOGIN_URL;

//    String BASE_URL = TEST_BASE_URL;
//    String IMAGE_URL = TEST_IMAGE_URL;
    String BASE_URL = RELEASE_BASE_URL;
    String IMAGE_URL = RELEASE_IMAGE_URL;


    static String getImageUrl(String subUrl) {
        LogUtils.i(IMAGE_URL + subUrl);
        return IMAGE_URL + subUrl;
    }

    static String getQrCodeUrl(long carId) {
        return BASE_URL + "user/garage/activated/qrcode/" + carId + '/';
    }

    //獲取條款説明（只獲取啓用一條記錄）
    static String getTermsConditions() {
        return BASE_URL + "terms/search/enabled/";
    }

    static String getPayReceipt(long recordId) {
        return BASE_URL + "receipt/show/payreceipt/" + recordId;
    }

    static String getPayReceiptImage(long recordId) {
        return BASE_URL + "receipt/download/img/" + recordId;
    }
}
