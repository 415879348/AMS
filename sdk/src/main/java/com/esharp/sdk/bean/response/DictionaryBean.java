package com.esharp.sdk.bean.response;

import com.esharp.sdk.dialog.ListPopWindow;

import java.io.Serializable;

public class DictionaryBean extends ListPopWindow.Item implements Serializable {

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
    private String id = "";
    private String companyId;
    private String dictName;
    private String dictType;
    private String features;
    private String active;
    private String sort;
    private String parentId;
    private String companyName;
    private String parentDictName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getParentDictName() {
        return parentDictName;
    }

    public void setParentDictName(String parentDictName) {
        this.parentDictName = parentDictName;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    @Override
    public void setChecked(String checkedCode) {
        this.checked = this.id.equals(checkedCode);
    }

    @Override
    public String text() {
        return dictName;
    }
}
