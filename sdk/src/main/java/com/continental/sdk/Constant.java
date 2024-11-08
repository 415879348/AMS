package com.continental.sdk;

import com.blankj.utilcode.util.SizeUtils;

import java.util.Locale;

/**
 * 功能描述：
 *
 * @author (作者) someone
 * 创建时间： 2021/7/12
 */
public interface Constant {
    String SHARED_PREF_NAME = "smart_parking_store";
    String KEY_MODE_NIGHT = "mode_night";
    String KEY_BUNDLE_LOGORES = "key_logo_res";
    String KEY_LANGUAGE = "key_language";
    String KEY_TITLE = "key_title";
    String KEY_TINT = "key_tint";
    String KEY_BOTTOM_RES = "key_bottom_res";
    String KEY_USER= "key_user";

    String Authorization = "Authorization";
    String TOKEN_HEAD_BEARER = "Bearer ";
    String Language = "language";
    String TAG = "tag";
    String DATA = "data";

    String VERSION = "V1.2.";

    int DEFAULT_PAGE_SIZE = 10;

    //1请假，2销假，3薪资，4补签，5加班
    int APPLY_TYPE_LEAVE = 1;
    int APPLY_TYPE_LEAVE_CANCELLATION = 2;
    int APPLY_TYPE_SALARY = 3;
    int APPLY_TYPE_AMEND = 4;
    int APPLY_TYPE_OVERTIME = 5;

    //0待处理，1已通过，2已拒绝，3撤销
//    int APPLY_STATUS_APPROVING = 0;
    int APPLY_STATUS_WAITING_HANDLE = 0;
    int APPLY_STATUS_PASSED = 1;
    int APPLY_STATUS_REFUSED = 2;
    int APPLY_STATUS_CANCEL = 3;

//    100警報，101考勤
    int STATISTIC_RECORD_TYPE_WARNING = 100;
    int STATISTIC_RECORD_TYPE_ATTENDANCE = 101;

    interface Client {
        String clientId = "demoApp";
        String clientSecret = "123456";
    }


    SPLocal EN = new SPLocal("en", "English", Locale.ENGLISH);
    SPLocal TC = new SPLocal("tc", "中文繁體", new Locale("zh", "HK"));
    SPLocal SC = new SPLocal("sc", "中文简体", Locale.SIMPLIFIED_CHINESE);
    SPLocal[] language = new SPLocal[]{EN, TC, SC};

    String RESPONSE_CODE_SUCCESS = "000000";
    String RESPONSE_AUTHENTICATION_FAILED = "070003";

    String KEY_CURRENT = "current";
    String KEY_SIZE = "size";

    int SIZE = 20;
    String REFRESH_DATA = "REFRESH_DATA";

    int CROP_IMAGE_WIDE_HIGH = SizeUtils.dp2px( 240);
    int MAX_SELECT_NUM = 6;
}


