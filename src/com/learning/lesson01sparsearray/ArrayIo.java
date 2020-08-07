package com.learning.lesson01sparsearray;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 数组的转存与读取
 *
 * @author Zongheng Ma
 */
class ArrayIo {

    /**
     * 转存文件的路径
     */
    private static final String PATH = "d:\\workspace_data_structure\\data_structure\\resource\\file\\parse_array.md";

    /**
     * 数组转存为文本
     *
     * @param array 待转存数组
     */
    public static void arraySave(int[][] array) {
        try {
            File file = new File(PATH);
            // 若文件不存在则进行创建
            if (!file.exists()) {
                if (file.createNewFile()) {
                    System.out.println("文件已创建\n");
                }
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
            e.printStackTrace();
        }
    }


    /**
     * 从文件读取数组
     *
     * @return int[][]
     */
    public static int[][] arrayRead() throws IOException {
        List<int[]> result = new ArrayList<>();
        // 创建输入流
        File file = new File(PATH);
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
