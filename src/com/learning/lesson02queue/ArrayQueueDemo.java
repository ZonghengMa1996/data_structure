package com.learning.lesson02queue;

import java.util.Scanner;

/**
 * 数组模拟队列
 *
 * @author Zongheng Ma
 * @date 2020-4-24
 */
public class ArrayQueueDemo {

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);

        String key;
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show)：显示队列");
            System.out.println("e(exit)：退出");
            System.out.println("a(addQueue)：元素入队");
            System.out.println("d(deQueue)：队头出队");
            System.out.print("请输入指令：");
            key = scanner.next();
            switch (key) {
                // 显示队列
                case "s":
                    queue.printQueue();
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
                        System.out.printf("元素 %d 出队\n", queue.deQueue());
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
