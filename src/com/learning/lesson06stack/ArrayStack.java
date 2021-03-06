package com.learning.lesson06stack;

/**
 * 数组模拟堆栈
 *
 * @author Zongheng Ma
 * @date 2020-5-6
 */
public class ArrayStack {

    /**
     * 栈顶(指向栈顶元素)
     */
    public int top;

    /**
     * 栈大小
     */
    public int size;

    /**
     * 栈数组
     */
    public int[] array;

    public ArrayStack(int size) {
        this.size = size;
        array = new int[size];
        top = -1;
    }


    /**
     * 栈是否空
     *
     * @return 空 true; 不空 false
     */
    public boolean isEmpty() {
        return top == -1;
    }


    /**
     * 栈是否满
     *
     * @return 已满 true; 未满 false
     */
    public boolean isFull() {
        return top + 1 == size;
    }


    /**
     * 入栈
     *
     * @param item 入栈元素
     */
    public void push(int item) {
        if (isFull()) {
            // 先判断栈是否满
            System.out.println("栈已满，元素无法入栈");
        } else {
            // 栈顶指针先上移（重新定位栈顶），将item赋给栈顶
            array[++top] = item;
        }
    }


    /**
     * 出栈
     */
    public int pop() {
        if (isEmpty()) {
            // 先判断栈是否空
            throw new RuntimeException("栈空");
        }
        // 先打印出栈元素，将栈顶下移
        return array[top--];
    }


    /**
     * 显示栈
     */
    public void showStack() {
        if (isEmpty()) {
            System.out.println("栈空，无元素可以显示");
        } else {
            System.out.println("-----------------\n栈内元素由顶至底依次为：");
            for (int i = top; i > -1; i--) {
                System.out.println(array[i]);
            }
            System.out.println("-----------------");
        }
    }


    /**
     * 获取栈顶元素
     *
     * @return int
     */
    public int getTop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        return array[top];
    }


    /**
     * 获取有效元素个数
     *
     * @return int
     */
    public int getSize() {
        return isEmpty() ? 0 : top + 1;
    }
}
