package com.learning.lesson08recursion;

import com.common.utils.NumberUtil;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 迷宫回溯问题演示实例
 *
 * @author Zongheng Ma
 * @date 2020-5-13
 */
public class MazeBacktrackDemo {

    public static void main(String[] a) {
        // 用一个二维数组表示网格迷宫
        int[][] map = new int[8][7];
        mapInitial(map);
        // 随机设置挡板
        setBlock(6, map);
        System.out.println("地图已初始化");
        printMap(map);

        // 输入起点
        int x;
        int y;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("请输入起点行号：");
            x = scanner.nextInt();
            if (x <= 0 || x >= map.length - 1) {
                System.out.println("请输入正确的行号");
            } else {
                break;
            }
        }
        while (true) {
            System.out.print("请输入起点列号：");
            y = scanner.nextInt();
            if (y <= 0 || y >= map[0].length - 1) {
                System.out.println("请输入正确的列号");
            } else {
                break;
            }
        }
        scanner.close();
        setWay1(map, x, y);
        System.out.println("采用 下->右->上->左 的寻路策略，路径如下");
        printMap(map);
        System.out.printf("寻路次数为%d\n", count);
    }


    /**
     * 终点x坐标
     */
    private final static int TARGET_X = 6;

    /**
     * 终点y坐标
     */
    private final static int TARGET_Y = 5;

    /**
     * 寻路步数统计
     */
    private static int count = 0;


    /**
     * 寻路 下->右->上->左
     *
     * @param map 地图二维数组
     * @param x   起始位置的行
     * @param y   起始位置的列
     * @return 找到通路 true; 未找到 false
     */
    public static boolean setWay1(int[][] map, int x, int y) {
        /*
         * 1.约定: 0表示未走过；1表示墙，不通；2表示通路；3表示已走过但不通
         * 2.拟定寻路方法：下->右->上->左 若该点不通再回溯
         */

        // 如果已寻路至终点，则返回true
        if (x == TARGET_X && y == TARGET_Y) {
            map[x][y] = 2;
            System.out.printf("抵达终点 (%d,%d)\n", TARGET_X, TARGET_Y);
            return true;
        }

        // 若当前点未走过，则从该点开始 下->右->上->左 寻路
        if (map[x][y] == 0) {
            // 先假定通路
            count++;
            map[x][y] = 2;
            System.out.printf("寻路至 (%d,%d)\n", x, y);
            // 按策略开始递归
            if (setWay1(map, x + 1, y)) {
                // 向下寻路
                return true;
            } else if (setWay1(map, x, y + 1)) {
                // 向右寻路
                return true;
            } else if (setWay1(map, x - 1, y)) {
                // 向上寻路
                return true;
            } else if (setWay1(map, x, y - 1)) {
                // 向左寻路
                return true;
            } else {
                // 路不通
                map[x][y] = 3;
                System.out.printf("(%d,%d) 不通\n", x, y);
                return false;
            }
        }
        // 为1、2、3的情况
        return false;
    }


    /**
     * 寻路 上->右->下->左
     *
     * @param map 地图二维数组
     * @param x   起始位置的行
     * @param y   起始位置的列
     * @return 找到通路 true; 未找到 false
     */
    public static boolean setWay2(int[][] map, int x, int y) {
        /*
           1.约定: 0表示未走过；1表示墙，不通；
                   2表示通路；3表示已走过但不通
           2.拟定寻路方法：上->右->下->左 若该点不通再回溯
         */

        // 如果已寻路至终点，则返回true
        if (x == TARGET_X && y == TARGET_Y) {
            map[x][y] = 2;
            System.out.printf("抵达终点 (%d,%d)\n", TARGET_X, TARGET_Y);
            return true;
        }

        // 若当前点未走过，则从该点开始 上->右->下->左 寻路
        if (map[x][y] == 0) {
            // 先假定通路
            count++;
            map[x][y] = 2;
            System.out.printf("寻路至 (%d,%d)\n", x, y);
            // 按策略开始递归
            if (setWay2(map, x - 1, y)) {
                // 向上寻路
                return true;
            } else if (setWay2(map, x, y + 1)) {
                // 向右寻路
                return true;
            } else if (setWay2(map, x + 1, y)) {
                // 向下寻路
                return true;
            } else if (setWay2(map, x, y - 1)) {
                // 向左寻路
                return true;
            } else {
                // 路不通
                map[x][y] = 3;
                System.out.printf("(%d,%d) 不通\n", x, y);
                return false;
            }
        }
        // 为1、2、3的情况
        return false;
    }


    /**
     * 地图初始化
     *
     * @param map 地图数组
     */
    public static void mapInitial(int[][] map) {
        // 元素全部置0
        for (int[] row : map) {
            Arrays.fill(row, 0);
        }
        // 地图的左右边界
        for (int[] row : map) {
            // 第一列和最后一列置1
            row[0] = 1;
            row[row.length - 1] = 1;
        }
        // 地图的上下边界
        for (int i = 1; i < map[0].length - 1; i++) {
            map[0][i] = 1;
            map[map.length - 1][i] = 1;
        }
    }


    /**
     * 随机设置n个挡板
     *
     * @param n   挡板个数
     * @param map 地图数组
     */
    public static void setBlock(int n, int[][] map) {
        for (int i = 1; i <= n; i++) {
            int x = NumberUtil.randomGenerate(1, map.length - 2);
            int y = NumberUtil.randomGenerate(2, map[0].length - 2);
            // 若坐标为终点或者已经是墙则重新生成
            while (map[x][y] == 1 || (x == TARGET_X && y == TARGET_Y)) {
                x = NumberUtil.randomGenerate(1, map.length - 2);
                y = NumberUtil.randomGenerate(2, map[0].length - 2);
            }
            map[x][y] = 1;
        }
    }


    /**
     * 打印地图
     *
     * @param map 数组地图
     */
    public static void printMap(int[][] map) {
        for (int[] row : map) {
            for (int i = 0; i < row.length; i++) {
                System.out.print(mapString(row[i]) + (i == row.length - 1 ? "\n" : " "));
            }
        }
    }


    /**
     * 地图标记
     *
     * @param flag
     * @return 地图符号
     */
    public static String mapString(int flag) {
        switch (flag) {
            // 1 墙，不通
            case 1:
                return "■";
            // 2 已寻路，通路
            case 2:
                return "○";
            // 3 已寻路，不通
            case 3:
                return "△";
            // 0 未走过
            default:
                return "□";
        }
    }
}
