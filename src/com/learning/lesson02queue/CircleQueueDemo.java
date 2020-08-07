package com.learning.lesson02queue;

import java.util.Scanner;

/**
 * 数组模拟循环队列demo
 *
 * @author Zongheng Ma
 * @date 2020-4-26
 */
public class CircleQueueDemo {

    public static void main(String[] args) {
        // 数组容量为4，队列容量为4-1=3
        CircleQueue queue = new CircleQueue(4);

        // key存储输入的指令
        String key;
        Scanner scanner = new Scanner(System.in);

        // 利用while循环显示指令菜单
        boolean loop = true;
        while (loop) {
            System.out.println("请输入指令");
            System.out.println("    s(show)：显示队列");
            System.out.println("    h(head)：显示队头");
            System.out.println("    n(num)：有效元素个数");
            System.out.println("    e(exit)：退出");
            System.out.println("    a(addQueue)：元素入队");
            System.out.println("    d(deQueue)：队头出队");
            key = scanner.next();
            switch (key) {
                // 显示队列
                case "s":
                    queue.printQueue();
                    break;
                case "h":
                    try {
                        System.out.printf("当前队头元素为 %d\n", queue.head());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                // 显示有效元素个数
                case "n":
                    System.out.printf("当前队列有效元素个数为 %d\n", queue.num());
                    break;
                // 退出
                case "e":
                    scanner.close();
                    loop = false;
                    break;
                // 元素入队
                case "a":
                    System.out.print("请输入待入队元素：");
                    String num = scanner.next();
                    queue.addQueue(Integer.parseInt(num));
                    break;
                // 队头出队
                case "d":
                    try {
                        queue.deQueue();
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
