package com.learning.lesson01sparsearray;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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

        // 转为稀疏数组
        int[][] sparseArr = arrToSparseArr(originalArr);

        // 指定转存路径
        String path = "./resource/sparseArray.md";
        // 转存稀疏数组
        arraySave(sparseArr, path);
        // 读取稀疏数组
        int[][] arrayRead = arrayRead(path);
        System.out.println("读取的稀疏数组：");
        arrayPrint(arrayRead);

        // 根据稀疏数组第一行创建还原数组
        int[][] reductionArr = new int[arrayRead[0][0]][arrayRead[0][1]];
        for (int i = 1; i < arrayRead.length; i++) {
            reductionArr[arrayRead[i][0]][arrayRead[i][1]] = arrayRead[i][2];
        }
        System.out.println("还原数组：");
        arrayPrint(reductionArr);
    }


    /**
     * 数组转稀疏数组
     *
     * @param array 原数组
     * @return
     */
    public static int[][] arrToSparseArr(int[][] array) {
        // 记录有效元素个数
        int count = 0;
        for (int[] row : array) {
            for (int item : row) {
                if (item != 0) {
                    count++;
                }
            }
        }

        // 创建稀疏数组
        int[][] sparseArr = new int[count + 1][3];
        // 第0行元素记录原始数组的行数、列数、有效元素个数
        sparseArr[0] = new int[]{array.length, array[0].length, count};
        if (count > 0) {
            int rowNum = 1;
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[0].length; j++) {
                    if (array[i][j] != 0) {
                        // 第一列记录行号
                        sparseArr[rowNum][0] = i;
                        // 第二列记录列号
                        sparseArr[rowNum][1] = j;
                        // 第三列记录元素值
                        sparseArr[rowNum][2] = array[i][j];
                        rowNum++;
                    }
                }
            }
        }
        return sparseArr;
    }


    /**
     * 数组转存为文本
     *
     * @param array 待转存数组
     * @param path  转存的路径
     */
    public static void arraySave(int[][] array, String path) {
        try {
            File file = new File(path);
            // 若文件夹不存在
            File fileParent = file.getParentFile();
            if (!fileParent.exists()) {
                System.out.println(fileParent.mkdirs() ? "文件夹已创建" : "文件夹创建失败");
            }
            // 若文件不存在则进行创建
            if (!file.exists()) {
                System.out.println(file.createNewFile() ? "文件已创建" : "文件创建失败");
            }
            // 创建输出流（第二个参数append默认为false，即对已存在的文件不续写而是直接覆盖；若为true则是续写文件）
            FileWriter out = new FileWriter(file);
            // 遍历数组并输出
            for (int[] row : array) {
                for (int i = 0; i < row.length; i++) {
                    out.write(row[i] + (i == row.length - 1 ? "\n" : "\t"));
                }
            }
            // 关闭输出流
            out.close();
            System.out.println("数组已转存!");
        } catch (IOException e) {
            System.out.println("数组转存失败!");
            System.out.println(e.getMessage());
        }
    }


    /**
     * 从文件读取数组
     *
     * @param path 转存文件的路径
     * @return 读取的稀疏数组
     */
    public static int[][] arrayRead(String path) throws IOException {
        List<int[]> result = new ArrayList<>();
        // 创建输入流
        File file = new File(path);
        if (!file.exists()) {
            if (file.createNewFile()) {
                System.out.println("文件已创建\n");
            }
        }
        BufferedReader in = new BufferedReader(new FileReader(file));
        // 逐行读取输入流
        String line;
        while ((line = in.readLine()) != null) {
            String[] temp = line.split("\t");
            int[] intTemp = new int[temp.length];
            for (int i = 0; i < temp.length; i++) {
                intTemp[i] = Integer.parseInt(temp[i]);
            }
            result.add(intTemp);
        }
        in.close();
        return result.toArray(new int[result.size()][]);
    }


    /**
     * 在控制台输出数组
     *
     * @param array
     */
    public static void arrayPrint(int[][] array) {
        for (int[] row : array) {
            for (int i = 0; i < row.length; i++) {
                System.out.print(row[i] + (i == row.length - 1 ? "\n" : "\t"));
            }
        }
    }
}