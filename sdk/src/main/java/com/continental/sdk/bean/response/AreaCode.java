package com.continental.sdk.bean.response;

import com.continental.sdk.dialog.ListPopWindow;

/**
 * 功能描述：
 *
 * @author (作者) someone
 * 创建时间： 2021/9/7
 */
public class AreaCode extends ListPopWindow.Item {
    private int id;
    private String name;
    private String code;
    private String reg;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }

    @Override
    public void setChecked(String checkedCode) {
        this.checked = this.code.equals(checkedCode);
    }

    @Override
    public String text() {
        return String.format("%s\n+%s", this.name, this.code);
    }
}
