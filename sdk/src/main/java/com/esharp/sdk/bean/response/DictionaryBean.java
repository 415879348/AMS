package com.esharp.sdk.bean.response;

import java.io.Serializable;

public class DictionaryBean implements Serializable {

    /**
     * id : 主键
     * companyId : 公司ID
     * dictName : 字典名
     * dictType : 類型 0:品牌 1:品牌型號 2:資產類型 3:所在位置
     * active : 0:正常 1:刪除
     * sort : 排序
     * parentId : 上级ID
     * companyName : 公司名
     * parentDictName : 上级字典名
     */
    private String id;
    private String companyId;
    private String dictName;
    private String dictType;
    private String active;
    private String sort;
    private String parentId;
    private String companyName;
    private String parentDictName;
}
