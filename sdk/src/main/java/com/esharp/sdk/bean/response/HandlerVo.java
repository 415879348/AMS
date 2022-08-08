package com.esharp.sdk.bean.response;

import java.io.Serializable;

public class HandlerVo implements Serializable {

    /**
     * id : 主鍵
     * content : 描述
     * processId : 下一輪處理人主鍵
     * isOver : 0:結束 1:指派 是否結束
     * remark : 説明
     */

    private Long id;
    private String content;
    private Long processId;
    private Integer isOver;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    public Integer getIsOver() {
        return isOver;
    }

    public void setIsOver(Integer isOver) {
        this.isOver = isOver;
    }

}
