/*
 * <问题描述>
 *
 * 在一张8*8的国际象棋棋盘上依次摆放8个“皇后”，要求各个“皇后“之间不能相互攻击。
 * 求：共有多少种放置方法？
 */
package com.learning.lesson08recursion;

import com.common.utils.ChessUtils;

/**
 * 八皇后问题演示实例
 *
 * @author Zongheng Ma
 * @date 2020-5-14
 */
public class EightQueensDemo {

    public static void main(String[] a) {
        EightQueensDemo demo = new EightQueensDemo();
        demo.putQueen(0);
        System.out.printf("共%d种解法，一共判断了%d次冲突\n", demo.count, demo.judgeCount);
    }

    /**
     * 可放置皇后的个数
     */
    private final int MAX = 8;

    /**
     * 记录解的个数
     */
    public int count = 0;

    /**
     * 记录检测冲突的次数
     */
    public int judgeCount = 0;

    /**
     * 存放位置结果的一维数组
     */
    private final int[] resArray = new int[MAX];


    /**
     * 放置皇后
     *
     * @param n 第n个皇后（0-7）
     */
    private void putQueen(int n) {
        if (n == MAX) {
            System.out.printf("第%d种解法：", ++count);
            showResBoard(resArray);
        } else {
            /*
             * 每一次递归中都有一个for循环，回溯时会返回到上一个皇后的for循环中
             * 无论之后是否找到，都会回溯并向后一列尝试摆放
             */
            // 从第1（即数组下标0）列开始摆放
            for (int i = 0; i < MAX; i++) {
                resArray[n] = i;
                if (!isConflict(n)) {
                    // 若不冲突则取尝试放置下一个皇后
                    putQueen(n + 1);
                }
                //如果有冲突，尝试将当前皇后向后移一列，重新检查冲突
            }
        }
    }


    /**
     * 检查放置的第n个皇后与前面的是否冲突
     *
     * @param n 第n个皇后（0-7）
     * @return 有冲突 true; 无冲突 false
     */
    private boolean isConflict(int n) {
        // 检测冲突统计量自增
        judgeCount++;
        for (int i = 0; i < n; i++) {
            // 第n个皇后放在第n行，无需判断是否同行
            if (resArray[n] == resArray[i]) {
                // 是否同列
                return true;
            }
            if (Math.abs(resArray[n] - resArray[i]) == Math.abs(n - i)) {
                // 是否在同一斜线上，应使用Math.abs()比较绝对值
                return true;
            }
        }
        return false;
    }


    /**
     * 展示摆放结果
     */
    private void showResBoard(int[] array) {
        // 打印结果数组
        System.out.println(ChessUtils.toCoordinate(array));
        // 表示棋盘的二维数组
        int[][] board = new int[8][8];
        // 标明摆放位置
        for (int i = 0; i < array.length; i++) {
            board[i][array[i]] = 1;
        }
        // 打印棋盘
        for (int[] row : board) {
            for (int i = 0; i < row.length; i++) {
                System.out.print((row[i] == 1 ? "●" : "□") + (i + 1 == row.length ? "\n" : " "));
            }
        }
        System.out.println("------------------------------------");
    }

}