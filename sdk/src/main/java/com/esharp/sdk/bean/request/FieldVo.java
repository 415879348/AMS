package com.esharp.sdk.bean.request;

import java.io.Serializable;

public class FieldVo implements Serializable {

    /**
     * id :
     * code :
     * type : 类型 0:整數 1:小數 2:字符串 3:日期
     * length : 长度
     * place : 小數位
     * nullable : 0:可空 1:非空
     * regular : 正則表達式
     * active : 0:正常 1:刪除
     * features : 0:其他 1:雪柜 2:跌倒CAM
     * dict : 下拉選擇字典類型 type為5時才會有數據
     */

    private String id;
    private String code;
    private String type;
    private String length;
    private String place;
    private String nullable;
    private String regular;
    private String active;
    private String features;
    private String dict;
}
