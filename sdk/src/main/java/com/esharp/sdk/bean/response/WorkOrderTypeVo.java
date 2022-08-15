package com.esharp.sdk.bean.response;

import com.esharp.sdk.dialog.ListPopWindow;

import java.io.Serializable;

public class WorkOrderTypeVo extends ListPopWindow.Item implements Serializable {
    private String typeId;
    private String typeName;

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public void setChecked(String checkedCode) {
        this.checked = this.typeId.equals(checkedCode);
    }

    @Override
    public String text() {
        return typeName;
    }
}
