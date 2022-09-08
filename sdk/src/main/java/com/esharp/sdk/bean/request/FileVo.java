package com.esharp.sdk.bean.request;

import java.io.Serializable;

public class FileVo implements Serializable {

    /**
     * base64 : 文件
     * extension : 文件后缀
     * id : 0
     * type : 类型 // 0：资产文件 1：维修记录文件 2：维护记录文件
     */
    private String base64;
    private String extension;
    private String id;
    private Integer type;

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
