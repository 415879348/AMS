package com.continental.sdk.bean.response;

import java.util.List;

public class AssetAlertVo {

    private List<AssetAlertBean> records;
    private Integer total;
    private Integer size;
    private Integer current;
    private List<OrdersBean> orders;
    private Boolean hitCount;
    private Boolean searchCount;
    private Integer pages;

    public static class OrdersBean {
        private String column;
        private Boolean asc;

        public String getColumn() {
            return column;
        }

        public void setColumn(String column) {
            this.column = column;
        }

        public Boolean getAsc() {
            return asc;
        }

        public void setAsc(Boolean asc) {
            this.asc = asc;
        }
    }

    public List<AssetAlertBean> getRecords() {
        return records;
    }

    public void setRecords(List<AssetAlertBean> records) {
        this.records = records;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public List<OrdersBean> getOrders() {
        return orders;
    }

    public void setOrders(List<OrdersBean> orders) {
        this.orders = orders;
    }

    public Boolean getHitCount() {
        return hitCount;
    }

    public void setHitCount(Boolean hitCount) {
        this.hitCount = hitCount;
    }

    public Boolean getSearchCount() {
        return searchCount;
    }

    public void setSearchCount(Boolean searchCount) {
        this.searchCount = searchCount;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }
}
