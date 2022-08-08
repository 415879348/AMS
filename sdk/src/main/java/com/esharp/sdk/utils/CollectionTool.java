package com.esharp.sdk.utils;

import com.blankj.utilcode.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class CollectionTool {

    /**
     * 同个集合去重
     */
    public static <T> void removeDuplicateWithOrder(List<T> list) {

        List<T> tempList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            if (! tempList.contains(list.get(i))) {
                // 将未包含的元素添加进listTemp集合中
                tempList.add(list.get(i));
            }
        }

        list.clear();

        list.addAll(tempList);
    }

    /**
     * 两个集合去重
     */
    public static <T> List<T> deDuplication(List<T> list, List<T> list2) {
        LogUtils.json(list);
        LogUtils.json(list2);
        list.removeAll(list2);
        return list;
    }

}
