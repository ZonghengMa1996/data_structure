package com.common.utils;

/**
 * 对象工具类
 *
 * @author Zongheng Ma
 * @date 2020/8/5
 */
public class ObjectUtil {

    /**
     * 对象类型强制转换
     *
     * @param obj
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T castObject(Object obj) {
        return (T) obj;
    }
}
