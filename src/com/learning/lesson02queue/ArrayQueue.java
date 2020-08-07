package com.learning.lesson02queue;

/**
 * 队列类
 *
 * @author Zongheng Ma
 */
public class ArrayQueue {

    /**
     * 队列最大容量
     */
    private final int maxSize;

    /**
     * 队头（指向第一个元素前一个位置）
     */
    private final int front;

    /**
     * 队尾（指向队尾元素）
     */
    private int rear;

    /**
     * 模拟队列
     */
    private final int[] array;


    /**
     * 构造函数
     *
     * @param arrMaxSize 队列最大容量
     */
    public ArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        rear = -1;
        front = -1;
        array = new int[maxSize];

    }


    /**
     * 判断队列是否满
     *
     * @return 已满 true；未满 false
     */
    public boolean isFull() {
        return rear == maxSize - 1;
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
            array[++rear] = item;
            System.out.printf("元素 %d 入队成功\n", item);
        }
    }


    /**
     * 出队，获取队头元素
     *
     * @return int
     */
    public int deQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，无法出队");
        } else {
            int num = array[0];
            if (rear > 0) {
                /*
                 * 若采用出队front++、入队rear++的形式
                 * 在元素满队后，由于rear指针始终指向最后一个元素
                 * 即rear + 1 == maxSize始终成立，则后续添加元素会因为“队满“无法加入
                 * 故此处采用front固定不动、只移动rear的方式
                 */
                // 出队时将新队头至队尾的元素复制一份重新初始化数组
                System.arraycopy(array, 1, array, 0, rear);
            }
            rear--;
            return num;
        }
    }


    /**
     * 打印队列
     */
    public void printQueue() {
        if (isEmpty()) {
            System.out.println("队列空");
        } else {
            for (int i = front + 1; i <= rear; i++) {
                System.out.print(array[i] + (i != rear ? "\t" : "\n"));
            }
        }
    }
}
