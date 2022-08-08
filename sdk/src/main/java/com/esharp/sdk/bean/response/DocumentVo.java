package com.esharp.sdk.bean.response;

import java.io.Serializable;

public class DocumentVo implements Serializable {

    /**
     * base64 : 文件BASE64
     * extension : 文件後綴
     * type : 類型 0:資產表文件 1:維修記錄文件 2:保養文件 3:使用文件 4:大華設備文件
     */

    private String base64;
    private String extension;
    private Integer type;

    public DocumentVo(String base64, String extension, Integer type) {
        this.base64 = base64;
        this.extension = extension;
        this.type = type;
    }
}
