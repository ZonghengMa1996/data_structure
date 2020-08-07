package com.learning.lesson02queue;

/**
 * 数组模拟循环队列
 *
 * @author Zongheng Ma
 * @date 2020-4-26
 */
public class CircleQueue {

    /**
     * 数组容量（实际队列容量为数组容量-1）
     */
    private final int maxSize;

    /**
     * 队头（指向队列第一个元）
     */
    private int front;

    /**
     * 队尾（指向队尾元素的后一个位置，空出一个位置作为约定）
     */
    private int rear;

    /**
     * 数组队列
     */
    private final int[] array;


    /**
     * 构造函数
     *
     * @param arrMaxSize 队列最大容量
     */
    public CircleQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        array = new int[maxSize];
    }


    /**
     * 判断队列是否满
     *
     * @return 已满 true；未满 false
     */
    public boolean isFull() {
        return front == (rear + 1) % maxSize;
    }


    /**
     * 判断队列是否空
     *
     * @return 空 true；不空 false
     */
    public boolean isEmpty() {
        return rear == front;
    }


    /**
     * 入队
     *
     * @param item 入队元素
     */
    public void addQueue(int item) {
        if (isFull()) {
            System.out.println("队列已满，无法入队");
        } else {
            // 先移动尾指针，再将指向元素赋值
            array[rear] = item;
            rear = (rear + 1) % maxSize;
            System.out.printf("元素 %d 入队成功，队尾在 %d\n", item, rear);
        }
    }


    /**
     * 出队，获取队头元素
     */
    public void deQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，无法出队");
        } else {
            int num = array[front];
            front = (front + 1) % maxSize;
            System.out.printf("元素 %d 出队，队头在 %d\n", num, front);
        }
    }


    /**
     * 打印队列
     */
    public void printQueue() {
        if (isEmpty()) {
            System.out.println("队列空");
        } else {
            for (int i = front; i % maxSize != rear; i++) {
                System.out.print(array[i % maxSize]
                        + ((i + 1) % maxSize == rear ? "\n" : "\t"));
            }

            // 韩老师的方法，遍历范围为front -> front+有效元素个数
            /*for (int i = front; i < front + num(); i++) {
                System.out.printf("arr[%d]=%d\n", i,
                            array[i % maxSize]);
            }*/
        }
    }

    /**
     * 显示队头元素
     *
     * @return int
     */
    public int head() {
        if (rear == front) {
            throw new RuntimeException("队为空");
        }
        return array[front];
    }

    /**
     * 显示有效元素个数
     */
    public int num() {
        return (rear + maxSize - front) % maxSize;
    }
}
