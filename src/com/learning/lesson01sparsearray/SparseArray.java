package com.learning.lesson01sparsearray;

import java.io.IOException;

/**
 * 稀疏数组的创建、转存与读取、还原
 *
 * @author Zongheng Ma
 * @date 2020-4-24
 */
public class SparseArray {

    public static void main(String[] args) throws IOException {
        // 创建原始数组
        int[][] originalArr = new int[11][11];
        // 初始化元素
        originalArr[1][2] = 1;
        originalArr[2][3] = 2;
        originalArr[3][4] = 3;
        // 记录有效元素个数
        int count = 0;
        for (int[] row : originalArr) {
            for (int item : row) {
                if (item != 0) {
                    count++;
                }
            }
        }

        // 创建稀疏数组
        int[][] sparseArr = new int[count + 1][3];
        // 第0行元素记录原始数组的行数、列数、有效元素个数
        sparseArr[0] = new int[]{originalArr.length, originalArr[0].length, count};
        int rowNum = 1;
        for (int i = 0; i < originalArr.length; i++) {
            for (int j = 0; j < originalArr[0].length; j++) {
                if (originalArr[i][j] != 0) {
                    sparseArr[rowNum][0] = i;
                    sparseArr[rowNum][1] = j;
                    sparseArr[rowNum][2] = originalArr[i][j];
                    rowNum++;
                }
            }
        }

        // 转存稀疏数组
        ArrayIo.arraySave(sparseArr);

        // 读取稀疏数组
        int[][] arrayRead = ArrayIo.arrayRead();
        System.out.println("读取的稀疏数组：");
        ArrayIo.arrayPrint(arrayRead);

        // 根据稀疏数组第一行创建还原数组
        int[][] reductionArr = new int[arrayRead[0][0]][arrayRead[0][1]];
        for (int i = 1; i < arrayRead.length; i++) {
            reductionArr[arrayRead[i][0]][arrayRead[i][1]] = arrayRead[i][2];
        }
        System.out.println("还原数组：");
        ArrayIo.arrayPrint(reductionArr);
    }
}