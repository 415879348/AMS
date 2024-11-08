package com.continental.sdk.bean.request;

import java.io.Serializable;

public class CurrentSizeVo implements Serializable {

    public CurrentSizeVo(String current, String size) {
        this.current = current;
        this.size = size;
    }

    /**
     * current :
     * size :
     */
    private String current;
    private String size;
}
