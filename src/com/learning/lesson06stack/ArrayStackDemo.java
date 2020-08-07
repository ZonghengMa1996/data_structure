package com.learning.lesson06stack;

import java.util.Scanner;

/**
 * 数组模拟栈演示样例
 *
 * @author Zongheng Ma
 * @date 2020-5-6
 */
public class ArrayStackDemo {

    public static void main(String[] args) {

        ArrayStack stack = new ArrayStack(4);

        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("show 显示栈内元素");
            System.out.println("size 显示栈内有效元素个数");
            System.out.println("push 元素入栈");
            System.out.println("pop 栈顶元素出栈");
            System.out.println("exit 退出程序");
            System.out.print("请输入指令:");
            String key = scanner.next();
            switch (key) {
                case "show":
                    stack.showStack();
                    break;
                case "size":
                    System.out.printf("当前栈内有效元素个数为%d\n", stack.getSize());
                    break;
                case "exit":
                    loop = false;
                    scanner.close();
                    break;
                case "push":
                    System.out.print("请输入待入栈元素：");
                    int item = scanner.nextInt();
                    stack.push(item);
                    break;
                case "pop":
                    try {
                        System.out.printf("元素%d出栈\n", stack.pop());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
