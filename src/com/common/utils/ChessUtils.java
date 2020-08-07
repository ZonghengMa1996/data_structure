package com.common.utils;

/**
 * 国象坐标转换
 *
 * @author Zongheng Ma
 * @date 20020-5-14
 */
public class ChessUtils {

    /**
     * 8皇后问题的结果数组转换为国象坐标
     *
     * @param res 结果数组
     * @return
     */
    public static String toCoordinate(int[] res) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            /*
             * 0-7对应a-h
             */
            str.append((char) ('a' + i)).append(res[i] + 1).append(i + 1 == res.length ? "" : " ");
        }
        return str.toString();
    }
}