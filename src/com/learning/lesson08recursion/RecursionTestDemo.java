package com.learning.lesson08recursion;

/**
 * 递归演示实例
 *
 * @author Zongheng Ma
 * @date 2020-5-12
 */
public class RecursionTestDemo {

    public static void main(String[] args) {
        int n = 4;
        print(n);
        System.out.println("----------------");
        printAlter(n);
        System.out.println("----------------");
        System.out.printf("%d! = %d\n", n, factorial(n));
    }


    /**
     * 打印问题
     *
     * @param n
     */
    public static void print(int n) {
        if (n > 1) {
            print(n - 1);
        }
        System.out.printf("n = %d\n", n);
    }


    /**
     * 打印问题的另一种情况
     *
     * @param n
     */
    public static void printAlter(int n) {
        /*
            有else的情况 输出只有 n = 1
            在n=1之前的函数都进入了n>1的if分支
            在回溯时，if已经执行完（else内的打印语句被跳过）
            故不会有其他打印
         */
        if (n > 1) {
            printAlter(n - 1);
        } else {
            System.out.printf("n = %d\n", n);
        }
        // 回溯验证语句
        System.out.printf("分支 %d 已回溯\n", n);
    }


    /**
     * 阶乘问题
     *
     * @param n
     * @return n的阶乘
     */
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }
}
