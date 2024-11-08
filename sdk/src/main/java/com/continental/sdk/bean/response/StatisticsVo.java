package com.continental.sdk.bean.response;

public class StatisticsVo {

    /**
     *   "recordType": 100, //100警報，101考勤
     *   "count": 0, //次数
     *   "extra": null //额外属性（类型3的请假总时长，类型4的加班总时长）
     */

    private int recordType;
    private int count;
    private Object extra;

    public int getRecordType() {
        return recordType;
    }

    public void setRecordType(int recordType) {
        this.recordType = recordType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Object getExtra() {
        return extra;
    }

    public void setExtra(Object extra) {
        this.extra = extra;
    }
}
