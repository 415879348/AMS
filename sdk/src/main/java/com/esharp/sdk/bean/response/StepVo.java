package com.esharp.sdk.bean.response;

import com.esharp.sdk.dialog.ListPopWindow;

import java.io.Serializable;

public class StepVo extends ListPopWindow.Item implements Serializable {
    /**
     *  步骤 -1：撤销(暂无) 0：结束 1：申请  2：审批(暂无) 3：处理
     */
    private String id;
    private String progressName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProgressName() {
        return progressName;
    }

    public void setProgressName(String progressName) {
        this.progressName = progressName;
    }

    @Override
    public void setChecked(String checkedCode) {
        this.checked = this.id.equals(checkedCode);
    }

    @Override
    public String text() {
        return progressName;
    }
}
