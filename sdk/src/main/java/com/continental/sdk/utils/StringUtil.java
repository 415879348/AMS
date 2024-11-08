package com.continental.sdk.utils;

import java.net.URLDecoder;

/**
 * 功能描述：
 *
 * @author (作者) someone
 * 创建时间： 2021/7/12
 */
public final class StringUtil {

    public static String replaceNull(String text) {
        return text == null ? "" : text;
    }

    public static String decodeString(String source) {
        return decodeString(source, "utf-8");
    }

    public static String decodeString(String source , String enc) {
        try {
            return URLDecoder.decode(source, enc);
        } catch (Exception e) {
            return source;
        }
    }

}
