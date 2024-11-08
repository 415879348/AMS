package com.continental.sdk.bean.response;

import com.continental.sdk.dialog.ListPopWindow;

public class LeaveTypeVo extends ListPopWindow.Item {

    /**
     * id : 11
     * name : 年假
     * flag : 1
     * organizationId : a6baa2bdb9134615ac3735a5b96fb37f
     */

    private int id;
    private String name;
    private int flag;
    private String organizationId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    @Override
    public void setChecked(String checkedCode) {
        this.checked = (this.id + "").equals(checkedCode);
    }

    @Override
    public String text() {
        return name;
    }
}
