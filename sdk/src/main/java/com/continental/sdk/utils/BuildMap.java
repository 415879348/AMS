package com.continental.sdk.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述：Map链式添加工具类
 *
 * @author (作者) someone
 * 创建时间： 2018/5/30 0030
 */
public class BuildMap<K, V> {
    private final Map<K, V> map;

    public static <K, V> BuildMap<K, V> create() {
        return new BuildMap<>();
    }

    public static BuildMap<String, String> init() {
        return create();
    }

    private BuildMap() {
        map = new HashMap<>();
    }

    public BuildMap<K, V> put(K k, V v) {
        this.map.put(k, v);
        return this;
    }

    public BuildMap<K, V> putAll(Map<K, V> map) {
        this.map.putAll(map);
        return this;
    }

    public Map<K, V> build() {
        return map;
    }
}
